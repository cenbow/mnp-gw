/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.om.dao.mapper;

import cat.mnp.om.domain.CatOmService;
import cat.mnp.om.util.CatOmNpcMessageUtils;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
import org.springframework.data.jdbc.support.oracle.StructMapper;

/**
 *
 * @author HP-CAT
 */
public class CatOmServiceMapper implements StructMapper<CatOmService> {
    
    private boolean extended;

    public boolean isExtended() {
        return extended;
    }

    public void setExtended(boolean extended) {
        this.extended = extended;
    }

    @Override
    public STRUCT toStruct(CatOmService msg, Connection conn, String typeName) throws SQLException {
        StructDescriptor descriptor = new StructDescriptor(typeName, conn);

        List l = new ArrayList();
        l.add(msg.getOrderId());
        l.add(msg.getPortId());
        l.add(msg.getProcessType());
        l.add(msg.getMsisdn());
        l.add(msg.getPinCode());
        l.add(msg.getCorrectPinCode());
        l.add(msg.getClhAccpFlag());
        l.add(msg.getClhRejCode());
        l.add(msg.getNumAcceptFlag());
        l.add(msg.getDonorRejReasonCode());
        l.add(msg.getRecipientConfFlag());
        l.add(msg.getPortingDate());
        l.add(msg.getNumAppFlag());
        l.add(msg.getRejectReasonCode());
        l.add(msg.getDonor());
        l.add(msg.getRecipient());
        l.add(msg.getNumberRangeHolder());
        l.add(msg.getRoute());
        l.add(msg.getZone());
        l.add(msg.getOperatorCode());
        l.add(msg.getDownloadType());
        l.add(msg.getDownloadStartDate());
        l.add(msg.getDownloadEndDate());
        l.add(msg.getSynReqId());
        l.add(msg.getDataDate());
        l.add(msg.getDataLocation());
        l.add(msg.getContactdetail());
        l.add(msg.getNotifyErrCode());
        l.add(msg.getNotifyErrDesc());
        l.add(msg.getNotifyErrMsgId());
        if (isExtended()) {
            l.add(msg.getIsPrepaid());
        }

        return new STRUCT(descriptor, conn, l.toArray());
    }

    @Override
    public CatOmService fromStruct(STRUCT struct) throws SQLException {
        CatOmService msg = new CatOmService();
        
        Object[] attr = struct.getAttributes();
        msg.setOrderId((String) attr[0]);
        msg.setPortId((String) attr[1]);
        msg.setProcessType(CatOmNpcMessageUtils.bigDecimalToBigInteger((BigDecimal) attr[2]));
        msg.setMsisdn((String) attr[3]);
        msg.setPinCode((String) attr[4]);
        msg.setCorrectPinCode((String) attr[5]);
        msg.setClhAccpFlag(CatOmNpcMessageUtils.bigDecimalToBigInteger((BigDecimal) attr[6]));
        msg.setClhRejCode((String) attr[7]);
        msg.setNumAcceptFlag(CatOmNpcMessageUtils.bigDecimalToBigInteger((BigDecimal) attr[8]));
        msg.setDonorRejReasonCode((String) attr[9]);
        msg.setRecipientConfFlag(CatOmNpcMessageUtils.bigDecimalToBigInteger((BigDecimal) attr[10]));
        msg.setPortingDate((String) attr[11]);
        msg.setNumAppFlag((String) attr[12]);
        msg.setRejectReasonCode((String) attr[13]);
        msg.setDonor((String) attr[14]);
        msg.setRecipient((String) attr[15]);
        msg.setNumberRangeHolder((String) attr[16]);
        msg.setRoute((String) attr[17]);
        msg.setZone(CatOmNpcMessageUtils.bigDecimalToBigInteger((BigDecimal) attr[18]));
        msg.setOperatorCode((String) attr[19]);
        msg.setDownloadType((String) attr[20]);
        msg.setDownloadStartDate((String) attr[21]);
        msg.setDownloadEndDate((String) attr[22]);
        msg.setSynReqId((String) attr[23]);
        msg.setDataDate((String) attr[24]);
        msg.setDataLocation((String) attr[25]);
        msg.setContactdetail((String) attr[26]);
        msg.setNotifyErrCode((String) attr[27]);
        msg.setNotifyErrDesc((String) attr[28]);
        msg.setNotifyErrMsgId(CatOmNpcMessageUtils.bigDecimalToBigInteger((BigDecimal) attr[29]));
        if (isExtended()) {
            msg.setIsPrepaid(CatOmNpcMessageUtils.bigDecimalToBigInteger((BigDecimal) attr[30]));
        }

        return msg;
    }
}