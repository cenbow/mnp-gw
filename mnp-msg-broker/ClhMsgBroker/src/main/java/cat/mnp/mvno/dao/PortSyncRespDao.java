package cat.mnp.mvno.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import jaxb.clh.npcbulksync.ActivatedNumberType;
import jaxb.clh.npcbulksync.NPCData;
import miw.sql.util.OracleTypeUtil;
import miw.util.DBHelper;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;

public class PortSyncRespDao extends JdbcDaoSupport {
	private static final Logger logger = LoggerFactory.getLogger(PortSyncRespDao.class);
	public void insert(NPCData npcData) {

		logger.info("npcData: MessageName={}, IDNumber={}, NumberOfMessages={}", npcData.getMessageName(), npcData.getIDNumber(), npcData.getNumberOfMessages());
		Long parentId = getJdbcTemplate().queryForObject(" select mnpdb.mnp_port_sync_resp_seq.nextval from dual", Long.class);

		String sql = "insert into mnpdb.mnp_port_sync_resp ( id, messagename, idnumber, numberofmessages) " + "values ( ?, ?, ?, ? )";
		getJdbcTemplate().update(sql, new Object[]{parentId, npcData.getMessageName(), npcData.getIDNumber(), npcData.getNumberOfMessages()});

		List dtlList = new ArrayList();
		int i = 0;
		for (ActivatedNumberType a : npcData.getActivatedNumbers().getActivatedNumber()) {
			logger.debug((++i) + ". Action={}, PortId={}, MSISDN={}, Donor={}, Recipient={}, ActivationDate={}", a.getAction(), a.getPortId(), a.getMSISDN(), a.getDonor(), a.getRecipient(),
					a.getActivationDate());
			dtlList.add(new Object[]{parentId, a.getAction(), a.getPortId(), a.getMSISDN(), a.getDonor(), a.getRecipient(), a.getNumberHolderInd(), a.getRoute(), a.getActivationDate()});

		}
		String dSql = "insert into mnpdb.mnp_port_sync_resp_dtl ( p_id,id, action, portid, msisdn, donor, recipient, numberholderind, route, activationdate) "
				+ "values (?, mnpdb.mnp_port_sync_resp_dtl_seq.nextval, ?, ?, ?, ?, ?, ?,?,? )";
		getJdbcTemplate().batchUpdate(dSql, dtlList);

	}

	public void transfromNpcData(NPCData npcData, boolean isRmv) throws SQLException {
		String storeName = "CONVERT_MVNO";
		if (isRmv) {
			storeName = "CONVERT_MVNO_FOR_RMV001";
		}

		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate()).withCatalogName("MVNO_BROADCAST").withProcedureName(storeName).declareParameters(
				new SqlParameter("i_msg_id", OracleTypes.VARCHAR),
				new SqlParameter("i_donor", OracleTypes.VARCHAR),
				new SqlParameter("i_recipient", OracleTypes.VARCHAR),
				new SqlParameter("i_msisdn", OracleTypes.VARCHAR),
				new SqlParameter("i_port_id", OracleTypes.VARCHAR),
				new SqlParameter("i_route", OracleTypes.VARCHAR),
				new SqlParameter("i_msg_create_timestamp", OracleTypes.VARCHAR),
				new SqlOutParameter("o_donor", OracleTypes.VARCHAR),
				new SqlOutParameter("o_recipient", OracleTypes.VARCHAR),
				new SqlOutParameter("o_route", OracleTypes.VARCHAR));

		for (ActivatedNumberType a : npcData.getActivatedNumbers().getActivatedNumber()) {
			logger.debug(". Action={}, PortId={}, MSISDN={}, Donor={}, Recipient={}, ActivationDate={}", a.getAction(), a.getPortId(), a.getMSISDN(), a.getDonor(), a.getRecipient(),
					a.getActivationDate());

			Map inMap = new LinkedHashMap<>();
			inMap.put("i_msg_id", "4002");
			inMap.put("i_donor", a.getDonor());
			inMap.put("i_recipient", a.getRecipient());
			inMap.put("i_msisdn", a.getMSISDN());
			inMap.put("i_port_id", a.getPortId());
			inMap.put("i_route", a.getRoute());
			inMap.put("i_msg_create_timestamp", a.getActivationDate());

			Map<String, Object> callResult = call.execute(inMap);
			logger.debug("callResult=" + callResult);

			a.setDonor((String) callResult.get("o_donor"));
			a.setRecipient((String) callResult.get("o_recipient"));
			a.setRoute((String) callResult.get("o_route"));

		}

	}

}