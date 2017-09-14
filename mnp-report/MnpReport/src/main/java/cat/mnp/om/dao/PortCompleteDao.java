package cat.mnp.om.dao;

import cat.mnp.om.hibernate.MnpPortComplete;
import java.util.List;

public interface PortCompleteDao {

    public MnpPortComplete create(String portId, String msisdn) throws Exception;
    
    public List<MnpPortComplete> listAll() throws Exception;

    public int save(MnpPortComplete mnpPortComplete) throws Exception;

    public int saveBatch(List<MnpPortComplete> mnpPortCompleteList) throws Exception;

    public void updateMvnoName(MnpPortComplete mnpPortComplete) throws Exception;

}
