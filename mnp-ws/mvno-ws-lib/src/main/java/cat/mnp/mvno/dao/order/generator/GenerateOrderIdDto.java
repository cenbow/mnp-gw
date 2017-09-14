/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.mvno.dao.order.generator;

import cat.mnp.mvno.ws.order.generator.OrderType;

/**
 *
 * @author anuchitr
 */
public class GenerateOrderIdDto {
    
    private OrderType orderType;
    private String mvnoName;

    public GenerateOrderIdDto() {
    }

    public GenerateOrderIdDto(OrderType orderType, String mvnoName) {
        this.orderType = orderType;
        this.mvnoName = mvnoName;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public String getMvnoName() {
        return mvnoName;
    }

    public void setMvnoName(String mvnoName) {
        this.mvnoName = mvnoName;
    }

    @Override
    public String toString() {
        return "GenerateOrderIdDto{" + "orderType=" + orderType + ", mvnoName=" + mvnoName + '}';
    }
}
