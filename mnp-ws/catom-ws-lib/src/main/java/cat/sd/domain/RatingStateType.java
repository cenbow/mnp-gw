
package cat.sd.domain;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RatingStateType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="RatingStateType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Idle"/&gt;
 *     &lt;enumeration value="Active"/&gt;
 *     &lt;enumeration value="AwaitActivation"/&gt;
 *     &lt;enumeration value="Await1stRecharge"/&gt;
 *     &lt;enumeration value="FraudLockout"/&gt;
 *     &lt;enumeration value="SuspendL1"/&gt;
 *     &lt;enumeration value="SuspendL2"/&gt;
 *     &lt;enumeration value="Deactivate"/&gt;
 *     &lt;enumeration value="Terminated"/&gt;
 *     &lt;enumeration value="DisconnectRequest"/&gt;
 *     &lt;enumeration value="Disconnected"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "RatingStateType")
@XmlEnum
public enum RatingStateType {

    @XmlEnumValue("Idle")
    IDLE("Idle"),
    @XmlEnumValue("Active")
    ACTIVE("Active"),
    @XmlEnumValue("AwaitActivation")
    AWAIT_ACTIVATION("AwaitActivation"),
    @XmlEnumValue("Await1stRecharge")
    AWAIT_1_ST_RECHARGE("Await1stRecharge"),
    @XmlEnumValue("FraudLockout")
    FRAUD_LOCKOUT("FraudLockout"),
    @XmlEnumValue("SuspendL1")
    SUSPEND_L_1("SuspendL1"),
    @XmlEnumValue("SuspendL2")
    SUSPEND_L_2("SuspendL2"),
    @XmlEnumValue("Deactivate")
    DEACTIVATE("Deactivate"),
    @XmlEnumValue("Terminated")
    TERMINATED("Terminated"),
    @XmlEnumValue("DisconnectRequest")
    DISCONNECT_REQUEST("DisconnectRequest"),
    @XmlEnumValue("Disconnected")
    DISCONNECTED("Disconnected");
    private final String value;

    RatingStateType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static RatingStateType fromValue(String v) {
        for (RatingStateType c: RatingStateType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
