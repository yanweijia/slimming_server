package cn.yanweijia.slimming.service.impl;

import cn.yanweijia.slimming.dao.IUserDAO;
import cn.yanweijia.slimming.model.User;
import cn.yanweijia.slimming.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service("userService")
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDAO userDao;

    public User selectUser(Integer userId) {
        return userDao.selectByPrimaryKey(userId);
    }

}
