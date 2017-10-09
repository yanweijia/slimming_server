package cn.yanweijia.slimming.dao;

import cn.yanweijia.slimming.model.HeartRate;
import cn.yanweijia.slimming.model.HeartRateExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface IHeartRateDAO {
    long countByExample(HeartRateExample example);

    int deleteByExample(HeartRateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(HeartRate record);

    int insertSelective(HeartRate record);

    List<HeartRate> selectByExample(HeartRateExample example);

    HeartRate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") HeartRate record, @Param("example") HeartRateExample example);

    int updateByExample(@Param("record") HeartRate record, @Param("example") HeartRateExample example);

    int updateByPrimaryKeySelective(HeartRate record);

    int updateByPrimaryKey(HeartRate record);
}