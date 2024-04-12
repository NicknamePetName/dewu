package com.youkeda.dewu.control;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.youkeda.dewu.dao.UserDAO;
import com.youkeda.dewu.dataobject.UserDO;
import com.youkeda.dewu.model.Paging;
import com.youkeda.dewu.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @GetMapping("/users")
    @ResponseBody
    public Result<Paging<UserDO>> getAll(@RequestParam(defaultValue = "1") Integer pageNum,
                                         @RequestParam(defaultValue = "15") Integer pageSize) {
        Result<Paging<UserDO>> result = new Result();

        // 设置当前页数为1，以及每页3条记录
        Page<UserDO> page = PageHelper.startPage(pageNum, pageSize).doSelectPage(() -> userDAO.findAll());

        result.setSuccess(true);
        result.setData(
                new Paging<>(page.getPageNum(), page.getPageSize(), page.getPages(), page.getTotal(), page.getResult()));
        return result;
    }

    @PostMapping("/user")
    @ResponseBody
    public UserDO save(@RequestBody UserDO userDO) {
        userDAO.add(userDO);
        return userDO;
    }

    @PostMapping("/user/batchAdd")
    @ResponseBody
    public List<UserDO> batchAdd(@RequestBody List<UserDO> userDOs) {
        userDAO.batchAdd(userDOs);
        return userDOs;
    }

    @PostMapping("/user/update")
    @ResponseBody
    public UserDO update(@RequestBody UserDO userDO) {
        userDAO.update(userDO);
        return userDO;
    }

    @GetMapping("/user/del")
    @ResponseBody
    public boolean delete(@RequestParam("id") Long id) {
        return userDAO.delete(id) > 0;
    }

    @GetMapping("/user/findByUserName")
    @ResponseBody
    public UserDO findByUserName(@RequestParam("userName") String userName) {
        return userDAO.findByUserName(userName);
    }

    @GetMapping("/user/search")
    @ResponseBody
    public List<UserDO> search(@RequestParam("keyWord") String keyWord,
                               @RequestParam("startTime") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                               LocalDateTime startTime,
                               @RequestParam("endTime") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                               LocalDateTime endTime) {
        return userDAO.search(keyWord, startTime, endTime);
    }

    @GetMapping("/user/findByIds")
    @ResponseBody
    public List<UserDO> findByIds(@RequestParam("ids") List<Long> ids) {
        return userDAO.findByIds(ids);
    }
}
