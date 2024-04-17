package com.youkeda.dewu.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class PaymentRecord implements Serializable {
    /**
     * 主键 id
     */
    private String id;

    /**
     * 用户 id
     */
    private String userId;

    /**
     * 订单号
     */
    private String orderNumber;

    /**
     * 外部支付渠道主键 id
     */
    private String channelPaymentId;

    /**
     * 渠道类型
     */
    private String channelType;

    /**
     * 支付金额
     */
    private double amount;

    /**
     * 支付类型
     */
    private PayType payType;

    /**
     * 支付状态
     */
    private PaymentStatus paymentStatus;

    /**
     * 订单额外信息
     */
    private String extendStr;

    /**
     * 支付完成时间
     */
    private String payEndTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreated;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtModified;
}
