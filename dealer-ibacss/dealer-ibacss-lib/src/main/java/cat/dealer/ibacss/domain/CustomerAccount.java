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
import javax.persistence.OneToOne;
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
@Table(name = "BATCH_CUSTOMER_ACCOUNT", schema = "CRMAPP")
public class CustomerAccount implements Serializable {
    
    public static final String INITIAL_STATUS = "1";
    public static final String SUBMITTED_STATUS = "2";
    public static final String CREATING_CA_STATUS = "4";
    public static final String INDIVIDUAL_CA_TYPE = "individual";
    
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "CUSTOMER_ACCOUNT_SEQ")
    @SequenceGenerator(name = "CUSTOMER_ACCOUNT_SEQ", sequenceName = "CRMAPP.CUSTOMER_ACCOUNT_SEQ")
    @Column(name = "CUSTOMER_ACCOUNT_ID")
    private Long customerAccountId;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "customerAccount", fetch = FetchType.EAGER)
    @JsonManagedReference
    private BillingAccount billingAccount;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerAccountId", fetch = FetchType.EAGER)
    private Set<BatchService> batchServices;

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
    @Column(name = "CUSTOMER_TYPE")
    private String customerType;
    @Column(name = "CAT_CARD_TYPE")
    private Integer catCardType;
    @Column(name = "CAT_CARD_NUMBER")
    private String catCardNumber;
    @Column(name = "CAT_CARD_ISSUED_BY")
    private String catCardIssuedBy;
    @Column(name = "CAT_CARD_ISSUED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date catCardIssuedDate;
    @Column(name = "CAT_TAX_REGISTER_NUM")
    private String catTaxRegisterNum;
    @Column(name = "BRANCH_ID")
    private String branchId;
    @Column(name = "CAT_THAI_TITLE")
    private Integer catThaiTitle;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "DATE_OF_BIRTH")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;
    @Column(name = "GENDER_LKP")
    private Integer genderLkp;
    @Column(name = "EMPLOYEE_ID")
    private String employeeId;
    @Column(name = "CAT_THAI_CORP_TYPE")
    private Integer catThaiCorpType;
    @Column(name = "FULL_NAME")
    private String fullName;
    @Column(name = "SHORT_NAME")
    private String shortName;
    @Column(name = "CAT_CUSTOMER_SEGMENT")
    private Integer catCustomerSegment;
    @Column(name = "CAT_CUSTOMER_GROUP")
    private String catCustomerGroup;
    @Column(name = "CAT_CUSTOMER_TYPE")
    private String catCustomerType;
    @Column(name = "CAT_CUSTOMER_FOCUS")
    private String catCustomerFocus;
    @Column(name = "ADDRESS_TYPE_LKP")
    private Integer addressTypeLkp;
    @Column(name = "CAT_HOUSE_NUMBER")
    private String catHouseNumber;
    @Column(name = "CAT_MOO")
    private String catMoo;
    @Column(name = "CAT_VILLAGE")
    private String catVillage;
    @Column(name = "CAT_MORE_INFO")
    private String catMoreInfo;
    @Column(name = "CAT_TROK_SOI")
    private String catTrokSoi;
    @Column(name = "CAT_ROAD")
    private String catRoad;
    @Column(name = "CAT_KWANG")
    private String catKwang;
    @Column(name = "CAT_KHET")
    private String catKhet;
    @Column(name = "CAT_PROVINCE")
    private String catProvince;
    @Column(name = "POSTAL_CODE")
    private String postalCode;
    @Column(name = "COUNTRY_CODE_ADDRESS")
    private String countryCodeAddress;
    @Column(name = "EMAIL_ADDRESS_TYPE")
    private String emailAddressType;
    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;
    @Column(name = "TELEPHONE_TYPE_LKP")
    private Integer telephoneTypeLkp;
    @Column(name = "COUNTRY_CODE_TELEPHONE")
    private String countryCodeTelephone;
    @Column(name = "AREA_CODE")
    private String areaCode;
    @Column(name = "TELEPHONE_NUMBER")
    private String telephoneNumber;
    @Column(name = "EXTENSION_CODE")
    private String extensionCode;
    @Basic(optional = false)
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Basic(optional = false)
    @Column(name = "CREATE_BY")
    private String createBy;
    @Column(name = "DEALER_CODE")
    private String dealerCode;
    @Column(name = "CRM_USER_ID")
    private byte[] crmUserId;
    @Column(name = "SAP_COST_CENTER")
    private String sapCostCenter;
    @Column(name = "CAT_SVC_TYPE_LKP")
    private Integer catSvcTypeLkp;

    public BillingAccount getBillingAccount() {
        return billingAccount;
    }

    public void setBillingAccount(BillingAccount billingAccount) {
        this.billingAccount = billingAccount;
    }

    public Set<BatchService> getBatchServices() {
        return batchServices;
    }

    public void setBatchServices(Set<BatchService> batchServices) {
        this.batchServices = batchServices;
    }

    public Long getCustomerAccountId() {
        return customerAccountId;
    }

    public void setCustomerAccountId(Long customerAccountId) {
        this.customerAccountId = customerAccountId;
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

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public Integer getCatCardType() {
        return catCardType;
    }

    public void setCatCardType(Integer catCardType) {
        this.catCardType = catCardType;
    }

    public String getCatCardNumber() {
        return catCardNumber;
    }

    public void setCatCardNumber(String catCardNumber) {
        this.catCardNumber = catCardNumber;
    }

    public String getCatCardIssuedBy() {
        return catCardIssuedBy;
    }

    public void setCatCardIssuedBy(String catCardIssuedBy) {
        this.catCardIssuedBy = catCardIssuedBy;
    }

    public Date getCatCardIssuedDate() {
        return catCardIssuedDate;
    }

    public void setCatCardIssuedDate(Date catCardIssuedDate) {
        this.catCardIssuedDate = catCardIssuedDate;
    }

    public String getCatTaxRegisterNum() {
        return catTaxRegisterNum;
    }

    public void setCatTaxRegisterNum(String catTaxRegisterNum) {
        this.catTaxRegisterNum = catTaxRegisterNum;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public Integer getCatThaiTitle() {
        return catThaiTitle;
    }

    public void setCatThaiTitle(Integer catThaiTitle) {
        this.catThaiTitle = catThaiTitle;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getGenderLkp() {
        return genderLkp;
    }

    public void setGenderLkp(Integer genderLkp) {
        this.genderLkp = genderLkp;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getCatThaiCorpType() {
        return catThaiCorpType;
    }

    public void setCatThaiCorpType(Integer catThaiCorpType) {
        this.catThaiCorpType = catThaiCorpType;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Integer getCatCustomerSegment() {
        return catCustomerSegment;
    }

    public void setCatCustomerSegment(Integer catCustomerSegment) {
        this.catCustomerSegment = catCustomerSegment;
    }

    public String getCatCustomerGroup() {
        return catCustomerGroup;
    }

    public void setCatCustomerGroup(String catCustomerGroup) {
        this.catCustomerGroup = catCustomerGroup;
    }

    public String getCatCustomerType() {
        return catCustomerType;
    }

    public void setCatCustomerType(String catCustomerType) {
        this.catCustomerType = catCustomerType;
    }

    public String getCatCustomerFocus() {
        return catCustomerFocus;
    }

    public void setCatCustomerFocus(String catCustomerFocus) {
        this.catCustomerFocus = catCustomerFocus;
    }

    public Integer getAddressTypeLkp() {
        return addressTypeLkp;
    }

    public void setAddressTypeLkp(Integer addressTypeLkp) {
        this.addressTypeLkp = addressTypeLkp;
    }

    public String getCatHouseNumber() {
        return catHouseNumber;
    }

    public void setCatHouseNumber(String catHouseNumber) {
        this.catHouseNumber = catHouseNumber;
    }

    public String getCatMoo() {
        return catMoo;
    }

    public void setCatMoo(String catMoo) {
        this.catMoo = catMoo;
    }

    public String getCatVillage() {
        return catVillage;
    }

    public void setCatVillage(String catVillage) {
        this.catVillage = catVillage;
    }

    public String getCatMoreInfo() {
        return catMoreInfo;
    }

    public void setCatMoreInfo(String catMoreInfo) {
        this.catMoreInfo = catMoreInfo;
    }

    public String getCatTrokSoi() {
        return catTrokSoi;
    }

    public void setCatTrokSoi(String catTrokSoi) {
        this.catTrokSoi = catTrokSoi;
    }

    public String getCatRoad() {
        return catRoad;
    }

    public void setCatRoad(String catRoad) {
        this.catRoad = catRoad;
    }

    public String getCatKwang() {
        return catKwang;
    }

    public void setCatKwang(String catKwang) {
        this.catKwang = catKwang;
    }

    public String getCatKhet() {
        return catKhet;
    }

    public void setCatKhet(String catKhet) {
        this.catKhet = catKhet;
    }

    public String getCatProvince() {
        return catProvince;
    }

    public void setCatProvince(String catProvince) {
        this.catProvince = catProvince;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountryCodeAddress() {
        return countryCodeAddress;
    }

    public void setCountryCodeAddress(String countryCodeAddress) {
        this.countryCodeAddress = countryCodeAddress;
    }

    public String getEmailAddressType() {
        return emailAddressType;
    }

    public void setEmailAddressType(String emailAddressType) {
        this.emailAddressType = emailAddressType;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Integer getTelephoneTypeLkp() {
        return telephoneTypeLkp;
    }

    public void setTelephoneTypeLkp(Integer telephoneTypeLkp) {
        this.telephoneTypeLkp = telephoneTypeLkp;
    }

    public String getCountryCodeTelephone() {
        return countryCodeTelephone;
    }

    public void setCountryCodeTelephone(String countryCodeTelephone) {
        this.countryCodeTelephone = countryCodeTelephone;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getExtensionCode() {
        return extensionCode;
    }

    public void setExtensionCode(String extensionCode) {
        this.extensionCode = extensionCode;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
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

    public Integer getCatSvcTypeLkp() {
        return catSvcTypeLkp;
    }

    public void setCatSvcTypeLkp(Integer catSvcTypeLkp) {
        this.catSvcTypeLkp = catSvcTypeLkp;
    }
    
    @JsonIgnore
    public boolean isIndividual() {
        return this.customerType.equals(INDIVIDUAL_CA_TYPE);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customerAccountId != null ? customerAccountId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerAccount)) {
            return false;
        }
        CustomerAccount other = (CustomerAccount) object;
        if ((this.customerAccountId == null && other.customerAccountId != null) || (this.customerAccountId != null && !this.customerAccountId.equals(other.customerAccountId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CustomerAccount(" + customerAccountId + ")";
    }

}
