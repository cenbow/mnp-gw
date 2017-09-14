package cat.mnp.sps.dao;

import cat.mnp.sps.hibernate.InternalPortHist;
import java.util.List;

public interface InternalPortHistDao {

    public void save(InternalPortHist internalPortHist) throws Exception;

    public void saveBatch(List<InternalPortHist> internalPortHistList) throws Exception;

}
