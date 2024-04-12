package com.youkeda.dewu.api;

import com.youkeda.dewu.model.Result;
import com.youkeda.dewu.model.User;
import com.youkeda.dewu.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author joe
 */
@Controller
public class UserAPI {

    @Autowired
    private UserService userService;

    @PostMapping("/api/user/reg")
    @ResponseBody
    public Result<User> reg(@RequestParam("userName") String userName, @RequestParam("pwd") String pwd) {
        return userService.register(userName, pwd);
    }

    @PostMapping("/api/user/login")
    @ResponseBody
    public Result<User> login(@RequestParam("userName") String userName, @RequestParam("pwd") String pwd,
                              HttpServletRequest request) {
        Result<User> result = userService.login(userName, pwd);

        if (result.isSuccess()) {
            request.getSession().setAttribute("userId", result.getData().getId());
        }

        return result;
    }

    @GetMapping("/api/user/logout")
    @ResponseBody
    public Result logout(HttpServletRequest request) {
        Result result = new Result();
        request.getSession().removeAttribute("userId");

        result.setSuccess(true);
        return result;
    }

}
