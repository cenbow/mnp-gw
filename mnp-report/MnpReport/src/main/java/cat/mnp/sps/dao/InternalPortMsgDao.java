package cat.mnp.sps.dao;

import cat.mnp.om.hibernate.MnpPortComplete;
import java.util.Date;
import java.util.List;

public interface InternalPortMsgDao {
    
    public List<MnpPortComplete> listInternalPort(Date startDate, Date endDate) throws Exception;

    public List<MnpPortComplete> listByDate(Date date) throws Exception;

    public List<MnpPortComplete> listByMonth(Date date) throws Exception;
}
