package com.youkeda.dewu.dao;

import com.youkeda.dewu.dataobject.PaymentRecordDO;
import com.youkeda.dewu.param.PaymentRecordQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PaymentRecordDAO {
    int insert(PaymentRecordDO paymentRecordDO);

    List<PaymentRecordDO> select(PaymentRecordQueryParam paymentRecordQueryParam);

    int update(PaymentRecordDO paymentRecordDO);
}
