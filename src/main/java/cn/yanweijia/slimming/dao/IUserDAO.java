package cn.yanweijia.slimming.dao;

import cn.yanweijia.slimming.model.User;

public interface IUserDAO {
    User selectUser(long id);
}
