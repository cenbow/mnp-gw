/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.pincode.ws.service;

import com.sicap.ussdgw.ProcessUMBReqInput;
import com.sicap.ussdgw.ProcessUMBReqOutput;
import com.sicap.ussdgw.SoapGatewayPortType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author HP-CAT
 */
public class SicapService implements SoapGatewayPortType {

    private static final Logger logger = LoggerFactory.getLogger(SicapService.class);

    @Override
    public ProcessUMBReqOutput processUMBReqArr(ProcessUMBReqInput req) {
        logger.debug("Req: {}", req);
        ProcessUMBReqOutput resp = new ProcessUMBReqOutput();
        resp.setMsgCode("0");
//        UmbTrans umbTrans = new UmbTrans();
//        umbTrans.setId(req.getTranxId());
//        umbTrans.setReqDate(new Date());
//        try {
//            //extract starCode and item
//            Matcher m = UssdUtil.USSD_PATTERN.matcher(req.getInpF01());
//            if (!m.find()) {//wrong pattern
//                logger.error("Wrong UssdPattern detected: ", req.getInpF01());
//                //TODO: map return code here
//                return resp;
//            }
//
//            //get offerId from starCode and item
//            String starCode = m.group(UssdUtil.STAR_CODE);
//            String itemId = m.group(UssdUtil.ITEM_ID);
//
//            umbTrans.setReqStarCode(starCode);
//            umbTrans.setReqItemId(itemId);
//
//        } catch (Exception ex) {
//            logger.error(String.format("Error: cpurl %s, tranxId %s, msisdn %s, inpF01 %s", req.getCpurl(), req.getTranxId(), req.getMsisdn(), req.getInpF01()), ex);
////            umbTransService.mapReturnCodeAndSaveTrans(OfferReturnCode.INTERNAL_ERROR, umbTrans, req.getMsisdn(), resp);
//        }
        return resp;
    }

}
