/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cat.dealer.ibacss.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "BATCH_COMPONENT", schema = "CRMAPP")
public class BatchComponent implements Serializable {
    
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "COMPONENT_SEQ")
    @SequenceGenerator(name = "COMPONENT_SEQ", sequenceName = "CRMAPP.COMPONENT_SEQ")
    @Column(name = "COMPONENT_ID")
    private Long componentId;
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
    @JsonIgnore
    private BatchService batchService;
    
    @Basic(optional = false)
    @ManyToOne
    @JoinColumn(name = "PACKAGE_ID")
    @JsonBackReference
    private BatchPackage batchPackage;
    
    @Basic(optional = false)
    @Column(name = "CAT_COMP_ID")
    private String catCompId;
    @Basic(optional = false)
    @Column(name = "COMPONENT_NAME")
    private String componentName;
    @Basic(optional = false)
    @Column(name = "START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    public Long getComponentId() {
        return componentId;
    }

    public void setComponentId(Long componentId) {
        this.componentId = componentId;
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

    public void setBatchService(BatchService batchService) {
        this.batchService = batchService;
    }

    public BatchPackage getBatchPackage() {
        return batchPackage;
    }

    public void setBatchPackage(BatchPackage batchPackage) {
        this.batchPackage = batchPackage;
    }

    public String getCatCompId() {
        return catCompId;
    }

    public void setCatCompId(String catCompId) {
        this.catCompId = catCompId;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (componentId != null ? componentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BatchComponent)) {
            return false;
        }
        BatchComponent other = (BatchComponent) object;
        if ((this.componentId == null && other.componentId != null) || (this.componentId != null && !this.componentId.equals(other.componentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BatchComponent(" + componentId + ")";
    }
    
}
