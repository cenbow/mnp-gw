/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.report.file;

import cat.mnp.mq.core.MsgHandlerBase;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author HP-CAT
 */
public class XlsxReportWriter extends MsgHandlerBase implements ReportFileWriter {

    private static final Logger logger = LoggerFactory.getLogger(XlsxReportWriter.class);
    private File file;
    private Workbook workbook;
    private Sheet sheet;
    private CellStyle cellStyle;
    private int rownum;
    private boolean writeHeader;
    private String[] columnList;
    private String dateFormat;

    @Override
    public File getFile() throws IOException{
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

    @Override
    public void init() throws IOException {
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet();
        cellStyle = workbook.createCellStyle();
        DataFormat dataFormat = workbook.createDataFormat();
        cellStyle.setDataFormat(dataFormat.getFormat("Text"));
        file = null;
        rownum = 0;
    }

    private void createFile() throws IOException {
        String dateString = DateFormatUtils.format(new Date(), getFileDateFormat());
        String[] threadNameList = Thread.currentThread().getName().split("-");
        String filename = String.format(getFilenameFormat(), dateString, threadNameList[threadNameList.length-1]);
        FileUtils.forceMkdir(new File(getFilePath()));
        file = new File(FilenameUtils.concat(getFilePath(), filename));
    }

    private void writeHeader() {
        doWrite(columnList);
        rownum++;
    }

    private void doWrite(String[] row) {
        Row r = sheet.createRow(rownum);
        Cell c;
        for (int cellnum = 0; cellnum < row.length; cellnum++) {
            c = r.createCell(cellnum);
            c.setCellStyle(cellStyle);
            c.setCellValue(row[cellnum]);
        }
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
        try (FileOutputStream fileOutputStream = new FileOutputStream(getFile())) {
            workbook.write(fileOutputStream);
            logger.info("Report is generated to {}", file.getAbsolutePath());
        }
    }
}
