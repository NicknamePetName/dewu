package com.youkeda.dewu.service;

import com.youkeda.dewu.model.Result;
import com.youkeda.dewu.param.PaymentParam;
import org.springframework.beans.factory.annotation.Autowired;

public interface PayService {
    /**
     * 支付订单
     *
     * @param paymentParam 支付所需参数
     * @return Result
     */
    Result payOrder(PaymentParam paymentParam);
}
