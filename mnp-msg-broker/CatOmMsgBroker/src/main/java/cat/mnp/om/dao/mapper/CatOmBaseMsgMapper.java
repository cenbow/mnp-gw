/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.om.dao.mapper;

import cat.mnp.om.domain.CatOmBaseMsg;
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
public class CatOmBaseMsgMapper implements StructMapper<CatOmBaseMsg> {

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
    public STRUCT toStruct(CatOmBaseMsg msg, Connection conn, String typeName) throws SQLException {
        StructDescriptor descriptor = new StructDescriptor(typeName, conn);

        List l = new ArrayList();
        l.add(msg.getPortType());
        l.add(msg.getMsgId());
        l.add(msg.getReqTransId());
        l.add(msg.getMsgCreateTimeStamp());

        ComplexSqlStructArrayValue msgList = new ComplexSqlStructArrayValue(msg.getMsgList(), structMapper, structTypeName);

        l.add(msgList.createTypeValue(conn, OracleTypes.ARRAY, arrayTypeName));

        return new STRUCT(descriptor, conn, l.toArray());
    }

    @Override
    public CatOmBaseMsg fromStruct(STRUCT struct) throws SQLException {
        CatOmBaseMsg msg = new CatOmBaseMsg();

        Object[] attr = struct.getAttributes();
        msg.setPortType(((BigDecimal) attr[0]).toBigInteger());
        msg.setMsgId(((BigDecimal) attr[1]).toBigInteger());
        msg.setReqTransId(((BigDecimal) attr[2]).toBigInteger());
        msg.setMsgCreateTimeStamp((String) attr[3]);

        List l = new ArrayList();
        msg.setMsgList(l);

        Object[] objArray = (Object[]) ((ARRAY) attr[4]).getArray();
        for (Object obj : objArray) {
            l.add(structMapper.fromStruct((STRUCT) obj));
        }

        return msg;
    }
}