/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.clh.domain;

import java.io.Serializable;
import java.sql.Clob;

/**
 *
 * @author HP-CAT
 */
public class MnpPortReqXml implements Serializable {

    private String orderId;
    private Clob xml;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Clob getXml() {
        return xml;
    }

    public void setXml(Clob xml) {
        this.xml = xml;
    }

    @Override
    public String toString() {
        return String.format("MnpPortReqXml(%s)", getOrderId());
    }
}
