package cat.mnp.dealer.ws.portin;

import java.util.List;

public class CustomerInfo {

	 Integer msisdnCheckSum;
	 String  orderId;
	 List<MsisdnInfo> msisdnInfoList;
	public Integer getMsisdnCheckSum() {
		return msisdnCheckSum;
	}
	public void setMsisdnCheckSum(Integer msisdnCheckSum) {
		this.msisdnCheckSum = msisdnCheckSum;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public List<MsisdnInfo> getMsisdnInfoList() {
		return msisdnInfoList;
	}
	public void setMsisdnInfoList(List<MsisdnInfo> msisdnInfoList) {
		this.msisdnInfoList = msisdnInfoList;
	}



}
