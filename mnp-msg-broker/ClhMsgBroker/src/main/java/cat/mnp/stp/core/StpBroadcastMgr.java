/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.stp.core;

import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.type.StandardBasicTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;

import cat.mnp.mq.core.MsgHandlerBase;

/**
 *
 * @author HP-CAT
 */
public class StpBroadcastMgr extends MsgHandlerBase {

	private static final Logger logger = LoggerFactory.getLogger(StpBroadcastMgr.class);
	private Map stpBroadcastMsgHandlerMap;
	private SessionFactory sessionFactory;

	@Override
	public void processMsg(List<Message> msgList) throws Exception {
		logger.info("select handler " + stpBroadcastMsgHandlerMap);
		String sql = "select cat_mnp_inf_gw.check_order_type_donor ( '1122' ) orderType from dual "; // FIXME: correct procedure
		logger.warn("getType: " + sql);
		String type = (String) sessionFactory.getCurrentSession().createSQLQuery(sql).addScalar("orderType", StandardBasicTypes.STRING).uniqueResult();
		MsgHandlerBase msgHandler = (MsgHandlerBase) stpBroadcastMsgHandlerMap.get("file");
		msgHandler.processMsg(msgList);
	}

	public void setStpBroadcastMsgHandlerMap(Map stpBroadcastMsgHandlerMap) {
		this.stpBroadcastMsgHandlerMap = stpBroadcastMsgHandlerMap;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
