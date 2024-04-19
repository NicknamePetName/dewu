package com.youkeda.dewu.service;

import com.youkeda.dewu.model.PaymentRecord;
import com.youkeda.dewu.param.PaymentParam;
import com.youkeda.dewu.param.PaymentRecordQueryParam;

import java.util.List;

public interface PaymentRecordService {
    /**
     * 添加或修改支付记录
     *
     * @param paymentRecord 支付所需参数
     * @return PaymentRecord
     */
    PaymentRecord save(PaymentRecord paymentRecord);

    /**
     * 查询支付记录
     *
     * @param paymentRecordQueryParam 查询参数
     * @return List<PaymentRecord>
     */
    List<PaymentRecord> query(PaymentRecordQueryParam paymentRecordQueryParam);

    /**
     * 更新支付记录状态
     * @param paymentRecord
     * @return
     */
    PaymentRecord updateStatus(PaymentRecord paymentRecord);
}
