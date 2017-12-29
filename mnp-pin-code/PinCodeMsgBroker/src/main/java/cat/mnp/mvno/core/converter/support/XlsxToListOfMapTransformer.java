/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.mvno.core.converter.support;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.util.StringUtils;

/**
 *
 * @author CATr
 */
public class XlsxToListOfMapTransformer {

    private static final Logger logger = LoggerFactory.getLogger(XlsxToListOfMapTransformer.class);

    private int firstRow = 1;
    private String[] colHeaders;
    private Set<String> skipRowIfColHeadersEmpty = new HashSet<>();

    private DataFormatter dataFormatter;

    public void setFirstRow(int firstRow) {
        this.firstRow = firstRow;
    }

    public void setColHeaders(String[] colHeaders) {
        this.colHeaders = colHeaders;
    }

    public void setSkipRowIfColHeadersEmpty(String[] skipRowIfColHeadersEmpty) {
        this.skipRowIfColHeadersEmpty = Sets.newHashSet(skipRowIfColHeadersEmpty);
    }

    public void setDataFormatter(DataFormatter dataFormatter) {
        this.dataFormatter = dataFormatter;
    }

    @Transformer
    public List<Map<String, String>> transform(@Header(FileHeaders.FILENAME) String fileName, @Payload byte[] fileByte) throws IOException, InvalidFormatException {
        List<Map<String, String>> dataList = new ArrayList<>();
        try (ByteArrayInputStream bis = new ByteArrayInputStream(fileByte);
            XSSFWorkbook workbook = new XSSFWorkbook(bis)) {

            int firstSheet = workbook.getFirstVisibleTab();
            XSSFSheet sheet = workbook.getSheetAt(firstSheet);

            List<Row> rowList = Lists.newArrayList(sheet.iterator());
            rowList = rowList.subList(firstRow, rowList.size());

            for (Row row : rowList) {
                Map<String, String> rowMap = new HashMap<>();
                boolean skipRow = false;
                for (int cellIndex = 0; cellIndex < colHeaders.length; cellIndex++) {
                    Cell cell = row.getCell(cellIndex);
                    String cellHeader = colHeaders[cellIndex];
                    String cellValue = dataFormatter.formatCellValue(cell);
                    if (skipRowIfColHeadersEmpty.contains(cellHeader) && StringUtils.isEmpty(cellValue)) {
                        skipRow = true;
                        break;
                    }
                    cellValue = StringUtils.trimWhitespace(cellValue);
                    rowMap.put(cellHeader, cellValue);
                }
                if (skipRow) {
                    continue;
                }
                logger.trace("Row data: {}", rowMap);
                dataList.add(rowMap);
            }
        }
        logger.info("Xlsx '{}' contains {} rows", fileName, dataList.size());
        return dataList;
    }
}
