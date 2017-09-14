/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.report;

import cat.mnp.clh.util.NpcMessageUtils;
import cat.mnp.file.FileSender;
import cat.mnp.mq.core.MsgHandlerBase;
import cat.mnp.mvno.dao.EntityMapMsgDao;
import cat.mnp.report.file.ReportFileWriter;
import com.telcordia.inpac.ws.jaxb.MessageHeaderType;
import com.telcordia.inpac.ws.jaxb.NPCDataType;
import com.telcordia.inpac.ws.jaxb.NPCMessageData;
import com.telcordia.inpac.ws.jaxb.NPCMessageType;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author HP-CAT
 */
public class ReportGenerator extends MsgHandlerBase {

    private static final Logger logger = LoggerFactory.getLogger(ReportGenerator.class);
    private EntityMapMsgDao entityMapMsgDao;
    private SummaryReportGenerator summaryReportWriter;
    private List<ReportFileWriter> reportWriterList;
    private String summaryText;
    private List<File> reportFileList;
    private FileSender fileSender;
    private boolean silentIfNoResult;

    public EntityMapMsgDao getEntityMapMsgDao() {
        return entityMapMsgDao;
    }

    public void setEntityMapMsgDao(EntityMapMsgDao entityMapMsgDao) {
        this.entityMapMsgDao = entityMapMsgDao;
    }

    public SummaryReportGenerator getSummaryReportWriter() {
        return summaryReportWriter;
    }

    public void setSummaryReportWriter(SummaryReportGenerator summaryReportWriter) {
        this.summaryReportWriter = summaryReportWriter;
    }

    public List<ReportFileWriter> getReportWriterList() {
        return reportWriterList;
    }

    public void setReportWriterList(List<ReportFileWriter> reportWriterList) {
        this.reportWriterList = reportWriterList;
    }

    public String getSummaryText() {
        return summaryText;
    }

    public List<File> getReportFileList() {
        return reportFileList;
    }

    public void setFileSender(FileSender fileSender) {
        this.fileSender = fileSender;
    }

    public boolean isSilentIfNoResult() {
        return silentIfNoResult;
    }

    public void setSilentIfNoResult(boolean silentIfNoResult) {
        this.silentIfNoResult = silentIfNoResult;
    }

    @Override
    public void processMsg(String msgString) throws Exception {
        NPCMessageData npcMessageData = NpcMessageUtils.unMarshal(getJaxbUnMarshaller(), msgString);

        NPCDataType npcDataType = npcMessageData.getNPCData();

        MessageHeaderType messageHeader = npcDataType.getMessageHeader();
        NPCMessageType npcMessages = npcDataType.getNPCMessages();

        List msgList = NpcMessageUtils.listOtherMsg(npcMessages);
        msgList.addAll(NpcMessageUtils.listBroadcastMsg(npcMessages));

        List<String> portIdList = new ArrayList<>();
        for (Object msgObject : msgList) {
            List<HashMap<String, String>> dataList = NpcMessageUtils.extractOtherMsgData(messageHeader, msgObject);

            for (HashMap<String, String> data : dataList) {
                portIdList.add(data.get("portId"));
            }
        }
        logger.info("Listed portId from {} size: {}", messageHeader, portIdList.size());

        List<Map<String, Object>> resultList = entityMapMsgDao.queryMsg(portIdList);
        logger.info("Listed result from {} size: {}", messageHeader, resultList.size());
        
        if (silentIfNoResult && resultList.isEmpty()) {
            logger.info("Ignore report {}", messageHeader);
            return;
        }

        if (summaryReportWriter != null) {
            summaryText = summaryReportWriter.generate(resultList);
        } else {
            summaryText = "";
        }
        reportFileList = new ArrayList<>();
        for (ReportFileWriter writer : reportWriterList) {
            writer.init();
            for (Map<String, Object> result : resultList) {
                writer.writeNext(result);
            }
            writer.close();
            reportFileList.add(writer.getFile());
        }

        if (fileSender != null) {
            fileSender.send(summaryText, reportFileList.toArray(new File[0]));
        } else {
            for (File reportFile : reportFileList) {
                moveFileToDirectory(reportFile, getBackupPath());
            }
        }
    }
}
