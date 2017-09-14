package cat.mnp.om.domain;

import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

import cat.mnp.om.domain.enumeration.OrderType;
import cat.mnp.om.domain.enumeration.OrderState;
import cat.mnp.om.domain.enumeration.OrderStatus;

/**
 * A MnpPiOrder.
 */
@Entity
@Table(name = "mnp_pi_order")
public class MnpPiOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(max = 16)
    @Column(name = "order_id", length = 16)
    private String orderId;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "order_type", nullable = false)
    private OrderType orderType;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_state")
    private OrderState orderState;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @Column(name = "created_date", nullable = false)
    private LocalDate createdDate;

    @Column(name = "submitted_date", nullable = false)
    private LocalDate submittedDate;

    @Column(name = "updated_date", nullable = false)
    private LocalDate updatedDate;

    @Size(max = 400)
    @Column(name = "remark", length = 400)
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(LocalDate submittedDate) {
        this.submittedDate = submittedDate;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MnpPiOrder mnpPiOrder = (MnpPiOrder) o;

        if ( ! Objects.equals(id, mnpPiOrder.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "MnpPiOrder{" +
            "id=" + id +
            ", orderId='" + orderId + "'" +
            ", orderType='" + orderType + "'" +
            ", orderState='" + orderState + "'" +
            ", orderStatus='" + orderStatus + "'" +
            ", createdDate='" + createdDate + "'" +
            ", submittedDate='" + submittedDate + "'" +
            ", updatedDate='" + updatedDate + "'" +
            ", remark='" + remark + "'" +
            '}';
    }
}
