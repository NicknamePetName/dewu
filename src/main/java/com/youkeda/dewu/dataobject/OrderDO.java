package com.youkeda.dewu.dataobject;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.youkeda.dewu.model.Order;
import com.youkeda.dewu.model.OrderStatus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.Date;


public class OrderDO {
    /**
     * 主键id
     */

    private String id;

    /**
     * 订单编号
     */
    private String orderNumber;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 商品id
     */
    private String productDetailId;

    /**
     * 订单总价格
     */
    private Double totalPrice;

    /**
     * 订单状态
     */
    private String status;

    /**
     * 创建时间
     */
    private Date gmtCreated;

    /**
     * 修改时间
     */
    private Date gmtModified;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getProductDetailId() {
        return productDetailId;
    }

    public void setProductDetailId(String productDetailId) {
        this.productDetailId = productDetailId;
    }
    public OrderDO() {


    }
    public OrderDO(Order order) {
        BeanUtils.copyProperties(order, this);
        if (order.getStatus() != null) {
            this.setStatus(order.getStatus().toString());
        }

    }

    public Order convertToModel() {
        Order order = new Order();
        BeanUtils.copyProperties(this, order);
        if (!StringUtils.isEmpty(this.getStatus())) {
            order.setStatus(OrderStatus.valueOf(this.getStatus()));
        }

        return order;
    }
}
