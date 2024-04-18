package com.youkeda.dewu.service.impl;

import com.youkeda.dewu.dao.PaymentRecordDAO;
import com.youkeda.dewu.dataobject.PaymentRecordDO;
import com.youkeda.dewu.model.PaymentRecord;
import com.youkeda.dewu.param.PaymentRecordQueryParam;
import com.youkeda.dewu.service.PaymentRecordService;
import com.youkeda.dewu.util.UUIDUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentRecordServiceImpl implements PaymentRecordService {

    @Autowired
    private PaymentRecordDAO paymentRecordDAO;

    @Override
    public PaymentRecord save(PaymentRecord paymentRecord) {
        if (paymentRecord == null) {
            return null;
        }

        if (StringUtils.isBlank(paymentRecord.getId())) {
            PaymentRecordDO paymentRecordDO = new PaymentRecordDO(paymentRecord);
            paymentRecordDO.setId(UUIDUtils.getUUID());
            int insertSize = paymentRecordDAO.insert(paymentRecordDO);
            if (insertSize < 1) {
                return null;
            }
            paymentRecord.setId(paymentRecordDO.getId());
            return paymentRecord;
        } else {
            int updateSize = paymentRecordDAO.update(new PaymentRecordDO(paymentRecord));
            if (updateSize < 1) {
                return null;
            }
            return paymentRecord;
        }

    }

    @Override
    public List<PaymentRecord> query(PaymentRecordQueryParam paymentRecordQueryParam) {
        if (paymentRecordQueryParam == null) {
            return null;
        }
        List<PaymentRecordDO> paymentRecordDOS = paymentRecordDAO.select(paymentRecordQueryParam);
        return CollectionUtils.isEmpty(paymentRecordDOS) ? new ArrayList<>() : paymentRecordDOS.stream()
                .map(PaymentRecordDO::convertToModel)
                .collect(Collectors.toList());
    }
}
