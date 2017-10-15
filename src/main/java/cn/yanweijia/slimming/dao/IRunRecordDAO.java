package cn.yanweijia.slimming.dao;

import cn.yanweijia.slimming.model.RunRecord;
import cn.yanweijia.slimming.model.RunRecordExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface IRunRecordDAO {
    long countByExample(RunRecordExample example);

    int deleteByExample(RunRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RunRecord record);

    int insertSelective(RunRecord record);

    List<RunRecord> selectByExampleWithBLOBs(RunRecordExample example);

    List<RunRecord> selectByExample(RunRecordExample example);

    RunRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RunRecord record, @Param("example") RunRecordExample example);

    int updateByExampleWithBLOBs(@Param("record") RunRecord record, @Param("example") RunRecordExample example);

    int updateByExample(@Param("record") RunRecord record, @Param("example") RunRecordExample example);

    int updateByPrimaryKeySelective(RunRecord record);

    int updateByPrimaryKeyWithBLOBs(RunRecord record);

    int updateByPrimaryKey(RunRecord record);
}