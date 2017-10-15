package cn.yanweijia.slimming.service;


import cn.yanweijia.slimming.model.RunRecord;

import java.util.Date;
import java.util.List;

/**
 * 运动相关类
 *
 * @author weijia
 * @date 2017.10.15
 */
public interface ISportService {

    List<RunRecord> listRunRecoard(int userid, Date startTime, Date endTime);

    int saveRunRecoard(RunRecord record);
}
