package com.youkeda.dewu.model;

public enum OrderStatus {
    /**
     * 待付款
     */
    WAIT_BUYER_PAY,

    /**
     * 订单关闭
     */
    TRADE_CLOSED,

    /**
     * 支付成功
     */
    TRADE_PAID_SUCCESS,

    /**
     * 支付失败
     */
    TRADE_PAID_FAILED,
}
