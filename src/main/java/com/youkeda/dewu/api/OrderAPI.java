package com.youkeda.dewu.api;

import com.youkeda.dewu.model.Order;
import com.youkeda.dewu.model.Paging;
import com.youkeda.dewu.model.Result;
import com.youkeda.dewu.param.QueryOrderParam;
import com.youkeda.dewu.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


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
        result.setSuccess(true);
        if (order == null) {
            result.setSuccess(false);
            result.setMessage("order is null");
            return result;
        }
        String userId = (String)request.getSession().getAttribute("userId");
        if (userId == null) {
            result.setSuccess(false);
            result.setMessage("没有获取登录信息");
            return result;
        }
        order.setUserId(userId);
        Order orderResult = orderService.add(order);
        result.setData(orderResult);
        return result;
    }

    @ResponseBody
    @GetMapping("/queryrecentpaysuccess")
    public Result<Paging<Order>> queryRecentPaySuccess(@RequestBody(required = false) QueryOrderParam queryOrderParam) {
        Result<Paging<Order>> result = new Result<>();
        result.setData(orderService.queryRecentPaySuccess(queryOrderParam));
        result.setSuccess(true);
        return result;
    }
}
