/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.om.dao.mapper;

import cat.mnp.om.domain.CatOmOrder;
import cat.mnp.om.domain.CatOmService;
import cat.mnp.om.util.CatOmNpcMessageUtils;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
import org.springframework.data.jdbc.support.oracle.ComplexSqlStructArrayValue;
import org.springframework.data.jdbc.support.oracle.StructMapper;

/**
 *
 * @author HP-CAT
 */
public class CatOmOrderMapper implements StructMapper<CatOmOrder> {

    private String structTypeName;
    private String arrayTypeName;
    private StructMapper structMapper;

    public String getStructTypeName() {
        return structTypeName;
    }

    public void setStructTypeName(String structTypeName) {
        this.structTypeName = structTypeName;
    }

    public String getArrayTypeName() {
        return arrayTypeName;
    }

    public void setArrayTypeName(String arrayTypeName) {
        this.arrayTypeName = arrayTypeName;
    }

    public StructMapper getStructMapper() {
        return structMapper;
    }

    public void setStructMapper(StructMapper structMapper) {
        this.structMapper = structMapper;
    }

    @Override
    public STRUCT toStruct(CatOmOrder msg, Connection conn, String typeName) throws SQLException {
        StructDescriptor descriptor = new StructDescriptor(typeName, conn);

        List l = new ArrayList();
        l.add(msg.getOrderId());
        l.add(msg.getProcessType());
        l.add(msg.getOrderDate());
        l.add(msg.getCustomerIdentifier());
        l.add(msg.getCustomerRemark());
        l.add(msg.getZone());
        l.add(msg.getRecipient());
        l.add(msg.getOperatorCode());
        l.add(msg.getDonor());
        l.add(msg.getValidDeadlineDate());
        l.add(msg.getTimeMsgId());
        l.add(msg.getTimeTimerCode());
        l.add(msg.getChannelId());

        ComplexSqlStructArrayValue serviceList = new ComplexSqlStructArrayValue(
                msg.getServiceList(),
                structMapper, structTypeName);

        l.add(serviceList.createTypeValue(conn, OracleTypes.ARRAY, arrayTypeName));

        return new STRUCT(descriptor, conn, l.toArray());
    }

    @Override
    public CatOmOrder fromStruct(STRUCT struct) throws SQLException {
        CatOmOrder msg = new CatOmOrder();
        
        Object[] attr = struct.getAttributes();
        msg.setOrderId((String) attr[0]);
        msg.setProcessType(CatOmNpcMessageUtils.bigDecimalToBigInteger((BigDecimal) attr[1]));
        msg.setOrderDate((String) attr[2]);
        msg.setCustomerIdentifier((String) attr[3]);
        msg.setCustomerRemark((String) attr[4]);
        msg.setZone(CatOmNpcMessageUtils.bigDecimalToBigInteger((BigDecimal) attr[5]));
        msg.setRecipient((String) attr[6]);
        msg.setOperatorCode((String) attr[7]);
        msg.setDonor((String) attr[8]);
        msg.setValidDeadlineDate((String) attr[9]);
        msg.setTimeMsgId(CatOmNpcMessageUtils.bigDecimalToBigInteger((BigDecimal) attr[10]));
        msg.setTimeTimerCode((String) attr[11]);
        msg.setChannelId((String) attr[12]);
        
        List<CatOmService> l = new ArrayList<>();
        msg.setServiceList(l);
        
        Object[] objArray = (Object[]) ((ARRAY) attr[13]).getArray();
        for (Object obj : objArray) {
            l.add((CatOmService) structMapper.fromStruct((STRUCT) obj));
        }
        
        return msg;
    }
}