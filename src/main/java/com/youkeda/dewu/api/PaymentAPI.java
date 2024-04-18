package com.youkeda.dewu.api;

import com.youkeda.dewu.model.Result;
import com.youkeda.dewu.param.PaymentParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class PaymentAPI {

    @GetMapping()
    public Result payOrder(@RequestBody PaymentParam paymentParam) {
        Result result = new Result();
        return result;
    }
}
