/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.nio.file.event;

import cat.nio.file.PathEvent;
import java.nio.file.Path;
import java.util.List;

/**
 *
 * @author HP-CAT
 */
public interface PathEventContext {

    boolean isValid();

    Path getWatchedDirectory();

    List<PathEvent> getEvents();
}
