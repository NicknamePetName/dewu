package com.youkeda.dewu.dataobject;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.youkeda.dewu.model.Order;
import com.youkeda.dewu.model.OrderStatus;
import com.youkeda.dewu.model.ProductDetail;
import com.youkeda.dewu.model.User;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

public class OrderDO implements Serializable {

    /**
     * 主键
     */
    private String id;

    /**
     * 订单编号
     */
    private String orderNumber;

    /**
     * 用户Id
     */
    private Long userId;

    /**
     * 用户模型
     */
    private User user;

    /**
     * 商品模型
     */
    private ProductDetail productDetail;

    /**
     * 商品Id
     */
    private String productId;

    /**
     * 订单总价格
     */
    private Double totalPrice;

    /**
     * 订单状态
     */
    private OrderStatus orderStatus;

    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreated;

    /**
     * 创建日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ProductDetail getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(ProductDetail productDetail) {
        this.productDetail = productDetail;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
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

    public OrderDO() {}

    public OrderDO(Order order) {
        BeanUtils.copyProperties(order, this);
    }

    public Order convertToModel() {
        Order order = new Order();
        BeanUtils.copyProperties(this, order);
        return order;
    }
}
