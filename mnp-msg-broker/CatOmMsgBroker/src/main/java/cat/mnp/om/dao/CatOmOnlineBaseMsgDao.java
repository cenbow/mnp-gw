/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.om.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jdbc.support.oracle.ComplexSqlStructArrayValue;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import cat.mnp.om.dao.mapper.CatOmBaseMsgMapper;
import cat.mnp.om.domain.CatOmBaseMsg;
import oracle.sql.ARRAY;
import oracle.sql.STRUCT;

/**
 *
 * @author HP-CAT
 */
public class CatOmOnlineBaseMsgDao extends CatOmBaseMsgDao {

	private static final Logger logger = LoggerFactory.getLogger(CatOmOnlineBaseMsgDao.class);
	private SimpleJdbcCall jdbcCaller;
	private String inputParameterName;
	private String outputParameterName;
	private String errorParameterName;
	private Map jdbcInputParameters;
	private List<SqlParameter> callParameterList;
	private CatOmBaseMsgMapper catOmBaseMsgMapper;
	private String structTypeName;

	public SimpleJdbcCall getJdbcCaller() {
		return jdbcCaller;
	}

	public void setJdbcCaller(SimpleJdbcCall jdbcCaller) {
		this.jdbcCaller = jdbcCaller;
	}

	public String getInputParameterName() {
		return inputParameterName;
	}

	public void setInputParameterName(String inputParameterName) {
		this.inputParameterName = inputParameterName;
	}

	public String getOutputParameterName() {
		return outputParameterName;
	}

	public void setOutputParameterName(String outputParameterName) {
		this.outputParameterName = outputParameterName;
	}

	public String getErrorParameterName() {
		return errorParameterName;
	}

	public void setErrorParameterName(String errorParameterName) {
		this.errorParameterName = errorParameterName;
	}

	public Map getJdbcInputParameters() {
		return jdbcInputParameters;
	}

	public void setJdbcInputParameters(Map jdbcInputParameters) {
		this.jdbcInputParameters = jdbcInputParameters;
	}

	public List<SqlParameter> getCallParameterList() {
		return callParameterList;
	}

	public void setCallParameterList(List<SqlParameter> callParameterList) {
		this.callParameterList = callParameterList;
	}

	public CatOmBaseMsgMapper getCatOmBaseMsgMapper() {
		return catOmBaseMsgMapper;
	}

	public void setCatOmBaseMsgMapper(CatOmBaseMsgMapper catOmBaseMsgMapper) {
		this.catOmBaseMsgMapper = catOmBaseMsgMapper;
	}

	public String getStructTypeName() {
		return structTypeName;
	}

	public void setStructTypeName(String structTypeName) {
		this.structTypeName = structTypeName;
	}

	@Override
	public String importMsg(Object msgObject) throws Exception {
		CatOmBaseMsg msg = (CatOmBaseMsg) msgObject;

		jdbcInputParameters.put(inputParameterName, new ComplexSqlStructArrayValue(msg, catOmBaseMsgMapper, structTypeName));
		Map jdbcOutputParameters = jdbcCaller.declareParameters(callParameterList.toArray(new SqlParameter[0])).execute(jdbcInputParameters);
		return (String) jdbcOutputParameters.get(errorParameterName);
	}

	public List<CatOmBaseMsg> mergeOnlineMsg(Object msgObject) throws Exception {
		String orderId = (String) msgObject;
		jdbcInputParameters.put("i_order", orderId);
		Map jdbcOutputParameters = jdbcCaller.declareParameters(callParameterList.toArray(new SqlParameter[0])).execute(jdbcInputParameters);

		String error = (String) jdbcOutputParameters.get(errorParameterName);
		if (error != null) {
			SQLException ex = new SQLException(error);
			logger.error("Error detected while merging msg", ex);
			throw ex;
		}

		Object[] objArray = (Object[]) ((ARRAY) jdbcOutputParameters.get(outputParameterName)).getArray();

		logger.debug("Array size: {}", objArray.length);
		List<CatOmBaseMsg> l = new ArrayList<>();
		for (Object obj : objArray) {
			l.add(catOmBaseMsgMapper.fromStruct((STRUCT) obj));
		}
		return l;
	}
}
