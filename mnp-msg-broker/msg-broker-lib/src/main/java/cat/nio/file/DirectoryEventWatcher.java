/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.nio.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static java.nio.file.StandardWatchEventKinds.*;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 *
 * @author HP-CAT
 */
public class DirectoryEventWatcher implements DisposableBean {

    private static final Logger logger = LoggerFactory.getLogger(DirectoryEventWatcher.class);
    private Future<Integer> watchTask;
    private MessageChannel inputChannel;
    private WatchService watchService;
    private Path watchPath;
    private boolean watchRecursively;
    private ThreadPoolTaskExecutor taskExecutor;

    public void setInputChannel(MessageChannel inputChannel) {
        this.inputChannel = inputChannel;
    }

    public void setWatchPath(String path) {
        this.watchPath = new File(path).toPath();
    }

    public boolean isWatchRecursively() {
        return watchRecursively;
    }

    public void setWatchRecursively(boolean watchRecursively) {
        this.watchRecursively = watchRecursively;
    }

    public void setTaskExecutor(ThreadPoolTaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public void init() throws Exception {
        watchService = FileSystems.getDefault().newWatchService();
        registerDirectories();
        createWatchTask();
    }

    public boolean isRunning() {
        return watchTask != null && !watchTask.isDone();
    }

    @Override
    public void destroy() throws IOException {
        watchService.close();
    }

    private void createWatchTask() throws Exception {
        watchTask = taskExecutor.submit(new Callable<Integer>() {
            private int totalEventCount;

            @Override
            public Integer call() throws Exception {
                while (!Thread.interrupted()) {
                    WatchKey watchKey = watchService.poll(10, TimeUnit.SECONDS);
                    if (watchKey != null) {
                        List<WatchEvent<?>> events = watchKey.pollEvents();
                        Path watched = (Path) watchKey.watchable();
                        PathEvents pathEvents = new PathEvents(watchKey.isValid(), watched);
                        for (WatchEvent event : events) {
                            pathEvents.add(new PathEvent(watchPath.resolve((Path) event.context()), event.kind()));
                            totalEventCount++;
                        }
                        watchKey.reset();

                        final Message<PathEvents> message = MessageBuilder.withPayload(pathEvents).build();

                        inputChannel.send(message);
                    }
                }
                return totalEventCount;
            }
        });
        logger.debug("Watch task is created for '{}'", watchPath.toAbsolutePath());
    }

    private void registerDirectories() throws IOException {
        if (watchRecursively) {
            Files.walkFileTree(watchPath, new WatchServiceRegisteringVisitor());
        } else {
            new WatchServiceRegisteringVisitor().preVisitDirectory(watchPath, null);
        }
    }

    private class WatchServiceRegisteringVisitor extends SimpleFileVisitor<Path> {

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            dir.register(watchService, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
            return FileVisitResult.CONTINUE;
        }
    }
}
