/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.report;

import com.google.common.collect.ArrayListMultimap;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 *
 * @author HP-CAT
 */
public class SummaryReportGenerator {

    private static final Logger logger = LoggerFactory.getLogger(SummaryReportGenerator.class);
    private String summaryText;
    private String lineSeparator;
    private String[] columnList;
    private String dateFormat;
    private String noDataString;

    public void setSummaryText(String summaryText) {
        this.summaryText = StringEscapeUtils.unescapeJava(summaryText);
    }

    public void setLineSeparator(String lineSeparator) {
        this.lineSeparator = StringEscapeUtils.unescapeJava(lineSeparator);
    }

    public void setColumnList(String[] columnList) {
        this.columnList = columnList;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public void setNoDataString(String noDataString) {
        this.noDataString = noDataString;
    }

    public String generate(List<Map<String, Object>> data) {
        ArrayListMultimap<String, String> formatedData = ArrayListMultimap.create();
        for (Map<String, Object> row : data) {
            for (String column : columnList) {
                String colData = Objects.toString(row.get(column), null);
                if (colData != null) {
                    formatedData.put(column, colData);
                }
            }
        }
        String result = summaryText.replaceAll(":DATE", DateFormatUtils.format(new Date(), dateFormat));
        for (String column : columnList) {
            List<String> row = formatedData.get(column);
            if (!row.isEmpty()) {
                result = result.replaceAll(":" + column, StringUtils.arrayToDelimitedString(formatedData.get(column).toArray(), lineSeparator));
            } else {
                result = result.replaceAll(":" + column, noDataString);
            }
        }
        logger.debug("{}", result);
        return result;
    }
}
