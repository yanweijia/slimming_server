package cn.yanweijia.slimming.dao;

import cn.yanweijia.slimming.model.UserWeight;
import cn.yanweijia.slimming.model.UserWeightExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface IUserWeightDAO {
    long countByExample(UserWeightExample example);

    int deleteByExample(UserWeightExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserWeight record);

    int insertSelective(UserWeight record);

    List<UserWeight> selectByExample(UserWeightExample example);

    UserWeight selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserWeight record, @Param("example") UserWeightExample example);

    int updateByExample(@Param("record") UserWeight record, @Param("example") UserWeightExample example);

    int updateByPrimaryKeySelective(UserWeight record);

    int updateByPrimaryKey(UserWeight record);
}