package cat.mnp.om.hibernate;

public class MnpPortCompleteId implements java.io.Serializable {

    private String portId;
    private Integer msgId;

    public MnpPortCompleteId() {
    }

    public MnpPortCompleteId(String portId, Integer msgId) {
        this.portId = portId;
        this.msgId = msgId;
    }

    public Integer getMsgId() {
        return msgId;
    }

    public void setMsgId(Integer msgId) {
        this.msgId = msgId;
    }

    public String getPortId() {
        return portId;
    }

    public void setPortId(String portId) {
        this.portId = portId;
    }

    @Override
    public boolean equals(Object other) {
        if ((this == other)) {
            return true;
        }
        if ((other == null)) {
            return false;
        }
        if (!(other instanceof MnpPortCompleteId)) {
            return false;
        }
        MnpPortCompleteId castOther = (MnpPortCompleteId) other;

        return ((this.getPortId() == null ? castOther.getPortId() == null : this.getPortId().equals(castOther.getPortId())) || (this.getPortId() != null && castOther.getPortId() != null && this.getPortId().equals(castOther.getPortId())))
                && ((this.getMsgId() == null ? castOther.getMsgId() == null : this.getMsgId().equals(castOther.getMsgId())) || (this.getMsgId() != null && castOther.getMsgId() != null && this.getMsgId().equals(castOther.getMsgId())));
    }

    @Override
    public int hashCode() {
        int result = 3;

        result = 37 * result + (getPortId() == null ? 0 : this.getPortId().hashCode());
        result = 37 * result + (getMsgId() == null ? 0 : this.getMsgId().hashCode());

        return result;
    }

    @Override
    public String toString() {
        return String.format("%s(%s, %s)", this.getClass().getName(), this.getMsgId(), this.getPortId());
    }
}
