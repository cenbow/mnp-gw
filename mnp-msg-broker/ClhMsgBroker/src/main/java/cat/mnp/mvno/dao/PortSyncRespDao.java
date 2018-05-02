package cat.mnp.mvno.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import jaxb.clh.npcbulksync.ActivatedNumberType;
import jaxb.clh.npcbulksync.NPCData;

public class PortSyncRespDao extends JdbcDaoSupport {
	private static final Logger logger = LoggerFactory.getLogger(PortSyncRespDao.class);
	public void insert(NPCData npcData) {

		logger.info("npcData: MessageName={}, IDNumber={}, NumberOfMessages={}", npcData.getMessageName(), npcData.getIDNumber(), npcData.getNumberOfMessages());
		Long parentId = getJdbcTemplate().queryForObject(" select mnpdb.mnp_port_sync_resp_seq.nextval from dual", Long.class);

		String sql = "insert into mnpdb.mnp_port_sync_resp ( id, messagename, idnumber, numberofmessages) " + "values ( ?, ?, ?, ? )";
		getJdbcTemplate().update(sql, new Object[]{parentId, npcData.getMessageName(), npcData.getIDNumber(), npcData.getNumberOfMessages()});

		List dtlList = new ArrayList();
		int i =0;
		for (ActivatedNumberType a : npcData.getActivatedNumbers().getActivatedNumber()) {
			logger.debug((++i)+". Action={}, PortId={}, MSISDN={}, Donor={}, Recipient={}, ActivationDate={}", a.getAction(), a.getPortId(), a.getMSISDN(), a.getDonor(), a.getRecipient(),
					a.getActivationDate());
			dtlList.add( new Object[]{parentId,  a.getAction(), a.getPortId(), a.getMSISDN(), a.getDonor(), a.getRecipient(), a.getNumberHolderInd(), a.getRoute(), a.getActivationDate()});

		}
		String dSql = "insert into mnpdb.mnp_port_sync_resp_dtl ( p_id,id, action, portid, msisdn, donor, recipient, numberholderind, route, activationdate) " + "values (?, mnpdb.mnp_port_sync_resp_dtl_seq.nextval, ?, ?, ?, ?, ?, ?,?,? )";
		getJdbcTemplate().batchUpdate(dSql,dtlList);

	}
}