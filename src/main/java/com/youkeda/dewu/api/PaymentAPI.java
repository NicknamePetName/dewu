package com.youkeda.dewu.api;

import com.youkeda.dewu.model.Result;
import com.youkeda.dewu.param.PaymentParam;
import com.youkeda.dewu.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class PaymentAPI {

    @Autowired
    private PayService payService;

    @GetMapping("/pay")
    public Result payOrder(@RequestBody PaymentParam paymentParam) {
        return  paymentParam != null ? payService.payOrder(paymentParam) : null;
    }
}
