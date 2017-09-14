package cat.mnp.clh.dao;

import cat.mnp.om.hibernate.MnpPortComplete;
import java.util.Date;
import java.util.List;

public interface BroadcastMsgDao {

    public MnpPortComplete get(String portId, String msisdn) throws Exception;
    
    public List listMsg(Date startDate, Date endDate) throws Exception;

    public List listMsgByDate(Date date) throws Exception;

    public List listMsgByMonth(Date date) throws Exception;
}
