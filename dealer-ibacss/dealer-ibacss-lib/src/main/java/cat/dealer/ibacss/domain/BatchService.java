/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cat.dealer.ibacss.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author HP-CAT
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "BATCH_SERVICE", schema = "CRMAPP")
public class BatchService implements Serializable {
    
    public static final Integer PREPAID_SVC_TYPE = 150;
    
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SERVICE_SEQ")
    @SequenceGenerator(name = "SERVICE_SEQ", sequenceName = "CRMAPP.SERVICE_SEQ")
    @Column(name = "SERVICE_ID")
    private Long serviceId;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "batchService", fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<BatchPackage> batchPackages = new HashSet();
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "batchService", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<BatchComponent> batchComponents = new HashSet();
    
    @Basic(optional = false)
    @Column(name = "BATCH_STATUS")
    private String batchStatus;
    @Column(name = "BATCH_ERROR_MESSAGE")
    private String batchErrorMessage;
    @Basic(optional = false)
    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    @Basic(optional = false)
    @Column(name = "LAST_UPDATE_BY")
    private String lastUpdateBy;
    @Basic(optional = false)
    @Column(name = "CUSTOMER_ACCOUNT_ID")
    private Long customerAccountId;
    @Column(name = "BILLING_ACCOUNT_ID")
    private Long billingAccountId;
    @Basic(optional = false)
    @Column(name = "CAT_SVC_TYPE_LKP")
    private Integer catSvcTypeLkp;
    @Basic(optional = false)
    @Column(name = "PROPERTY_ONE")
    private String propertyOne;
    @Basic(optional = false)
    @Column(name = "PROPERTY_TWO")
    private String propertyTwo;
    @Basic(optional = false)
    @Column(name = "START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name = "USER_ID")
    private String userId;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "CRM_USER_ID")
    private byte[] crmUserId;
    @Column(name = "SAP_COST_CENTER")
    private String sapCostCenter;
    @Column(name = "DONOR_OPERATOR")
    private String donorOperator;
    @Basic(optional = false)
    @Column(name = "IMSI")
    private String imsi;
    @Basic(optional = false)
    @Column(name = "DEALER_CODE")
    private String dealerCode;
    @Basic(optional = false)
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Set<BatchPackage> getBatchPackages() {
        return batchPackages;
    }

    public void setBatchPackages(Set<BatchPackage> batchPackages) {
        this.batchPackages = batchPackages;
    }

    public String getBatchStatus() {
        return batchStatus;
    }

    public void setBatchStatus(String batchStatus) {
        this.batchStatus = batchStatus;
    }

    public String getBatchErrorMessage() {
        return batchErrorMessage;
    }

    public void setBatchErrorMessage(String batchErrorMessage) {
        this.batchErrorMessage = batchErrorMessage;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public Long getCustomerAccountId() {
        return customerAccountId;
    }

    public void setCustomerAccountId(Long customerAccountId) {
        this.customerAccountId = customerAccountId;
    }

    public Long getBillingAccountId() {
        return billingAccountId;
    }

    public void setBillingAccountId(Long billingAccountId) {
        this.billingAccountId = billingAccountId;
    }

    public Integer getCatSvcTypeLkp() {
        return catSvcTypeLkp;
    }

    public void setCatSvcTypeLkp(Integer catSvcTypeLkp) {
        this.catSvcTypeLkp = catSvcTypeLkp;
    }

    public String getPropertyOne() {
        return propertyOne;
    }

    public void setPropertyOne(String propertyOne) {
        this.propertyOne = propertyOne;
    }

    public String getPropertyTwo() {
        return propertyTwo;
    }

    public void setPropertyTwo(String propertyTwo) {
        this.propertyTwo = propertyTwo;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public byte[] getCrmUserId() {
        return crmUserId;
    }

    public void setCrmUserId(byte[] crmUserId) {
        this.crmUserId = crmUserId;
    }

    public String getSapCostCenter() {
        return sapCostCenter;
    }

    public void setSapCostCenter(String sapCostCenter) {
        this.sapCostCenter = sapCostCenter;
    }

    public String getDonorOperator() {
        return donorOperator;
    }

    public void setDonorOperator(String donorOperator) {
        this.donorOperator = donorOperator;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    @JsonIgnore
    public boolean isPrepaid() {
        return this.catSvcTypeLkp.equals(PREPAID_SVC_TYPE);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serviceId != null ? serviceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BatchService)) {
            return false;
        }
        BatchService other = (BatchService) object;
        if ((this.serviceId == null && other.serviceId != null) || (this.serviceId != null && !this.serviceId.equals(other.serviceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BatchService(" + serviceId + ")";
    }
    
}
