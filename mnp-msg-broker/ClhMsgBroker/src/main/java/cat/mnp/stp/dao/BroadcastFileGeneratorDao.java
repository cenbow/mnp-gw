/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.stp.dao;

import cat.mnp.mvno.dao.MvnoMsgDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.type.StandardBasicTypes;

/**
 *
 * @author HP-CAT
 */
public class BroadcastFileGeneratorDao extends MvnoMsgDao {

    private static final Logger logger = LoggerFactory.getLogger(BroadcastFileGeneratorDao.class);
    private String getFileIdSqlQuery;

    public String getGetFileIdSqlQuery() {
        return getFileIdSqlQuery;
    }

    public void setGetFileIdSqlQuery(String getFileIdSqlQuery) {
        this.getFileIdSqlQuery = getFileIdSqlQuery;
    }

    public long getFileId() throws Exception {
        long fileId = (long) getSessionFactory().getCurrentSession().createSQLQuery(getFileIdSqlQuery).addScalar("nextval", StandardBasicTypes.LONG).uniqueResult();
        logger.info("FileId: {}", fileId);
        return fileId;
    }
}