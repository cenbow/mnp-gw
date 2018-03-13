/*
 * To change this license header choose License Headers in Project Properties.
 * To change this template file choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.dealer.ws.portin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author CATr
 */
@XmlAccessorType(XmlAccessType.FIELD)
// @XmlType(name = "", propOrder = {"userId", "password", "customerInfo"})
// @XmlRootElement(name = "portInRequest")
public class PortInRequest {

	@XmlElement(required = true)
	protected String userId;
	@XmlElement(required = true)
	protected String password;
	@XmlElement(required = true)
	protected CustomerInfo customerInfo;

	String cust_type;
	String userlogin; // user

	// รายละเอียดลูกค้า
	String titlename; // คำนำหน้าชือ่
	String fname; // ชื่อ
	String lname; // นามสกุล
	String titlecompname; // คำนำหน้าชื่อบริษัท
	String compname; // ชื่อบริษัท
	String birthday; // วันเกิด
	String gender; // เพศ
	String language; // ภาษา
	String doctype; // ประเภทเอกสารอ้างอิง
	String docnum; // เลขที่เอกสารอ้างอิง
	String acctcategory; // กลุ่มลูกค้า
	String email; // E-mail
	String telno; // หมายเลขโทรศัพท์ติดต่อ
	String vatcode; // อัตราภาษีมูลค่าเพิ่ม
	// String collunit in ; //หน่วยงานที่จัดเก็บและติดตามหนี้
	// ที่อยู่ที่จัดส่งใบแจ้งหนี้
	String biltitlename; // คำนำหน้าชือ่
	String bilfname; // ชื่อ
	String billname; // นามสกุล
	String biltitlecompname; // คำนำหน้าชื่อบริษัท
	String bilcompname; // ชื่อบริษัท
	String bilhousenum; // บ้านเลขที่
	String bilmoo; // หมู่ที่
	String bilvillage; // หมู่บ้าน/อาคาร/ชั้น/ห้อง/ตรอก/ซอย
	String bilroad; // ถนน
	String biltumbol; // แขวง/ตำบล
	String bilamphur; // อำเภอ/เขต
	String bilprovince; // จังหวัด
	String bilpostcode; // รหัสไปรษณีย์
	// Vat Address
	String vattitlename; // คำนำหน้าชือ่
	String vatfname; // ชื่อ
	String vatlname; // นามสกุล
	String vattitlecompname; // คำนำหน้าชื่อบริษัท
	String vatcompname; // ชื่อบริษัท
	String vathousenum; // บ้านเลขที่
	String vatmoo; // หมู่ที่
	String vatvillage; // หมู่บ้าน/อาคาร/ชั้น/ห้อง/ตรอก/ซอย
	String vatroad; // ถนน
	String vattumbol; // แขวง/ตำบล
	String vatamphur; // อำเภอ/เขต
	String vatprovince; // จังหวัด
	String vatpostcode; // รหัสไปรษณีย์
	String commercialid;
	String employeeid;
	String channel_id;
	String customer_remark;
	String customer_type;
	String priority;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}
	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}
	public String getCust_type() {
		return cust_type;
	}
	public void setCust_type(String cust_type) {
		this.cust_type = cust_type;
	}

	public String getUserlogin() {
		return userlogin;
	}
	public void setUserlogin(String userlogin) {
		this.userlogin = userlogin;
	}
	public String getTitlename() {
		return titlename;
	}
	public void setTitlename(String titlename) {
		this.titlename = titlename;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getTitlecompname() {
		return titlecompname;
	}
	public void setTitlecompname(String titlecompname) {
		this.titlecompname = titlecompname;
	}
	public String getCompname() {
		return compname;
	}
	public void setCompname(String compname) {
		this.compname = compname;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getDoctype() {
		return doctype;
	}
	public void setDoctype(String doctype) {
		this.doctype = doctype;
	}
	public String getDocnum() {
		return docnum;
	}
	public void setDocnum(String docnum) {
		this.docnum = docnum;
	}
	public String getAcctcategory() {
		return acctcategory;
	}
	public void setAcctcategory(String acctcategory) {
		this.acctcategory = acctcategory;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelno() {
		return telno;
	}
	public void setTelno(String telno) {
		this.telno = telno;
	}
	public String getVatcode() {
		return vatcode;
	}
	public void setVatcode(String vatcode) {
		this.vatcode = vatcode;
	}
	public String getBiltitlename() {
		return biltitlename;
	}
	public void setBiltitlename(String biltitlename) {
		this.biltitlename = biltitlename;
	}
	public String getBilfname() {
		return bilfname;
	}
	public void setBilfname(String bilfname) {
		this.bilfname = bilfname;
	}
	public String getBillname() {
		return billname;
	}
	public void setBillname(String billname) {
		this.billname = billname;
	}
	public String getBiltitlecompname() {
		return biltitlecompname;
	}
	public void setBiltitlecompname(String biltitlecompname) {
		this.biltitlecompname = biltitlecompname;
	}
	public String getBilcompname() {
		return bilcompname;
	}
	public void setBilcompname(String bilcompname) {
		this.bilcompname = bilcompname;
	}
	public String getBilhousenum() {
		return bilhousenum;
	}
	public void setBilhousenum(String bilhousenum) {
		this.bilhousenum = bilhousenum;
	}
	public String getBilmoo() {
		return bilmoo;
	}
	public void setBilmoo(String bilmoo) {
		this.bilmoo = bilmoo;
	}
	public String getBilvillage() {
		return bilvillage;
	}
	public void setBilvillage(String bilvillage) {
		this.bilvillage = bilvillage;
	}
	public String getBilroad() {
		return bilroad;
	}
	public void setBilroad(String bilroad) {
		this.bilroad = bilroad;
	}
	public String getBiltumbol() {
		return biltumbol;
	}
	public void setBiltumbol(String biltumbol) {
		this.biltumbol = biltumbol;
	}
	public String getBilamphur() {
		return bilamphur;
	}
	public void setBilamphur(String bilamphur) {
		this.bilamphur = bilamphur;
	}
	public String getBilprovince() {
		return bilprovince;
	}
	public void setBilprovince(String bilprovince) {
		this.bilprovince = bilprovince;
	}
	public String getBilpostcode() {
		return bilpostcode;
	}
	public void setBilpostcode(String bilpostcode) {
		this.bilpostcode = bilpostcode;
	}
	public String getVattitlename() {
		return vattitlename;
	}
	public void setVattitlename(String vattitlename) {
		this.vattitlename = vattitlename;
	}
	public String getVatfname() {
		return vatfname;
	}
	public void setVatfname(String vatfname) {
		this.vatfname = vatfname;
	}
	public String getVatlname() {
		return vatlname;
	}
	public void setVatlname(String vatlname) {
		this.vatlname = vatlname;
	}
	public String getVattitlecompname() {
		return vattitlecompname;
	}
	public void setVattitlecompname(String vattitlecompname) {
		this.vattitlecompname = vattitlecompname;
	}
	public String getVatcompname() {
		return vatcompname;
	}
	public void setVatcompname(String vatcompname) {
		this.vatcompname = vatcompname;
	}
	public String getVathousenum() {
		return vathousenum;
	}
	public void setVathousenum(String vathousenum) {
		this.vathousenum = vathousenum;
	}
	public String getVatmoo() {
		return vatmoo;
	}
	public void setVatmoo(String vatmoo) {
		this.vatmoo = vatmoo;
	}
	public String getVatvillage() {
		return vatvillage;
	}
	public void setVatvillage(String vatvillage) {
		this.vatvillage = vatvillage;
	}
	public String getVatroad() {
		return vatroad;
	}
	public void setVatroad(String vatroad) {
		this.vatroad = vatroad;
	}
	public String getVattumbol() {
		return vattumbol;
	}
	public void setVattumbol(String vattumbol) {
		this.vattumbol = vattumbol;
	}
	public String getVatamphur() {
		return vatamphur;
	}
	public void setVatamphur(String vatamphur) {
		this.vatamphur = vatamphur;
	}
	public String getVatprovince() {
		return vatprovince;
	}
	public void setVatprovince(String vatprovince) {
		this.vatprovince = vatprovince;
	}
	public String getVatpostcode() {
		return vatpostcode;
	}
	public void setVatpostcode(String vatpostcode) {
		this.vatpostcode = vatpostcode;
	}
	public String getCommercialid() {
		return commercialid;
	}
	public void setCommercialid(String commercialid) {
		this.commercialid = commercialid;
	}
	public String getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}
	public String getChannel_id() {
		return channel_id;
	}
	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}
	public String getCustomer_remark() {
		return customer_remark;
	}
	public void setCustomer_remark(String customer_remark) {
		this.customer_remark = customer_remark;
	}
	public String getCustomer_type() {
		return customer_type;
	}
	public void setCustomer_type(String customer_type) {
		this.customer_type = customer_type;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}

}
