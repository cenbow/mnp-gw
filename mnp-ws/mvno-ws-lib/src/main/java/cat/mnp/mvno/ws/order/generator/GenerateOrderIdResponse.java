/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.mvno.ws.order.generator;

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
@XmlType(name = "", propOrder = {
    "status",
    "orderId"
})
@XmlRootElement(name = "generateOrderIdResponse")
public class GenerateOrderIdResponse {

    public GenerateOrderIdResponse() {
    }

    public GenerateOrderIdResponse(String status, String orderId) {
        this.status = status;
        this.orderId = orderId;
    }
    
    @XmlElement
    protected String status;
    @XmlElement
    protected String orderId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "GenerateOrderIdResponse{" + "status=" + status + ", orderId=" + orderId + '}';
    }

}
