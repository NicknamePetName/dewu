package com.youkeda.dewu.api;

import com.youkeda.dewu.model.Order;
import com.youkeda.dewu.model.Result;
import com.youkeda.dewu.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(path = "/api/order")
public class OrderAPI {

    @Autowired
    private OrderService orderService;

    /**
     * 生成订单Api
     *
     * @return Result
     */
    @PostMapping(path = "/add")
    @ResponseBody
    public Result<Order> payOrder(@RequestBody Order order, HttpServletRequest request) {
        Result result = new Result();
        if (order == null) {
            result.setSuccess(false);
            result.setMessage("order is null");
            return result;
        }
        Long userId = (Long)request.getSession().getAttribute("userId");
        if (userId == null) {
            result.setSuccess(false);
            result.setMessage("未获取到登录信息");
            return result;
        }
        order.setUserId(userId.toString());
        Order orderResult = orderService.add(order);
        result.setData(orderResult);
        result.setSuccess(true);
        return result;
    }
}
