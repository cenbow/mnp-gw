/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cat.dealer.ibacss.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "BATCH_PACKAGE", schema = "CRMAPP")
public class BatchPackage implements Serializable {
    
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "PACKAGE_SEQ")
    @SequenceGenerator(name = "PACKAGE_SEQ", sequenceName = "CRMAPP.PACKAGE_SEQ")
    @Column(name = "PACKAGE_ID")
    private Long packageId;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "batchPackage", fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<BatchComponent> batchComponents = new HashSet();
    
    @Basic(optional = false)
    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    @Basic(optional = false)
    @Column(name = "LAST_UPDATE_BY")
    private String lastUpdateBy;
    
    @Basic(optional = false)
    @ManyToOne
    @JoinColumn(name = "SERVICE_ID")
    @JsonBackReference
    private BatchService batchService;
    
    @Basic(optional = false)
    @Column(name = "CAT_PKG_ID")
    private String catPkgId;
    @Basic(optional = false)
    @Column(name = "PACKAGE_NAME")
    private String packageName;
    @Basic(optional = false)
    @Column(name = "START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name = "SO_OFFER_ID")
    private Integer soOfferId;
    @Column(name = "PO_OFFER_ID")
    private Integer poOfferId;

    public Long getPackageId() {
        return packageId;
    }

    public void setPackageId(Long packageId) {
        this.packageId = packageId;
    }

    public Set<BatchComponent> getBatchComponents() {
        return batchComponents;
    }

    public void setBatchComponents(Set<BatchComponent> batchComponents) {
        this.batchComponents = batchComponents;
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

    public BatchService getBatchService() {
        return batchService;
    }

    public void setBatchService(BatchService batchService) {
        this.batchService = batchService;
    }

    public String getCatPkgId() {
        return catPkgId;
    }

    public void setCatPkgId(String catPkgId) {
        this.catPkgId = catPkgId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getSoOfferId() {
        return soOfferId;
    }

    public void setSoOfferId(Integer soOfferId) {
        this.soOfferId = soOfferId;
    }

    public Integer getPoOfferId() {
        return poOfferId;
    }

    public void setPoOfferId(Integer poOfferId) {
        this.poOfferId = poOfferId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (packageId != null ? packageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BatchPackage)) {
            return false;
        }
        BatchPackage other = (BatchPackage) object;
        if ((this.packageId == null && other.packageId != null) || (this.packageId != null && !this.packageId.equals(other.packageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BatchPackage(" + packageId + ")";
    }
    
}
