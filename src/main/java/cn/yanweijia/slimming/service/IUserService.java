package cn.yanweijia.slimming.service;

import cn.yanweijia.slimming.model.User;

import javax.servlet.http.HttpSession;

public interface IUserService {

    /**
     * 通过编号获取用户实例
     * @param id 用户编号
     * @return
     */
    User getUserById(Integer id);

    /**
     * 删除一个用户
     * @param id 用户编号
     * @return 是否成功
     */
    int deleteUserById(Integer id);

    /**
     * 插入一个用户
     * @param record 用户信息
     * @return
     */
    int addUser(User record);

    /**
     * 插入一个新用户
     * @param record 用户信息
     * @return
     */
    int saveUser(User record);

    /**
     * 修改用户信息
     * @param record 用户信息
     * @return
     */
    int updateUserById(User record);

    /**
     * 选择性修改用户信息
     * @param record 用户信息
     * @return
     */
    int updateUserByIdSelective(User record);


    /**
     * 修改密码
     * @param id 用户编号
     * @param oldPw 旧密码
     * @param newPw 新密码
     * @return 常量:UserServiceImpl.CHANGE_PW_开头
     */
    int changePassword(Integer id,String oldPw,String newPw);

    /**
     * 用户登录请求
     * @param session session
     * @param username 用户名
     * @param password 密码
     * @return 常量:UserServiceImpl.LOGIN_开头
     */
    int login(HttpSession session, String username, String password);

}
