package com.youkeda.dewu.model;

public enum PaymentStatus {
    /**
     * 支付中
     */
    PENDING,

    /**
     * 支付成功
     */
    SUCCESS,

    /**
     * 支付失败
     */
    FAILURE,

    /**
     * 退款成功
     */
    REFUND_SUCCESS,

    /**
     * 退款失败
     */
    REFUND_FAILED,
}
