/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.dealer.ibacss.dao;

import cat.dealer.ibacss.domain.BatchComponent;
import cat.dealer.ibacss.domain.BatchPackage;
import cat.dealer.ibacss.domain.Inventory;
import cat.dealer.ibacss.domain.BatchService;
import cat.dealer.ibacss.domain.InventorySim;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author HP-CAT
 */
@Transactional(readOnly = true)
public class InventoryDao {

    private static final Logger logger = LoggerFactory.getLogger(InventoryDao.class);
    private SessionFactory sessionFactory;
    private String batchStatus;
    private String lastUpdateBy;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public String getBatchStatus() {
        return batchStatus;
    }

    public void setBatchStatus(String batchStatus) {
        this.batchStatus = batchStatus;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public Inventory get(String mdn) {
        logger.debug("Getting inventory: {}", mdn);
        Session session = sessionFactory.getCurrentSession();
        Inventory inventory = (Inventory) session.get(Inventory.class, mdn);
        logger.debug("Got {}", inventory);
        return inventory;
    }

    public InventorySim getSim(String iccid) {
        logger.debug("Getting inventory sim: {}", iccid);
        Session session = sessionFactory.getCurrentSession();
        InventorySim inventorySim = (InventorySim) session.get(InventorySim.class, iccid);
        logger.debug("Got {}", inventorySim);
        return inventorySim;
    }

    @Transactional(readOnly = false)
    public void reserve(BatchService service, Inventory inventory, InventorySim inventorySim) {
        Session session = sessionFactory.getCurrentSession();
        Date currentDate = new Date();

        service.setBatchStatus(batchStatus);
        service.setLastUpdateDate(currentDate);
        service.setLastUpdateBy(lastUpdateBy);
        service.setCreateDate(currentDate);
        service.setStartDate(currentDate);

        for (BatchPackage pkg : service.getBatchPackages()) {
            pkg.setLastUpdateDate(currentDate);
            pkg.setLastUpdateBy(lastUpdateBy);
            pkg.setStartDate(currentDate);
            pkg.setBatchService(service);

            for (BatchComponent comp : pkg.getBatchComponents()) {
                comp.setLastUpdateDate(currentDate);
                comp.setLastUpdateBy(lastUpdateBy);
                comp.setStartDate(currentDate);
                comp.setBatchService(service);
                comp.setBatchPackage(pkg);
            }
        }

        inventory.setStatus(Inventory.RESERVED_STATUS);
        inventory.setLastUpdateDate(currentDate);

        if (!service.isPrepaid()) {//postpaid
            inventory.setIccid(inventorySim.getIccid());
            inventory.setImsi(inventorySim.getImsi());

            inventorySim.setStatus(Inventory.RESERVED_STATUS);
            inventorySim.setLastUpdateDate(currentDate);

            session.update(inventorySim);
        }

        session.save(service);
        session.update(inventory);
    }

    @Transactional(readOnly = false)
    public void unreserve(BatchService service, Inventory inventory, InventorySim inventorySim) {
        Session session = sessionFactory.getCurrentSession();
        Date currentDate = new Date();

        inventory.setStatus(Inventory.AVAILABLE_STATUS);
        inventory.setLastUpdateDate(currentDate);
        
        if (!service.isPrepaid()) {//postpaid
            inventory.setIccid(null);
            inventory.setImsi(null);

            inventorySim.setStatus(Inventory.AVAILABLE_STATUS);
            inventorySim.setLastUpdateDate(currentDate);

            session.update(inventorySim);
        }

        session.delete(service);
        session.update(inventory);
    }
}
