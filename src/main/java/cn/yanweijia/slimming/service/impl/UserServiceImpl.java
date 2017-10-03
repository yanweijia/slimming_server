package cn.yanweijia.slimming.service.impl;

import cn.yanweijia.slimming.dao.IUserDAO;
import cn.yanweijia.slimming.model.User;
import cn.yanweijia.slimming.model.UserExample;
import cn.yanweijia.slimming.service.IUserService;
import cn.yanweijia.slimming.utils.RequestUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;


@Service("userService")
public class UserServiceImpl implements IUserService {
    /**
     * 登录成功
     */
    public static final int LOGIN_SUCCESS = 0;
    /**
     * 输入参数非法
     */
    public static final int LOGIN_INPUT_ILLEGAL = 1;
    /**
     * 当前用户被禁用
     */
    public static final int LOGIN_USER_FORBIDDEN = 2;
    /**
     * 用户名密码不匹配
     */
    public static final int LOGIN_PW_NOT_MATCH = 3;
    /**
     * 已经登陆过了,不用重复登录
     */
    public static final int LOGIN_ALREADY_IN = 4;
    /**
     * 修改密码成功
     */
    public static final int CHANGE_PW_SUCCESS = 5;
    /**
     * 修改密码失败,旧密码与新密码相同
     */
    public static final int CHANGE_PW_FAIL_SAME_PW = 6;
    /**
     * 修改密码失败,旧密码错误或不存在此用户
     */
    public static final int CHANGE_PW_FAIL_NO_USER_OR_WRONG_OLD_PW = 7;
    /**
     * 修改密码失败,无效的新密码
     */
    public static final int CHANGE_PW_FAIL_ILLEGAL_PW = 8;
    /**
     * 修改密码失败,参数传递异常
     */
    public static final int CHANGE_PW_FAIL_PARAM_ERR = 9;
    /**
     * 修改密码失败,系统错误
     */
    public static final int CHANGE_PW_FAIL_SYS_ERR = 10;
    /**
     * 注册成功
     */
    public static final int REGISTER_SUCCESS = 11;
    /**
     * 注册失败,已存在同名用户
     */
    public static final int REGISTER_FAIL_USERNAME_REPEAT = 12;
    /**
     * 注册失败,参数非法
     */
    public static final int REGISTER_FAIL_PARAM_ILLEGAL = 13;
    /**
     * 注册失败,系统错误
     */
    public static final int REGISTER_FAIL_SYS_ERR = 14;

    @Resource
    private IUserDAO userDao;

    public User getUserById(Integer id) {
        return userDao.selectByPrimaryKey(id);
    }

    @Override
    public int deleteUserById(Integer id) {
        return (id == null || id < 0) ? 0 : userDao.deleteByPrimaryKey(id);
    }

    @Override
    public int addUser(User record) {
        if (record.getId() == null || record.getId() < 0)
            return 0;
        else
            return userDao.insert(record);
    }

    @Override
    public int saveUser(User record) {
        record.setId(null);
        record.setStatus((byte) 0);
        record.setRegTime(new Date());
        record.setLastLogin(new Date());

        return userDao.insertSelective(record);
    }

    @Override
    public int updateUserById(User record) {
        Integer id = record.getId();
        return (id == null || id < 0) ? 0 : userDao.updateByPrimaryKey(record);
    }

    @Override
    public int updateUserByIdSelective(User record) {
        Integer id = record.getId();
        return (id == null || id < 0) ? 0 : userDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int changePassword(Integer id, String oldPw, String newPw) {
        if (id == null || id < 0 || StringUtils.isEmpty(oldPw) || StringUtils.isEmpty(newPw)) {
            return CHANGE_PW_FAIL_PARAM_ERR;
        } else if (newPw.length() != 32) {
            return CHANGE_PW_FAIL_ILLEGAL_PW;
        } else if (oldPw.equals(newPw)) {
            return CHANGE_PW_FAIL_SAME_PW;
        } else {
            User user = this.getUserById(id);
            if (user != null && user.getPassword().equals(oldPw)) {
                user = new User();
                user.setId(id);
                user.setPassword(newPw);
                if (this.updateUserByIdSelective(user) != 0) {
                    return CHANGE_PW_SUCCESS;
                } else
                    return CHANGE_PW_FAIL_SYS_ERR;
            } else {
                return CHANGE_PW_FAIL_NO_USER_OR_WRONG_OLD_PW;
            }
        }
    }

    @Override
    public int login(HttpSession session, String username, String password) {
        if (session.getAttribute("id") != null) {
            return LOGIN_ALREADY_IN;
        }
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password))
            return LOGIN_INPUT_ILLEGAL;

        UserExample userExample = new UserExample();
        userExample.or().andUsernameEqualTo(username).andPasswordEqualTo(password);
        List<User> userList = userDao.selectByExample(userExample);
        if (userList.size() == 0)
            return LOGIN_PW_NOT_MATCH;
        else {
            if (userList.get(0).getStatus() != 0)
                return LOGIN_USER_FORBIDDEN;
        }
        User user = userList.get(0);
        session.setAttribute("id", user.getId());
        session.setAttribute("username", user.getUsername());
        return LOGIN_SUCCESS;
    }

    @Override
    public int register(HttpServletRequest request, String username, String password) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password) || password.length() != 32)
            return REGISTER_FAIL_PARAM_ILLEGAL;
        UserExample userExample = new UserExample();
        userExample.or().andUsernameEqualTo(username); 
        List<User> userList = userDao.selectByExample(userExample);
        if (userList.size() != 0)
            return REGISTER_FAIL_USERNAME_REPEAT;
        else {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setStatus((byte) 0);
            user.setRegTime(new Date());
            user.setLastLogin(new Date());
            String ipAddr = RequestUtils.getIPAddr(request);
            ipAddr = ipAddr.length() > 50 ? ipAddr.substring(0, 50) : ipAddr;
            user.setRegIp(ipAddr);
            if (userDao.insertSelective(user) == 0)
                return REGISTER_FAIL_SYS_ERR;
        }

        return REGISTER_SUCCESS;
    }

    @Override
    public User getUserByUsername(String username) {
        if (StringUtils.isEmpty(username))
            return null;
        UserExample userExample = new UserExample();
        userExample.or().andUsernameEqualTo(username);
        List<User> users = userDao.selectByExample(userExample);
        if (users.size() == 0)
            return null;
        else
            return users.get(0);
    }

}
