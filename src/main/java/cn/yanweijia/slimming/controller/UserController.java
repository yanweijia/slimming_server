package cn.yanweijia.slimming.controller;

import cn.yanweijia.slimming.model.User;
import cn.yanweijia.slimming.service.IUserService;
import cn.yanweijia.slimming.service.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: weijia
 * @func; 用户操作相关
 */
@Controller
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private IUserService userService;


    /**
     * 获取用户信息
     *
     * @param id 用户编号
     * @return json: {User}
     * @throws IOException
     */
    @RequestMapping(value = "/getUserInfo.action")
    public ResponseEntity<User> getUserInfo(@RequestParam Integer id) throws IOException {
        User user = userService.getUserById(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    /**
     * 更新用户信息,验证密码并更新,仅限 POST<br/>
     * 记得请求的时候 contentType设置为:
     * <strong style='color:green'>
     * application/json;charset=UTF-8
     * </strong>
     *
     * @param user User 的 JSON 格式请求
     * @return {"success":boolean,"message":string}
     */
    @RequestMapping(value = "/updateUserInfo.action", method = RequestMethod.POST)
    public ResponseEntity<Map> updateUserInfo(@RequestBody User user) throws IOException {
        Map<String, Object> map = new HashMap<>();
        boolean success = userService.updateUserById(user) != 0;
        map.put("success", success);
        map.put("message", (success ? "修改成功." : "修改失败."));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }


    /**
     * 修改用户密码
     *
     * @param id    用户编号
     * @param oldPw 旧密码
     * @param newPw 新密码
     * @return {"success":boolean,"message":string}
     * @throws IOException
     */
    @RequestMapping(value = "/changePassword.action", method = RequestMethod.POST)
    public ResponseEntity<Map> changePassword(@RequestParam Integer id, @RequestParam String oldPw, @RequestParam String newPw) throws IOException {
        Map<String, Object> map = new HashMap<>();
        int result = userService.changePassword(id, oldPw, newPw);
        String msg;
        switch (result) {
            case UserServiceImpl.CHANGE_PW_FAIL_ILLEGAL_PW:
                msg = "密码参数格式不正确,请使用大写的32位MD5";
                break;
            case UserServiceImpl.CHANGE_PW_FAIL_NO_USER_OR_WRONG_OLD_PW:
                msg = "旧密码不匹配或用户不存在";
                break;
            case UserServiceImpl.CHANGE_PW_FAIL_PARAM_ERR:
                msg = "参数格式不正确";
                break;
            case UserServiceImpl.CHANGE_PW_FAIL_SAME_PW:
                msg = "旧密码与新密码相同";
                break;
            case UserServiceImpl.CHANGE_PW_SUCCESS:
                msg = "修改密码成功";
                break;
            case UserServiceImpl.CHANGE_PW_FAIL_SYS_ERR:
            default:
                msg = "系统错误";
        }
        map.put("success", result == UserServiceImpl.CHANGE_PW_SUCCESS);
        map.put("message", msg);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }


    /**
     * 登出(注销)
     *
     * @param session session
     * @return {"success":boolean,"message":string}
     */
    @RequestMapping(value = "/logout.action")
    public ResponseEntity<Map> logout(HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        boolean success = false;
        if (session.getAttribute("id") != null) {
            session.removeAttribute("id");
            session.removeAttribute("username");
            success = true;
        }
        map.put("success", success);
        map.put("message", (success ? "注销成功!" : "您本来就不在线上!"));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}