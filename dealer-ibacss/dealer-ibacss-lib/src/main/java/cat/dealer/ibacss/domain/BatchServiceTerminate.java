/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cat.dealer.ibacss.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author HP-CAT
 */
@Entity
@Table(name = "BATCH_SERVICE_TERMINATE", schema = "CRMAPP")
public class BatchServiceTerminate implements Serializable {
    
    @Id
    @Basic(optional = false)
    @Column(name = "SERVICE_TERMINATE_ID")
    private Long serviceTerminateId;
    @Basic(optional = false)
    @Column(name = "BATCH_TYPE")
    private String batchType;
    @Basic(optional = false)
    @Column(name = "BATCH_STATUS")
    private String batchStatus;
    @Column(name = "BATCH_ERROR_MESSAGE")
    private String batchErrorMessage;
    @Basic(optional = false)
    @Column(name = "REFERENCE_ORDER_ID")
    private String referenceOrderId;
    @Basic(optional = false)
    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    @Basic(optional = false)
    @Column(name = "LAST_UPDATE_BY")
    private String lastUpdateBy;
    @Column(name = "CAT_SVC_TYPE_LKP")
    private Integer catSvcTypeLkp;
    @Basic(optional = false)
    @Column(name = "PROPERTY_ONE")
    private String propertyOne;
    @Basic(optional = false)
    @Column(name = "SUBSCR_NO")
    private String subscrNo;
    @Column(name = "END_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Column(name = "USER_ID")
    private String userId;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "RECIPIENT_OPERATOR")
    private String recipientOperator;
    @Column(name = "ORDER_SERVICE_ITEM_ID")
    private String orderServiceItemId;
    @Basic(optional = false)
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    public Long getServiceTerminateId() {
        return serviceTerminateId;
    }

    public void setServiceTerminateId(Long serviceTerminateId) {
        this.serviceTerminateId = serviceTerminateId;
    }

    public String getBatchType() {
        return batchType;
    }

    public void setBatchType(String batchType) {
        this.batchType = batchType;
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

    public String getReferenceOrderId() {
        return referenceOrderId;
    }

    public void setReferenceOrderId(String referenceOrderId) {
        this.referenceOrderId = referenceOrderId;
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

    public String getSubscrNo() {
        return subscrNo;
    }

    public void setSubscrNo(String subscrNo) {
        this.subscrNo = subscrNo;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    public String getRecipientOperator() {
        return recipientOperator;
    }

    public void setRecipientOperator(String recipientOperator) {
        this.recipientOperator = recipientOperator;
    }

    public String getOrderServiceItemId() {
        return orderServiceItemId;
    }

    public void setOrderServiceItemId(String orderServiceItemId) {
        this.orderServiceItemId = orderServiceItemId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serviceTerminateId != null ? serviceTerminateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BatchServiceTerminate)) {
            return false;
        }
        BatchServiceTerminate other = (BatchServiceTerminate) object;
        if ((this.serviceTerminateId == null && other.serviceTerminateId != null) || (this.serviceTerminateId != null && !this.serviceTerminateId.equals(other.serviceTerminateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BatchServiceTerminate(" + serviceTerminateId + ")";
    }
    
}
