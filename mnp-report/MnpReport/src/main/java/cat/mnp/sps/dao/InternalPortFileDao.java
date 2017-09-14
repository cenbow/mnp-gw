package cat.mnp.sps.dao;

import cat.mnp.sps.hibernate.InternalPortHist;
import java.io.File;
import java.util.List;

public interface InternalPortFileDao {

    public List<InternalPortHist> listAll() throws Exception;

    public List<InternalPortHist> read(String filename) throws Exception;

    public List<InternalPortHist> read(File file) throws Exception;

    public void backup() throws Exception;

}
