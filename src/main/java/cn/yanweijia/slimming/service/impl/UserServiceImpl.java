package cn.yanweijia.slimming.service.impl;

import cn.yanweijia.slimming.dao.IUserDAO;
import cn.yanweijia.slimming.model.User;
import cn.yanweijia.slimming.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;


@Service("userService")
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDAO userDao;

    public User getUserById(Integer id) {
        return userDao.selectByPrimaryKey(id);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return (id == null || id < 0) ? 0 : userDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insertUser(User record) {
        if (record.getId() == null || record.getId() < 0)
            return 0;
        else
            return userDao.insert(record);
    }

    @Override
    public int insertNewUser(User record) {
        record.setId(null);
        record.setStatus((byte)0);
        record.setRegTime(new Date());
        record.setLastLogin(new Date());

        return userDao.insertSelective(record);
    }

    @Override
    public int updateById(User record) {
        Integer id = record.getId();
        return (id == null || id < 0) ? 0 : userDao.updateByPrimaryKey(record);
    }

    @Override
    public int updateByIdSelective(User record) {
        Integer id = record.getId();
        return (id == null || id < 0) ? 0 : userDao.updateByPrimaryKeySelective(record);
    }

}
