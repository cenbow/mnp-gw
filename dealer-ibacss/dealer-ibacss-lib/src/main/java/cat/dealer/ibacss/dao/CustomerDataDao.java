/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.dealer.ibacss.dao;

import cat.dealer.ibacss.domain.BatchComponent;
import cat.dealer.ibacss.domain.BatchPackage;
import cat.dealer.ibacss.domain.BatchService;
import cat.dealer.ibacss.domain.BillingAccount;
import cat.dealer.ibacss.domain.CustomerAccount;
import com.google.common.base.Strings;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.hibernate.type.StandardBasicTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author HP-CAT
 */
@Transactional(readOnly = true)
public class CustomerDataDao {

    private static final Logger logger = LoggerFactory.getLogger(CustomerDataDao.class);
    private SessionFactory sessionFactory;
    private String batchType;
    private String lastUpdateBy;
    private String listSqlQuery;
    private int listRowLimit;
    private String[] dateFormatPatterns;
    private String[] directMapKey;
    private String[] likeMapKey;
    private String[] inMapKey;
    private String refOrderIdSeqQuery;
    private String catBillAcctNumberSeqQuery;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public String getBatchType() {
        return batchType;
    }

    public void setBatchType(String batchType) {
        this.batchType = batchType;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public String getListSqlQuery() {
        return listSqlQuery;
    }

    public void setListSqlQuery(String listSqlQuery) {
        this.listSqlQuery = listSqlQuery;
    }

    public int getListRowLimit() {
        return listRowLimit;
    }

    public void setListRowLimit(int listRowLimit) {
        this.listRowLimit = listRowLimit;
    }

    public String[] getDateFormatPatterns() {
        return dateFormatPatterns;
    }

    public void setDateFormatPatterns(String[] dateFormatPatterns) {
        this.dateFormatPatterns = dateFormatPatterns;
    }

    public String[] getDirectMapKey() {
        return directMapKey;
    }

    public void setDirectMapKey(String[] directMapKey) {
        this.directMapKey = directMapKey;
    }

    public String[] getLikeMapKey() {
        return likeMapKey;
    }

    public void setLikeMapKey(String[] likeMapKey) {
        this.likeMapKey = likeMapKey;
    }

    public String[] getInMapKey() {
        return inMapKey;
    }

    public void setInMapKey(String[] inMapKey) {
        this.inMapKey = inMapKey;
    }

    public String getRefOrderIdSeqQuery() {
        return refOrderIdSeqQuery;
    }

    public void setRefOrderIdSeqQuery(String refOrderIdSeqQuery) {
        this.refOrderIdSeqQuery = refOrderIdSeqQuery;
    }

    public String getCatBillAcctNumberSeqQuery() {
        return catBillAcctNumberSeqQuery;
    }

    public void setCatBillAcctNumberSeqQuery(String catBillAcctNumberSeqQuery) {
        this.catBillAcctNumberSeqQuery = catBillAcctNumberSeqQuery;
    }

    private void setChildProperties(CustomerAccount ca) {
        BillingAccount ba = ca.getBillingAccount();

        if (ba != null) {
            ba.setBatchStatus(ca.getBatchStatus());
            ba.setCreateBy(ca.getCreateBy());
            ba.setLastUpdateBy(lastUpdateBy);
            ba.setLastUpdateDate(ca.getLastUpdateDate());
        }

        for (BatchService svc : ca.getBatchServices()) {
            svc.setBatchStatus(ca.getBatchStatus());
            svc.setDealerCode(ca.getDealerCode());
            svc.setLastUpdateBy(lastUpdateBy);
            svc.setLastUpdateDate(ca.getLastUpdateDate());
            svc.setCrmUserId(ca.getCrmUserId());
            svc.setSapCostCenter(ca.getSapCostCenter());

            for (BatchPackage pkg : svc.getBatchPackages()) {
                pkg.setBatchService(svc);

                for (BatchComponent comp : pkg.getBatchComponents()) {
                    comp.setBatchService(svc);
                    comp.setBatchPackage(pkg);
                }
            }
        }
    }

    public CustomerAccount get(Long customerAccountId) {
        Session session = sessionFactory.getCurrentSession();

        CustomerAccount ca = (CustomerAccount) session.get(CustomerAccount.class, customerAccountId);
        return ca;
    }

    @Transactional(readOnly = false)
    public void updateService(BatchService svc) {
        Session session = sessionFactory.getCurrentSession();

        svc.setLastUpdateDate(new Date());
        svc.setLastUpdateBy(lastUpdateBy);
        session.update(svc);
    }

    @Transactional(readOnly = false)
    public void update(CustomerAccount ca) {
        Session session = sessionFactory.getCurrentSession();

        if (!ca.getCatSvcTypeLkp().equals(110) && ca.getBillingAccount() != null) {//not postpaid and has ba
            session.delete(ca.getBillingAccount());
            ca.setBillingAccount(null);
            for (BatchService batchService : ca.getBatchServices()) {
                batchService.setBillingAccountId(null);
            }
        }
        ca.setLastUpdateDate(new Date());
        ca.setLastUpdateBy(lastUpdateBy);
        setChildProperties(ca);

        session.update(ca);
    }

    @Transactional(readOnly = false)
    public void save(CustomerAccount ca) {
        Session session = sessionFactory.getCurrentSession();

        if (ca.getReferenceOrderId() == null) {
            String refOrderId = (String) session.createSQLQuery(refOrderIdSeqQuery).addScalar("REFERENCE_ORDER_ID", StandardBasicTypes.STRING).uniqueResult();
            ca.setReferenceOrderId(refOrderId);
        }

        Date currentDate = new Date();
        if (ca.getCreateDate() == null) {
            ca.setCreateDate(currentDate);
            ca.setCountryCodeAddress("TH");
            ca.setCountryCodeTelephone("66");
        }
        ca.setLastUpdateDate(currentDate);
        ca.setLastUpdateBy(lastUpdateBy);
        ca.setBatchType(batchType);

        BillingAccount ba = ca.getBillingAccount();
        if (ba != null) {
            ba.setCustomerAccount(ca);
            if (ba.getCreateDate() == null) {
                ba.setCreateDate(currentDate);

                ba.setBillAddrCountryLkp("TH");
                ba.setVatAddrCountryLkp("TH");
                ba.setTaxExempLkp(1);
                ba.setCatBillDispMethodLkp(1);
                ba.setCatVipCode(0);
                ba.setCatBillable(1);
                ba.setCatBillInternalFlag(1);
                ba.setCatVatDispFlag(1);
                ba.setBillingGroup("1101");
                ba.setRateClassLkp(1000);
                ba.setCatBillLangLkp("TH");
                ba.setPayMethodLkp(1);
                ba.setPayBillPeriodLkp("M01");
            }
            if (ba.getCatBillAcctNumber() == null) {
                Long catBillAcctNumber = (Long) session.createSQLQuery(catBillAcctNumberSeqQuery).addScalar("CAT_BILL_ACCT_NUMBER", StandardBasicTypes.LONG).uniqueResult();
                ba.setCatBillAcctNumber(catBillAcctNumber.toString());
            }
        }

        setChildProperties(ca);

        if (ca.getCustomerType().equals("individual")) {
            ca.setCatThaiCorpType(null);
            ca.setFullName(null);
            ca.setShortName(null);
            ca.setCatCustomerSegment(3);
            ca.setCatCustomerGroup("11");
            ca.setAddressTypeLkp(4);
            if (ba != null) {
                ba.setAcctCatLkp(3);
            }
        } else {
            ca.setCatThaiTitle(null);
            ca.setFirstName(null);
            ca.setLastName(null);
            ca.setCatCustomerSegment(1);
            ca.setCatCustomerGroup("16");
            ca.setAddressTypeLkp(1);
            if (ba != null) {
                ba.setAcctCatLkp(1);
            }
        }

        logger.debug("Saving ca: {}", ca.getFirstName());
        session.saveOrUpdate(ca);

        logger.debug("ca id: {}", ca.getCustomerAccountId());
        if (ba != null) {
            logger.debug("ba id: {}", ba.getBillingAccountId());
        }
    }

    @Transactional(readOnly = true)
    public List<Map<String, Object>> list(HashMap<String, Object> parameters) {
        HashMap<String, Object> convertedParameters = new HashMap();
        for (Map.Entry<String, Object> p : parameters.entrySet()) {
            convertedParameters.put(p.getKey().replaceFirst("__", "."), p.getValue());
        }
        logger.debug("Listing: {}", convertedParameters);
        Session session = sessionFactory.getCurrentSession();
        Query q = session.getNamedQuery(listSqlQuery);

        String queryString = buildListQuery(q.getQueryString(), convertedParameters);
        q = session.createSQLQuery(queryString);
        if (!convertedParameters.isEmpty()) {
            q.setProperties(convertedParameters);
        }
        
        for (String key : inMapKey) {
            List valueList = (List) convertedParameters.get(key);
            if (valueList != null) {
                q.setParameterList(key, valueList);
            }
        }
        
        q.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);

        q.setMaxResults(listRowLimit + 1);
        List<Map<String, Object>> result = q.list();
        return result;
    }

    private String buildListQuery(String queryString, HashMap<String, Object> parameters) {
        ArrayList<String> queryParameters = new ArrayList();

        for (String key : directMapKey) {
            if (Strings.emptyToNull((String) parameters.get(key)) != null) {
                queryParameters.add(String.format("%s = :%s", key, key));
            }
        }

        for (String key : likeMapKey) {
            if (Strings.emptyToNull((String) parameters.get(key)) != null) {
                queryParameters.add(String.format("%s like :%s", key, key));
            }
        }

        for (String key : inMapKey) {
            List valueList = (List) parameters.get(key);
            if (valueList != null) {
                queryParameters.add(String.format("%s in (:%s)", key, key));
            }
        }
        
        String startCreateDate = (String) parameters.get("startCreateDate");
        if (Strings.emptyToNull(startCreateDate) != null) {
            try {
                parameters.put("startCreateDate", DateUtils.parseDate(startCreateDate.replaceAll("Z$", "+0000"), dateFormatPatterns));
                queryParameters.add("ca.CREATE_DATE > :startCreateDate");
            } catch (ParseException ex) {
                logger.warn("Error parsing startCreateDate: " + startCreateDate, ex);
            }
        }
        String endCreateDate = (String) parameters.get("endCreateDate");
        if (Strings.emptyToNull(endCreateDate) != null) {
            try {
                parameters.put("endCreateDate", DateUtils.parseDate(endCreateDate.replaceAll("Z$", "+0000"), dateFormatPatterns));
                queryParameters.add("ca.CREATE_DATE < :endCreateDate + 1");
            } catch (ParseException ex) {
                logger.warn("Error parsing endCreateDate: " + endCreateDate, ex);
            }
        }

        String parametersString;
        if (!queryParameters.isEmpty()) {
            parametersString = " and " + StringUtils.join(queryParameters, " and ");
        } else {
            parametersString = "";
        }

        queryString = queryString.replaceAll(":parameters", parametersString);
        return queryString;
    }

}
