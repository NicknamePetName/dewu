package com.youkeda.dewu.api;

import com.youkeda.dewu.model.Result;
import com.youkeda.dewu.model.User;
import com.youkeda.dewu.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * @author joe
 */
@Controller
@RequestMapping("/api/user")
public class UserAPI {

    @Autowired
    private UserService userService;
    @ResponseBody
    @PostMapping("/reg")
    public Result<User> reg(@RequestParam("userName") String userName, @RequestParam("pwd") String pwd) {
        return userService.register(userName, pwd);
    }
    @ResponseBody
    @PostMapping("/login")
    public Result<User> login(@RequestParam("userName") String userName, @RequestParam("pwd") String pwd,
                              HttpServletRequest request) {
        Result<User> result = userService.login(userName, pwd);

        if (result.isSuccess()) {
            request.getSession().setAttribute("userId", result.getData().getId());
        }

        return result;
    }
    @ResponseBody
    @GetMapping("/logout")
    public Result logout(HttpServletRequest request) {
        Result result = new Result();
        request.getSession().removeAttribute("userId");

        result.setSuccess(true);
        return result;
    }

    @ResponseBody
    @GetMapping("/checklogin")
    public Result<Boolean> checkLogin(HttpServletRequest request) {
        Result<Boolean> result = new Result<>();
        HttpSession session = request.getSession();
        result.setData(userService.checkLogin(session));
        request.getSession(true);
        return result;
    }


}
