package cn.yanweijia.slimming.controller;


import cn.yanweijia.slimming.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @author:  weijia
 * @func;    用户操作相关
 */
@Controller
@RequestMapping("/api/guest")
public class GuestController {
    @Resource
    private IUserService userService;

    //TODO:登录接口

    //TODO:注册接口

}
