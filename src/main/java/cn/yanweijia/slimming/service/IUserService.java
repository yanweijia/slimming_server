package cn.yanweijia.slimming.service;

import cn.yanweijia.slimming.model.User;

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
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入用户
     * @param record 用户信息
     * @return
     */
    int insertUser(User record);

    /**
     * 插入一个新用户
     * @param record 用户信息
     * @return
     */
    int insertNewUser(User record);

    /**
     * 修改用户信息
     * @param record 用户信息
     * @return
     */
    int updateById(User record);

    /**
     * 选择性修改用户信息
     * @param record 用户信息
     * @return
     */
    int updateByIdSelective(User record);

}
