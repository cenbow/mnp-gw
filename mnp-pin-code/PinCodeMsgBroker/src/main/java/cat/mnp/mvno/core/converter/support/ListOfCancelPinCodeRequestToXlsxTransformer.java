/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.mvno.core.converter.support;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.integration.annotation.Transformer;

import cat.mnp.pincode.ws.portout.CancelPinCodeRequest;

/**
 *
 * @author CATr
 */
public class ListOfCancelPinCodeRequestToXlsxTransformer {

	private static final Logger logger = LoggerFactory.getLogger(ListOfCancelPinCodeRequestToXlsxTransformer.class);

	private int firstRow = 1;
	private String[] colHeaders;
	private Resource templateResource;

	public void setFirstRow(int firstRow) {
		this.firstRow = firstRow;
	}

	public void setColHeaders(String[] colHeaders) {
		this.colHeaders = colHeaders;
	}

	public void setTemplateResource(Resource templateResource) {
		this.templateResource = templateResource;
	}

	@Transformer
	public byte[] transform(List<CancelPinCodeRequest> dataList) throws IOException, InvalidFormatException {
		byte[] output;
		logger.info("Generating xlsx from data size: {}", dataList.size());
		try (InputStream is = templateResource.getInputStream();
				XSSFWorkbook workbook = new XSSFWorkbook(is);
				ByteArrayOutputStream bos = new ByteArrayOutputStream()) {

			int firstSheet = workbook.getFirstVisibleTab();
			XSSFSheet sheet = workbook.getSheetAt(firstSheet);
			CellStyle[] cellStyles = getCellStyles(sheet.getRow(firstRow));

			for (int rownum = 0; rownum < dataList.size(); rownum++) { // now we expect only size =1
				CancelPinCodeRequest rowData = dataList.get(rownum);
				setMsisdnRows(rowData, sheet, rownum, cellStyles);
			}

			workbook.write(bos);
			output = bos.toByteArray();
		}
		logger.info("Generated xlsx file size: {}", FileUtils.byteCountToDisplaySize(output.length));
		return output;
	}

	private int setMsisdnRows(CancelPinCodeRequest rowData, XSSFSheet sheet, int rownum, CellStyle[] cellStyles) {
		for (int i = 0; i < rowData.getMsisdnList().size(); i++) {
			String msisdn = rowData.getMsisdnList().get(i);
			Row r = sheet.createRow(firstRow + rownum++);
			int j = 0;
			setCell(cellStyles, (i+1) + "", j++, r);
			setCell(cellStyles, msisdn, j++, r);
			// setCell(cellStyles, rowData., j++, r);
		}
		return rownum;
	}

	private void setCell(CellStyle[] cellStyles, String v, int j, Row r) {

		Cell c = r.createCell(j);
		c.setCellStyle(cellStyles[j]);
		c.setCellValue(v);

	}

	private CellStyle[] getCellStyles(Row row) {
		List<CellStyle> cellStyleList = new ArrayList<>();
		for (int cellnum = 0; cellnum < colHeaders.length; cellnum++) {
			cellStyleList.add(row.getCell(cellnum).getCellStyle());
		}
		return cellStyleList.toArray(new CellStyle[0]);
	}

}
