/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.report.file;

import au.com.bytecode.opencsv.CSVWriter;
import cat.mnp.mq.core.MsgHandlerBase;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.output.FileWriterWithEncoding;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author HP-CAT
 */
public class CsvReportWriter extends MsgHandlerBase implements ReportFileWriter {

    private static final Logger logger = LoggerFactory.getLogger(CsvReportWriter.class);
    private File file;
    private List<String[]> resultList;
    private int rownum;
    private boolean writeHeader;
    private String[] columnList;
    private String dateFormat;
    private String[] fileHeader;
    private String[] fileTailer;

    @Override
    public File getFile() throws IOException {
        if (file == null) {
            createFile();
        }
        return file;
    }

    public boolean isWriteHeader() {
        return writeHeader;
    }

    public void setWriteHeader(boolean writeHeader) {
        this.writeHeader = writeHeader;
    }

    public String[] getColumnList() {
        return columnList;
    }

    public void setColumnList(String[] columnList) {
        this.columnList = columnList;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public void setFileHeader(String[] fileHeader) {
        this.fileHeader = new String[fileHeader.length];
        for (int i = 0; i < fileHeader.length; i++) {
            this.fileHeader[i] = StringEscapeUtils.unescapeJava(fileHeader[i]);
        }
    }

    public void setFileTailer(String[] fileTailer) {
        this.fileTailer = new String[fileTailer.length];
        for (int i = 0; i < fileTailer.length; i++) {
            this.fileTailer[i] = StringEscapeUtils.unescapeJava(fileTailer[i]);
        }
    }

    @Override
    public void init() throws IOException {
        resultList = new ArrayList<>();
        file = null;
        rownum = 0;
        if (fileHeader != null) {
            doWrite(fileHeader);
        }
    }

    private void createFile() throws IOException {
        String dateString = DateFormatUtils.format(new Date(), getFileDateFormat());
        String[] threadNameList = Thread.currentThread().getName().split("-");
        String filename = String.format(getFilenameFormat(), dateString, threadNameList[threadNameList.length - 1]);
        FileUtils.forceMkdir(new File(getFilePath()));
        file = new File(FilenameUtils.concat(getFilePath(), filename));
    }

    private void writeHeader() {
        doWrite(columnList);
    }

    private void doWrite(String[] row) {
        resultList.add(row);
    }

    @Override
    public void writeNext(String[] row) {
        if (isWriteHeader() && rownum == 0) {
            writeHeader();
        }
        doWrite(row);
        rownum++;
    }

    @Override
    public void writeNext(Map<String, Object> rowMapper) {
        String[] row = new String[columnList.length];

        for (int cellnum = 0; cellnum < columnList.length; cellnum++) {
            Object rawData = rowMapper.get(columnList[cellnum]);
            String cellData;
            if (Date.class.isInstance(rawData)) {
                cellData = DateFormatUtils.format((Date) rawData, dateFormat);
            } else {
                cellData = Objects.toString(rawData, null);
            }
            row[cellnum] = cellData;
        }
        writeNext(row);
    }

    @Override
    public void close() throws IOException {
        if (fileTailer != null) {
            String[] tmpFileTailer = new String[fileTailer.length];
            for (int i = 0; i < fileTailer.length; i++) {
                tmpFileTailer[i] = fileTailer[i].replaceAll(":ROW_COUNT", rownum + "");
            }
            doWrite(tmpFileTailer);
        }
        try (CSVWriter writer = new CSVWriter(new FileWriterWithEncoding(getFile(), getFileEncoding()), getFileLineSeparator().charAt(0), CSVWriter.NO_QUOTE_CHARACTER)) {
            writer.writeAll(resultList);
        }
        logger.info("Report is generated to {}", file.getAbsolutePath());
    }
}
