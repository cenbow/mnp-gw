/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.util.file.writer.impl;

import cat.mnp.util.file.writer.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

/**
 *
 * @author HP-CAT
 */
public class XlsWriterImpl implements FileWriter {

    private String fileName;
    private FileOutputStream fileOutputStream;
    private Workbook workbook;
    private Sheet sheet;
    private CellStyle cellStyle;
    private int rownum = 0;

    public XlsWriterImpl(String fileName) throws FileNotFoundException {
        this.fileName = fileName;

        workbook = new HSSFWorkbook();
        sheet = workbook.createSheet();

        cellStyle = workbook.createCellStyle();
        DataFormat dataFormat = workbook.createDataFormat();
        cellStyle.setDataFormat(dataFormat.getFormat("Text"));
    }

    @Override
    public void writeNext(String[] strings) {
        Row r = sheet.createRow(rownum);
        Cell c;
        for (int cellnum = 0; cellnum < strings.length; cellnum++) {
            c = r.createCell(cellnum);
            c.setCellStyle(cellStyle);
            c.setCellValue(strings[cellnum]);
        }
        rownum++;
    }

    @Override
    public void close() throws IOException {
        fileOutputStream = new FileOutputStream(fileName);
        workbook.write(fileOutputStream);
        fileOutputStream.close();
    }
}
