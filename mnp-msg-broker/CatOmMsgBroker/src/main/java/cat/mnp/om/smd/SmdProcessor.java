/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.om.smd;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cat.mnp.mq.core.MsgHandlerBase;
import cat.sd.domain.OfferInfo;
import cat.sd.domain.SubscriberInfoRequest;
import cat.sd.domain.SubscriberInfoResponse;
import cat.sd.domain.api.SubscriberInfoInterface;

/**
 *
 * @author HP-CAT
 */
public class SmdProcessor extends MsgHandlerBase {

	private static final Logger logger = LoggerFactory.getLogger(SmdProcessor.class);

	private SmdDao smdDao;
	private SubscriberInfoInterface smdWebService;

	// FIXME: re-check again, smart device update pakage
	public void processMsg(javax.jms.Message aqMsg) throws Exception {
		String orderId = aqMsg.getStringProperty("OrderId");
		String msisdn = aqMsg.getStringProperty("Msisdn");
		String orderSeq = aqMsg.getStringProperty("OrderSeq");
		String serviceSeq = aqMsg.getStringProperty("ServiceSeq");

		// call smd ws
		SubscriberInfoRequest subInfo = new SubscriberInfoRequest();
		subInfo.setLanguageCode(1);
		subInfo.setMsisdn(msisdn);
		SubscriberInfoResponse info = smdWebService.getInfo(subInfo);
		// Date pkgStartDate = info.getActiveDate().toGregorianCalendar().getTime();
		// Date pkgEndDate = info.getServiceInactiveDate().toGregorianCalendar().getTime();
		List<OfferInfo> offerList = info.getOfferInfoList();
		logger.info("OfferInfoList size =" + offerList.size());
		for (int i = 0; i < offerList.size(); i++) {
			OfferInfo offerInfo = offerList.get(0);
			Integer id1 = offerInfo.isIsPrimary() ? offerInfo.getId() : null;
			Integer id2 = offerInfo.isIsPrimary() ? null : offerInfo.getId();
			Date pkgStartDate = offerInfo.isIsPrimary() ? toDate(offerInfo.getActiveDate()) : null;
			Date pkgEndDate = offerInfo.isIsPrimary() ? toDate(offerInfo.getInactiveDate()) : null;
			Map m = new LinkedHashMap<>();
			m.put("i_order_seq", orderSeq);
			m.put("i_srv_seq", serviceSeq);
			m.put("i_package_id1", id1);
			m.put("i_package_id2", id2);
			m.put("i_package_start_dtm", pkgStartDate);
			m.put("i_package_end_dtm", pkgEndDate);
			smdDao.smartUpdatePkg(m);
		}
	}

	private Date toDate(XMLGregorianCalendar c) {
		Date r = null;
		if (c != null) {
			r = c.toGregorianCalendar().getTime();
		}
		return r;
	}

	public SmdDao getSmdDao() {
		return smdDao;
	}

	public void setSmdDao(SmdDao smdDao) {
		this.smdDao = smdDao;
	}

	public SubscriberInfoInterface getSmdWebService() {
		return smdWebService;
	}

	public void setSmdWebService(SubscriberInfoInterface smdWebService) {
		this.smdWebService = smdWebService;
	}

}
