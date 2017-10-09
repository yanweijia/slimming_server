package cn.yanweijia.slimming.dao;

import cn.yanweijia.slimming.model.BloodPressure;
import cn.yanweijia.slimming.model.BloodPressureExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface IBloodPressureDAO {
    long countByExample(BloodPressureExample example);

    int deleteByExample(BloodPressureExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BloodPressure record);

    int insertSelective(BloodPressure record);

    List<BloodPressure> selectByExample(BloodPressureExample example);

    BloodPressure selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BloodPressure record, @Param("example") BloodPressureExample example);

    int updateByExample(@Param("record") BloodPressure record, @Param("example") BloodPressureExample example);

    int updateByPrimaryKeySelective(BloodPressure record);

    int updateByPrimaryKey(BloodPressure record);
}