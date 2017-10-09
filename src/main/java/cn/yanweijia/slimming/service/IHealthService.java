package cn.yanweijia.slimming.service;


import cn.yanweijia.slimming.model.BloodGlucose;
import cn.yanweijia.slimming.model.BloodPressure;
import cn.yanweijia.slimming.model.HeartRate;
import cn.yanweijia.slimming.model.UserWeight;

import java.util.Date;
import java.util.List;

/**
 * 健康相关 <br/>
 * created on 2017.10.09
 *
 * @author weijia
 */
public interface IHealthService {

    /**
     * 获取用户心率数据
     *
     * @param userid    用户编号
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 可空List
     */
    List<HeartRate> listHeartRate(int userid, Date startTime, Date endTime);

    /**
     * 获取用户心率数据,最大2000条
     *
     * @param userid 用户编号
     * @return 可空List
     */
    List<HeartRate> listHeartRate(int userid);

    /**
     * 获取用户血糖数据
     *
     * @param userid    用户编号
     * @param startTime 区间开始时间
     * @param endTime   区间结束时间
     * @return 可空List
     */
    List<BloodGlucose> listBloodGlucose(int userid, Date startTime, Date endTime);

    /**
     * 获取用户所有血糖数据,最大2000条
     *
     * @param userid 用户编号
     * @return 可空List
     */
    List<BloodGlucose> listBloodGlucose(int userid);

    /**
     * 获取用户血压数据
     *
     * @param userid    用户编号
     * @param startTime 区间开始时间
     * @param endTime   区间结束时间
     * @return 可空List
     */
    List<BloodPressure> listBloodPressure(int userid, Date startTime, Date endTime);

    /**
     * 获取用户血压数据
     *
     * @param userid 用户编号
     * @return 可空List
     */
    List<BloodPressure> listBloodPressure(int userid);

    /**
     * 获取用户体重数据
     *
     * @param userid    用户编号
     * @param startTime 区间开始时间
     * @param endTime   区间结束时间
     * @return 可空List
     */
    List<UserWeight> listUserWeight(int userid, Date startTime, Date endTime);

    /**
     * 获取用户体重数据
     *
     * @param userid 用户编号
     * @return 可空List
     */
    List<UserWeight> listUserWeight(int userid);

    /**
     * 保存一个心率信息
     *
     * @param heartRate 心率信息,userid不为空
     * @return 为0失败
     */
    int saveHeartRate(HeartRate heartRate);

    /**
     * 保存用户血糖信息
     *
     * @param bloodGlucose 血糖信息,userid不为空
     * @return 为0失败
     */
    int saveBloodGlucose(BloodGlucose bloodGlucose);

    /**
     * 保存用户血压信息
     *
     * @param bloodPressure 血压信息,userid不为空
     * @return 为0失败
     */
    int saveBloodPressure(BloodPressure bloodPressure);

    /**
     * 保存用户体重信息
     *
     * @param userWeight 体重信息,userid不为空
     * @return 为0失败
     */
    int saveUserWeight(UserWeight userWeight);

}
