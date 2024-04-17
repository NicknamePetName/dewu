package com.youkeda.dewu.service.impl;

import com.youkeda.dewu.dao.UserDAO;
import com.youkeda.dewu.dataobject.UserDO;
import com.youkeda.dewu.model.Result;
import com.youkeda.dewu.model.User;
import com.youkeda.dewu.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public Result<User> register(String userName, String pwd) {
        Result<User> result = new Result<>();

        if (StringUtils.isEmpty(userName)) {
            result.setCode("600");
            result.setMessage("用户名不能为空");
            return result;
        }
        if (StringUtils.isEmpty(pwd)) {
            result.setCode("601");
            result.setMessage("密码不能为空");
            return result;
        }

        UserDO userDO = userDAO.findByUserName(userName);
        if (userDO != null) {
            result.setCode("602");
            result.setMessage("用户名已经存在");
            return result;
        }

        // 密码加自定义盐值，确保密码安全
        String saltPwd = pwd + "_ykd2050";
        // 生成md5值，并转大写字母
        String md5Pwd = DigestUtils.md5Hex(saltPwd).toUpperCase();

        UserDO userDO1 = new UserDO();
        userDO1.setUserName(userName);
        // 初始昵称等于用户名
        userDO1.setNickName(userName);
        userDO1.setPwd(md5Pwd);

        userDAO.add(userDO1);

        result.setSuccess(true);

        result.setData(userDO1.convertToModel());

        return result;
    }

    @Override
    public Result<User> login(String userName, String pwd) {

        Result<User> result = new Result<>();

        if (StringUtils.isEmpty(userName)) {
            result.setCode("600");
            result.setMessage("用户名不能为空");
            return result;
        }
        if (StringUtils.isEmpty(pwd)) {
            result.setCode("601");
            result.setMessage("密码不能为空");
            return result;
        }

        UserDO userDO = userDAO.findByUserName(userName);
        if (userDO == null) {
            result.setCode("602");
            result.setMessage("用户名不存在");
            return result;
        }

        // 密码加自定义盐值，确保密码安全
        String saltPwd = pwd + "_ykd2050";
        // 生成md5值，并转大写字母
        String md5Pwd = DigestUtils.md5Hex(saltPwd).toUpperCase();

        if (!StringUtils.equals(md5Pwd, userDO.getPwd())) {
            result.setCode("603");
            result.setMessage("密码不对");
            return result;
        }

        result.setSuccess(true);

        result.setData(userDO.convertToModel());

        return result;
    }

    @Override
    public List<User> queryUser(List<Long> userIds) {
        if (CollectionUtils.isEmpty(userIds)) {
            return null;
        }
        List<UserDO> userDOS = userDAO.findByIds(userIds);
        return CollectionUtils.isEmpty(userDOS) ? new ArrayList<>() : userDOS.stream()
                .map(UserDO::convertToModel)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean checkLogin(HttpSession session) {
        Object userId = session.getAttribute("userId");
        if (userId == null) {
            return false;
        } else {
            return true;
        }
    }
}
