package cn.yanweijia.slimming.controller;


import cn.yanweijia.slimming.service.IUserService;
import cn.yanweijia.slimming.service.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author:  weijia
 * @func;    用户操作相关
 */
@Controller
@RequestMapping("/api/guest")
public class GuestController {
    @Resource
    private IUserService userService;

    /**
     * 登录接口
     * @param httpSession session
     * @param username 用户名
     * @param password 密码
     * @return {"success":boolean,"message":string}
     */
    @RequestMapping(value="")
    public ResponseEntity<Map> login(HttpSession httpSession,@RequestParam String username, @RequestParam String password){
        Map<String,Object> map = new HashMap<>();
        int loginStatus = userService.login(httpSession,username,password);


        map.put("success",loginStatus == UserServiceImpl.LOGIN_SUCCESS);
        String msg;
        switch(loginStatus){
            case UserServiceImpl.LOGIN_SUCCESS:
                msg = "登录成功";break;
            case UserServiceImpl.LOGIN_USER_FORBIDDEN:
                msg = "用户已被禁止登录";break;
            case UserServiceImpl.LOGIN_PW_NOT_MATCH:
                msg = "用户名密码不匹配";break;
            case UserServiceImpl.LOGIN_INPUT_ILLEGAL:
                msg = "请提供完整的账号密码.";break;
            default:{
                msg = "未知异常,无法登录,请联系管理员!";
            }
        }
        map.put("message",msg);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    //TODO:注册接口

}
