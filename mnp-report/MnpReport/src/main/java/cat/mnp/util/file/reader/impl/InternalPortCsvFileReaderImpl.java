/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.util.file.reader.impl;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import au.com.bytecode.opencsv.bean.CsvToBean;
import au.com.bytecode.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import au.com.bytecode.opencsv.bean.MappingStrategy;
import cat.mnp.sps.hibernate.InternalPortHist;
import cat.mnp.util.file.reader.CsvFileReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HP-CAT
 */
public class InternalPortCsvFileReaderImpl implements CsvFileReader {
    
    private CSVReader reader;
    private Map<String, String> columnMapper;
    private MappingStrategy<InternalPortHist> mappingStrategy;

    public Map<String, String> getColumnMapper() {
        return columnMapper;
    }

    public void setColumnMapper(Map<String, String> columnMapper) {
        this.columnMapper = columnMapper;
    }
    
    public InternalPortCsvFileReaderImpl(String fileName, String fileEncoding) throws IOException {
        init(new File(fileName), fileEncoding);
    }
    
    public InternalPortCsvFileReaderImpl(File file, String fileEncoding) throws IOException {
        init(file, fileEncoding);
    }
    
    private void init(File file, String fileEncoding) throws IOException {
        reader = new CSVReader(new InputStreamReader(new FileInputStream(file), fileEncoding), ',', CSVWriter.DEFAULT_QUOTE_CHARACTER);
    }
    
    @Override
    public List readAll() throws IOException {
        CsvToBean<InternalPortHist> bean = new CsvToBean<InternalPortHist>();
        
        HeaderColumnNameTranslateMappingStrategy<InternalPortHist> strategy = new HeaderColumnNameTranslateMappingStrategy<InternalPortHist>();
        strategy.setType(InternalPortHist.class);
        strategy.setColumnMapping(columnMapper);
    
        List internalPortHistList = bean.parse(strategy, reader);
        
        return internalPortHistList;
    }
    
    @Override
    public void close() throws Exception {
        reader.close();
    }
}
