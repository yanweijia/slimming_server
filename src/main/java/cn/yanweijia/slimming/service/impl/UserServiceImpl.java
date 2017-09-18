package cn.yanweijia.slimming.service.impl;

import cn.yanweijia.slimming.dao.IUserDAO;
import cn.yanweijia.slimming.model.User;
import cn.yanweijia.slimming.model.UserExample;
import cn.yanweijia.slimming.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;


@Service("userService")
public class UserServiceImpl implements IUserService {
    /** 登录成功 */
    public static final int LOGIN_SUCCESS = 0;
    /** 输入参数非法 */
    public static final int LOGIN_INPUT_ILLEGAL = 1;
    /** 当前用户被禁用 */
    public static final int LOGIN_USER_FORBIDDEN = 2;
    /** 用户名密码不匹配 */
    public static final int LOGIN_PW_NOT_MATCH = 3;
    /** 修改密码成功 */
    public static final int CHANGE_PW_SUCCESS = 1;
    /** 修改密码失败,旧密码与新密码相同 */
    public static final int CHANGE_PW_FAIL_SAME_PW = 2;
    /** 修改密码失败,旧密码错误或不存在此用户 */
    public static final int CHANGE_PW_FAIL_NO_USER_OR_WRONG_OLD_PW = 3;
    /** 修改密码失败,无效的新密码 */
    public static final int CHANGE_PW_FAIL_ILLEGAL_PW = 4;
    /** 修改密码失败,参数传递异常 */
    public static final int CHANGE_PW_FAIL_PARAM_ERR = 5;
    /** 修改密码失败,系统错误 */
    public static final int CHANGE_PW_FAIL_SYS_ERR = 6;


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
        record.setStatus((byte)0);
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
        }else if(newPw.length() != 32){
            return CHANGE_PW_FAIL_ILLEGAL_PW;
        }else if (oldPw.equals(newPw)) {
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
    public int login(HttpSession session,String username, String password) {
        if(StringUtils.isEmpty(username)||StringUtils.isEmpty(password))
            return LOGIN_INPUT_ILLEGAL;

        UserExample userExample = new UserExample();
        userExample.or().andUsernameEqualTo(username).andPasswordEqualTo(password);
        List<User> userList = userDao.selectByExample(userExample);
        if(userList.size()==0)
            return LOGIN_PW_NOT_MATCH;
        else{
            if(userList.get(0).getStatus()!=0)
                return LOGIN_USER_FORBIDDEN;
        }
        User user = userList.get(0);
        session.setAttribute("id",user.getId());
        session.setAttribute("username",user.getUsername());
        return LOGIN_SUCCESS;
    }

}
