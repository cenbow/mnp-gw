/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.dealer.ws.portin;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author CATr
 */
@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(name = "", propOrder = {"status", "orderId"})
//@XmlRootElement(name = "portInResponse")
public class PortInResponse {

	List<PortInStatus> portInStatusList;

	public List<PortInStatus> getPortInStatusList() {
		return portInStatusList;
	}

	public void setPortInStatusList(List<PortInStatus> portInStatusList) {
		this.portInStatusList = portInStatusList;
	}


}
