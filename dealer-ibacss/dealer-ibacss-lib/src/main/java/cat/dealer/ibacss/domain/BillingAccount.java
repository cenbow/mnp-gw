/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cat.dealer.ibacss.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "BATCH_BILLING_ACCOUNT", schema = "CRMAPP")
public class BillingAccount implements Serializable {
    
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "BILLING_ACCOUNT_SEQ")
    @SequenceGenerator(name = "BILLING_ACCOUNT_SEQ", sequenceName = "CRMAPP.BILLING_ACCOUNT_SEQ")
    @Column(name = "BILLING_ACCOUNT_ID")
    private Long billingAccountId;
    
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
    @OneToOne
    @JoinColumn(name = "CUSTOMER_ACCOUNT_ID")
    @JsonBackReference
    private CustomerAccount customerAccount;
    
    @Column(name = "ACCT_INTERNAL_ID")
    private String acctInternalId;
    @Basic(optional = false)
    @Column(name = "CAT_BILL_ACCT_NUMBER")
    private String catBillAcctNumber;
    @Column(name = "TAX_EXEMP_LKP")
    private Integer taxExempLkp;
    @Column(name = "ACCT_CAT_LKP")
    private Integer acctCatLkp;
    @Column(name = "CAT_BILL_DISP_METHOD_LKP")
    private Integer catBillDispMethodLkp;
    @Column(name = "CAT_VIP_CODE")
    private Integer catVipCode;
    @Column(name = "CAT_BILLABLE")
    private Integer catBillable;
    @Column(name = "CAT_REGION_CODE")
    private String catRegionCode;
    @Column(name = "CAT_BILL_INTERNAL_FLAG")
    private Integer catBillInternalFlag;
    @Column(name = "CAT_VAT_DISP_FLAG")
    private Integer catVatDispFlag;
    @Column(name = "BILLING_GROUP")
    private String billingGroup;
    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;
    @Column(name = "RATE_CLASS_LKP")
    private Integer rateClassLkp;
    @Column(name = "CREDIT_LIMIT")
    private Integer creditLimit;
    @Column(name = "CAT_BILL_LANG_LKP")
    private String catBillLangLkp;
    @Column(name = "CAT_GOVERNMENT_CODE")
    private String catGovernmentCode;
    @Column(name = "CAT_TAX_REGISTER_NUMBER")
    private String catTaxRegisterNumber;
    @Column(name = "BRANCH_ID")
    private String branchId;
    @Column(name = "CAT_RELATED_PARTY")
    private String catRelatedParty;
    @Column(name = "NACC_NUMBER")
    private String naccNumber;
    @Column(name = "EGP_NUMBER")
    private String egpNumber;
    @Column(name = "BILLDISPLAY")
    private String billdisplay;
    @Column(name = "SALUTATION_LKP")
    private Integer salutationLkp;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "CAT_BILL_COMP_SALUTATION_LKP")
    private Integer catBillCompSalutationLkp;
    @Column(name = "CAT_BILL_COMP_NAME")
    private String catBillCompName;
    @Column(name = "BILL_ADDR_LINE1")
    private String billAddrLine1;
    @Column(name = "BILL_ADDR_LINE2")
    private String billAddrLine2;
    @Column(name = "BILL_ADDR_LINE3")
    private String billAddrLine3;
    @Column(name = "BILL_ADDR_LINE4")
    private String billAddrLine4;
    @Column(name = "BILL_ADDR_KHET_AMPHUR")
    private String billAddrKhetAmphur;
    @Column(name = "BILL_ADDR_PROVINCE_LKP")
    private String billAddrProvinceLkp;
    @Column(name = "BILL_ADDR_POST_CODE")
    private String billAddrPostCode;
    @Column(name = "BILL_ADDR_COUNTRY_LKP")
    private String billAddrCountryLkp;
    @Column(name = "VAT_ADDR_SALUTATION")
    private Integer vatAddrSalutation;
    @Column(name = "VAT_FIRST_NAME")
    private String vatFirstName;
    @Column(name = "VAT_LAST_NAME")
    private String vatLastName;
    @Column(name = "CAT_VAT_COMP_SALUTATION_LKP")
    private Integer catVatCompSalutationLkp;
    @Column(name = "CAT_VAT_COMP_NAME")
    private String catVatCompName;
    @Column(name = "VAT_ADDR_LINE1")
    private String vatAddrLine1;
    @Column(name = "VAT_ADDR_LINE2")
    private String vatAddrLine2;
    @Column(name = "VAT_ADDR_LINE3")
    private String vatAddrLine3;
    @Column(name = "VAT_ADDR_LINE4")
    private String vatAddrLine4;
    @Column(name = "VAT_ADDR_KHET_AMPHUR")
    private String vatAddrKhetAmphur;
    @Column(name = "VAT_ADDR_PROVINCE_LKP")
    private String vatAddrProvinceLkp;
    @Column(name = "VAT_ADDR_POST_CODE")
    private String vatAddrPostCode;
    @Column(name = "VAT_ADDR_COUNTRY_LKP")
    private String vatAddrCountryLkp;
    @Column(name = "DELI_THAI_TITLE_LKP")
    private Integer deliThaiTitleLkp;
    @Column(name = "DELI_FIRST_NAME")
    private String deliFirstName;
    @Column(name = "DELI_LAST_NAME")
    private String deliLastName;
    @Column(name = "DELI_COMP_TITLE")
    private String deliCompTitle;
    @Column(name = "DELI_COMP_NAME")
    private String deliCompName;
    @Column(name = "DELI_ADDR_LINE1")
    private String deliAddrLine1;
    @Column(name = "DELI_ADDR_LINE2")
    private String deliAddrLine2;
    @Column(name = "DELI_ADDR_LINE3")
    private String deliAddrLine3;
    @Column(name = "DELI_ADDR_LINE4")
    private String deliAddrLine4;
    @Column(name = "DELI_ADDR_KHET_AMPHUR")
    private String deliAddrKhetAmphur;
    @Column(name = "DELI_ADDR_PROVINCE_LKP")
    private String deliAddrProvinceLkp;
    @Column(name = "DELI_ADDR_COUNTRY_LKP")
    private String deliAddrCountryLkp;
    @Column(name = "DELI_ADDR_POST_CODE")
    private String deliAddrPostCode;
    @Column(name = "DELI_VAT_THAI_TITLE_LKP")
    private Integer deliVatThaiTitleLkp;
    @Column(name = "DELI_VAT_FIRST_NAME")
    private String deliVatFirstName;
    @Column(name = "DELI_VAT_LAST_NAME")
    private String deliVatLastName;
    @Column(name = "DELI_VAT_COMP_TITLE")
    private String deliVatCompTitle;
    @Column(name = "DELI_VAT_COMP_NAME")
    private String deliVatCompName;
    @Column(name = "DELI_VAT_ADDR_LINE1")
    private String deliVatAddrLine1;
    @Column(name = "DELI_VAT_ADDR_LINE2")
    private String deliVatAddrLine2;
    @Column(name = "DELI_VAT_ADDR_LINE3")
    private String deliVatAddrLine3;
    @Column(name = "DELI_VAT_ADDR_LINE4")
    private String deliVatAddrLine4;
    @Column(name = "DELI_VAT_ADDR_KHET_AMPHUR")
    private String deliVatAddrKhetAmphur;
    @Column(name = "DELI_VAT_ADDR_PROVINCE_LKP")
    private String deliVatAddrProvinceLkp;
    @Column(name = "DELI_VAT_ADDR_POST_CODE")
    private String deliVatAddrPostCode;
    @Column(name = "DELI_VAT_ADDR_COUNTRY_LKP")
    private String deliVatAddrCountryLkp;
    @Basic(optional = false)
    @Column(name = "PAY_METHOD_LKP")
    private Integer payMethodLkp;
    @Basic(optional = false)
    @Column(name = "PAY_BILL_PERIOD_LKP")
    private String payBillPeriodLkp;
    @Basic(optional = false)
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Basic(optional = false)
    @Column(name = "CREATE_BY")
    private String createBy;

    public Long getBillingAccountId() {
        return billingAccountId;
    }

    public void setBillingAccountId(Long billingAccountId) {
        this.billingAccountId = billingAccountId;
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

    public CustomerAccount getCustomerAccount() {
        return customerAccount;
    }

    public void setCustomerAccount(CustomerAccount customerAccount) {
        this.customerAccount = customerAccount;
    }

    public String getAcctInternalId() {
        return acctInternalId;
    }

    public void setAcctInternalId(String acctInternalId) {
        this.acctInternalId = acctInternalId;
    }

    public String getCatBillAcctNumber() {
        return catBillAcctNumber;
    }

    public void setCatBillAcctNumber(String catBillAcctNumber) {
        this.catBillAcctNumber = catBillAcctNumber;
    }

    public Integer getTaxExempLkp() {
        return taxExempLkp;
    }

    public void setTaxExempLkp(Integer taxExempLkp) {
        this.taxExempLkp = taxExempLkp;
    }

    public Integer getAcctCatLkp() {
        return acctCatLkp;
    }

    public void setAcctCatLkp(Integer acctCatLkp) {
        this.acctCatLkp = acctCatLkp;
    }

    public Integer getCatBillDispMethodLkp() {
        return catBillDispMethodLkp;
    }

    public void setCatBillDispMethodLkp(Integer catBillDispMethodLkp) {
        this.catBillDispMethodLkp = catBillDispMethodLkp;
    }

    public Integer getCatVipCode() {
        return catVipCode;
    }

    public void setCatVipCode(Integer catVipCode) {
        this.catVipCode = catVipCode;
    }

    public Integer getCatBillable() {
        return catBillable;
    }

    public void setCatBillable(Integer catBillable) {
        this.catBillable = catBillable;
    }

    public String getCatRegionCode() {
        return catRegionCode;
    }

    public void setCatRegionCode(String catRegionCode) {
        this.catRegionCode = catRegionCode;
    }

    public Integer getCatBillInternalFlag() {
        return catBillInternalFlag;
    }

    public void setCatBillInternalFlag(Integer catBillInternalFlag) {
        this.catBillInternalFlag = catBillInternalFlag;
    }

    public Integer getCatVatDispFlag() {
        return catVatDispFlag;
    }

    public void setCatVatDispFlag(Integer catVatDispFlag) {
        this.catVatDispFlag = catVatDispFlag;
    }

    public String getBillingGroup() {
        return billingGroup;
    }

    public void setBillingGroup(String billingGroup) {
        this.billingGroup = billingGroup;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Integer getRateClassLkp() {
        return rateClassLkp;
    }

    public void setRateClassLkp(Integer rateClassLkp) {
        this.rateClassLkp = rateClassLkp;
    }

    public Integer getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Integer creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getCatBillLangLkp() {
        return catBillLangLkp;
    }

    public void setCatBillLangLkp(String catBillLangLkp) {
        this.catBillLangLkp = catBillLangLkp;
    }

    public String getCatGovernmentCode() {
        return catGovernmentCode;
    }

    public void setCatGovernmentCode(String catGovernmentCode) {
        this.catGovernmentCode = catGovernmentCode;
    }

    public String getCatTaxRegisterNumber() {
        return catTaxRegisterNumber;
    }

    public void setCatTaxRegisterNumber(String catTaxRegisterNumber) {
        this.catTaxRegisterNumber = catTaxRegisterNumber;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getCatRelatedParty() {
        return catRelatedParty;
    }

    public void setCatRelatedParty(String catRelatedParty) {
        this.catRelatedParty = catRelatedParty;
    }

    public String getNaccNumber() {
        return naccNumber;
    }

    public void setNaccNumber(String naccNumber) {
        this.naccNumber = naccNumber;
    }

    public String getEgpNumber() {
        return egpNumber;
    }

    public void setEgpNumber(String egpNumber) {
        this.egpNumber = egpNumber;
    }

    public String getBilldisplay() {
        return billdisplay;
    }

    public void setBilldisplay(String billdisplay) {
        this.billdisplay = billdisplay;
    }

    public Integer getSalutationLkp() {
        return salutationLkp;
    }

    public void setSalutationLkp(Integer salutationLkp) {
        this.salutationLkp = salutationLkp;
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

    public Integer getCatBillCompSalutationLkp() {
        return catBillCompSalutationLkp;
    }

    public void setCatBillCompSalutationLkp(Integer catBillCompSalutationLkp) {
        this.catBillCompSalutationLkp = catBillCompSalutationLkp;
    }

    public String getCatBillCompName() {
        return catBillCompName;
    }

    public void setCatBillCompName(String catBillCompName) {
        this.catBillCompName = catBillCompName;
    }

    public String getBillAddrLine1() {
        return billAddrLine1;
    }

    public void setBillAddrLine1(String billAddrLine1) {
        this.billAddrLine1 = billAddrLine1;
    }

    public String getBillAddrLine2() {
        return billAddrLine2;
    }

    public void setBillAddrLine2(String billAddrLine2) {
        this.billAddrLine2 = billAddrLine2;
    }

    public String getBillAddrLine3() {
        return billAddrLine3;
    }

    public void setBillAddrLine3(String billAddrLine3) {
        this.billAddrLine3 = billAddrLine3;
    }

    public String getBillAddrLine4() {
        return billAddrLine4;
    }

    public void setBillAddrLine4(String billAddrLine4) {
        this.billAddrLine4 = billAddrLine4;
    }

    public String getBillAddrKhetAmphur() {
        return billAddrKhetAmphur;
    }

    public void setBillAddrKhetAmphur(String billAddrKhetAmphur) {
        this.billAddrKhetAmphur = billAddrKhetAmphur;
    }

    public String getBillAddrProvinceLkp() {
        return billAddrProvinceLkp;
    }

    public void setBillAddrProvinceLkp(String billAddrProvinceLkp) {
        this.billAddrProvinceLkp = billAddrProvinceLkp;
    }

    public String getBillAddrPostCode() {
        return billAddrPostCode;
    }

    public void setBillAddrPostCode(String billAddrPostCode) {
        this.billAddrPostCode = billAddrPostCode;
    }

    public String getBillAddrCountryLkp() {
        return billAddrCountryLkp;
    }

    public void setBillAddrCountryLkp(String billAddrCountryLkp) {
        this.billAddrCountryLkp = billAddrCountryLkp;
    }

    public Integer getVatAddrSalutation() {
        return vatAddrSalutation;
    }

    public void setVatAddrSalutation(Integer vatAddrSalutation) {
        this.vatAddrSalutation = vatAddrSalutation;
    }

    public String getVatFirstName() {
        return vatFirstName;
    }

    public void setVatFirstName(String vatFirstName) {
        this.vatFirstName = vatFirstName;
    }

    public String getVatLastName() {
        return vatLastName;
    }

    public void setVatLastName(String vatLastName) {
        this.vatLastName = vatLastName;
    }

    public Integer getCatVatCompSalutationLkp() {
        return catVatCompSalutationLkp;
    }

    public void setCatVatCompSalutationLkp(Integer catVatCompSalutationLkp) {
        this.catVatCompSalutationLkp = catVatCompSalutationLkp;
    }

    public String getCatVatCompName() {
        return catVatCompName;
    }

    public void setCatVatCompName(String catVatCompName) {
        this.catVatCompName = catVatCompName;
    }

    public String getVatAddrLine1() {
        return vatAddrLine1;
    }

    public void setVatAddrLine1(String vatAddrLine1) {
        this.vatAddrLine1 = vatAddrLine1;
    }

    public String getVatAddrLine2() {
        return vatAddrLine2;
    }

    public void setVatAddrLine2(String vatAddrLine2) {
        this.vatAddrLine2 = vatAddrLine2;
    }

    public String getVatAddrLine3() {
        return vatAddrLine3;
    }

    public void setVatAddrLine3(String vatAddrLine3) {
        this.vatAddrLine3 = vatAddrLine3;
    }

    public String getVatAddrLine4() {
        return vatAddrLine4;
    }

    public void setVatAddrLine4(String vatAddrLine4) {
        this.vatAddrLine4 = vatAddrLine4;
    }

    public String getVatAddrKhetAmphur() {
        return vatAddrKhetAmphur;
    }

    public void setVatAddrKhetAmphur(String vatAddrKhetAmphur) {
        this.vatAddrKhetAmphur = vatAddrKhetAmphur;
    }

    public String getVatAddrProvinceLkp() {
        return vatAddrProvinceLkp;
    }

    public void setVatAddrProvinceLkp(String vatAddrProvinceLkp) {
        this.vatAddrProvinceLkp = vatAddrProvinceLkp;
    }

    public String getVatAddrPostCode() {
        return vatAddrPostCode;
    }

    public void setVatAddrPostCode(String vatAddrPostCode) {
        this.vatAddrPostCode = vatAddrPostCode;
    }

    public String getVatAddrCountryLkp() {
        return vatAddrCountryLkp;
    }

    public void setVatAddrCountryLkp(String vatAddrCountryLkp) {
        this.vatAddrCountryLkp = vatAddrCountryLkp;
    }

    public Integer getDeliThaiTitleLkp() {
        return deliThaiTitleLkp;
    }

    public void setDeliThaiTitleLkp(Integer deliThaiTitleLkp) {
        this.deliThaiTitleLkp = deliThaiTitleLkp;
    }

    public String getDeliFirstName() {
        return deliFirstName;
    }

    public void setDeliFirstName(String deliFirstName) {
        this.deliFirstName = deliFirstName;
    }

    public String getDeliLastName() {
        return deliLastName;
    }

    public void setDeliLastName(String deliLastName) {
        this.deliLastName = deliLastName;
    }

    public String getDeliCompTitle() {
        return deliCompTitle;
    }

    public void setDeliCompTitle(String deliCompTitle) {
        this.deliCompTitle = deliCompTitle;
    }

    public String getDeliCompName() {
        return deliCompName;
    }

    public void setDeliCompName(String deliCompName) {
        this.deliCompName = deliCompName;
    }

    public String getDeliAddrLine1() {
        return deliAddrLine1;
    }

    public void setDeliAddrLine1(String deliAddrLine1) {
        this.deliAddrLine1 = deliAddrLine1;
    }

    public String getDeliAddrLine2() {
        return deliAddrLine2;
    }

    public void setDeliAddrLine2(String deliAddrLine2) {
        this.deliAddrLine2 = deliAddrLine2;
    }

    public String getDeliAddrLine3() {
        return deliAddrLine3;
    }

    public void setDeliAddrLine3(String deliAddrLine3) {
        this.deliAddrLine3 = deliAddrLine3;
    }

    public String getDeliAddrLine4() {
        return deliAddrLine4;
    }

    public void setDeliAddrLine4(String deliAddrLine4) {
        this.deliAddrLine4 = deliAddrLine4;
    }

    public String getDeliAddrKhetAmphur() {
        return deliAddrKhetAmphur;
    }

    public void setDeliAddrKhetAmphur(String deliAddrKhetAmphur) {
        this.deliAddrKhetAmphur = deliAddrKhetAmphur;
    }

    public String getDeliAddrProvinceLkp() {
        return deliAddrProvinceLkp;
    }

    public void setDeliAddrProvinceLkp(String deliAddrProvinceLkp) {
        this.deliAddrProvinceLkp = deliAddrProvinceLkp;
    }

    public String getDeliAddrCountryLkp() {
        return deliAddrCountryLkp;
    }

    public void setDeliAddrCountryLkp(String deliAddrCountryLkp) {
        this.deliAddrCountryLkp = deliAddrCountryLkp;
    }

    public String getDeliAddrPostCode() {
        return deliAddrPostCode;
    }

    public void setDeliAddrPostCode(String deliAddrPostCode) {
        this.deliAddrPostCode = deliAddrPostCode;
    }

    public Integer getDeliVatThaiTitleLkp() {
        return deliVatThaiTitleLkp;
    }

    public void setDeliVatThaiTitleLkp(Integer deliVatThaiTitleLkp) {
        this.deliVatThaiTitleLkp = deliVatThaiTitleLkp;
    }

    public String getDeliVatFirstName() {
        return deliVatFirstName;
    }

    public void setDeliVatFirstName(String deliVatFirstName) {
        this.deliVatFirstName = deliVatFirstName;
    }

    public String getDeliVatLastName() {
        return deliVatLastName;
    }

    public void setDeliVatLastName(String deliVatLastName) {
        this.deliVatLastName = deliVatLastName;
    }

    public String getDeliVatCompTitle() {
        return deliVatCompTitle;
    }

    public void setDeliVatCompTitle(String deliVatCompTitle) {
        this.deliVatCompTitle = deliVatCompTitle;
    }

    public String getDeliVatCompName() {
        return deliVatCompName;
    }

    public void setDeliVatCompName(String deliVatCompName) {
        this.deliVatCompName = deliVatCompName;
    }

    public String getDeliVatAddrLine1() {
        return deliVatAddrLine1;
    }

    public void setDeliVatAddrLine1(String deliVatAddrLine1) {
        this.deliVatAddrLine1 = deliVatAddrLine1;
    }

    public String getDeliVatAddrLine2() {
        return deliVatAddrLine2;
    }

    public void setDeliVatAddrLine2(String deliVatAddrLine2) {
        this.deliVatAddrLine2 = deliVatAddrLine2;
    }

    public String getDeliVatAddrLine3() {
        return deliVatAddrLine3;
    }

    public void setDeliVatAddrLine3(String deliVatAddrLine3) {
        this.deliVatAddrLine3 = deliVatAddrLine3;
    }

    public String getDeliVatAddrLine4() {
        return deliVatAddrLine4;
    }

    public void setDeliVatAddrLine4(String deliVatAddrLine4) {
        this.deliVatAddrLine4 = deliVatAddrLine4;
    }

    public String getDeliVatAddrKhetAmphur() {
        return deliVatAddrKhetAmphur;
    }

    public void setDeliVatAddrKhetAmphur(String deliVatAddrKhetAmphur) {
        this.deliVatAddrKhetAmphur = deliVatAddrKhetAmphur;
    }

    public String getDeliVatAddrProvinceLkp() {
        return deliVatAddrProvinceLkp;
    }

    public void setDeliVatAddrProvinceLkp(String deliVatAddrProvinceLkp) {
        this.deliVatAddrProvinceLkp = deliVatAddrProvinceLkp;
    }

    public String getDeliVatAddrPostCode() {
        return deliVatAddrPostCode;
    }

    public void setDeliVatAddrPostCode(String deliVatAddrPostCode) {
        this.deliVatAddrPostCode = deliVatAddrPostCode;
    }

    public String getDeliVatAddrCountryLkp() {
        return deliVatAddrCountryLkp;
    }

    public void setDeliVatAddrCountryLkp(String deliVatAddrCountryLkp) {
        this.deliVatAddrCountryLkp = deliVatAddrCountryLkp;
    }

    public Integer getPayMethodLkp() {
        return payMethodLkp;
    }

    public void setPayMethodLkp(Integer payMethodLkp) {
        this.payMethodLkp = payMethodLkp;
    }

    public String getPayBillPeriodLkp() {
        return payBillPeriodLkp;
    }

    public void setPayBillPeriodLkp(String payBillPeriodLkp) {
        this.payBillPeriodLkp = payBillPeriodLkp;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (billingAccountId != null ? billingAccountId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BillingAccount)) {
            return false;
        }
        BillingAccount other = (BillingAccount) object;
        if ((this.billingAccountId == null && other.billingAccountId != null) || (this.billingAccountId != null && !this.billingAccountId.equals(other.billingAccountId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BillingAccount(" + billingAccountId + ")";
    }
    
}
