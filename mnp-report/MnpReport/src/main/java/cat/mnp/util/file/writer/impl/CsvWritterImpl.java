/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.util.file.writer.impl;

import au.com.bytecode.opencsv.CSVWriter;
import cat.mnp.util.file.writer.FileWriter;
import java.io.IOException;
import org.apache.commons.io.output.FileWriterWithEncoding;

/**
 *
 * @author HP-CAT
 */
public class CsvWritterImpl implements FileWriter {

    private CSVWriter writer;

    public CsvWritterImpl(String fileName, String fileEncoding) throws IOException {
        writer = new CSVWriter(new FileWriterWithEncoding(fileName, fileEncoding), ',', CSVWriter.NO_QUOTE_CHARACTER);
    }

    @Override
    public void writeNext(String[] strings) {
        writer.writeNext(strings);
    }

    @Override
    public void close() throws Exception {
        writer.close();
    }
}
