package cn.yanweijia.slimming.service.impl;

import cn.yanweijia.slimming.dao.IBloodGlucoseDAO;
import cn.yanweijia.slimming.dao.IBloodPressureDAO;
import cn.yanweijia.slimming.dao.IHeartRateDAO;
import cn.yanweijia.slimming.dao.IUserWeightDAO;
import cn.yanweijia.slimming.model.*;
import cn.yanweijia.slimming.service.IHealthService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("healthService")
public class HealthServiceImpl implements IHealthService {
    @Resource
    private IHeartRateDAO heartRateDAO;
    @Resource
    private IBloodPressureDAO bloodPressureDAO;
    @Resource
    private IBloodGlucoseDAO bloodGlucoseDAO;
    @Resource
    private IUserWeightDAO userWeightDAO;

    @Override
    public List<HeartRate> listHeartRate(int userid, Date startTime, Date endTime) {
        HeartRateExample example = new HeartRateExample();
        example.or().andUserIdEqualTo(userid).andTimeBetween(startTime, endTime);
        return heartRateDAO.selectByExample(example);
    }

    @Override
    public List<HeartRate> listHeartRate(int userid) {
        HeartRateExample example = new HeartRateExample();
        example.or().andUserIdEqualTo(userid);
        return heartRateDAO.selectByExample(example);
    }

    @Override
    public List<BloodGlucose> listBloodGlucose(int userid, Date startTime, Date endTime) {
        BloodGlucoseExample example = new BloodGlucoseExample();
        example.or().andUserIdEqualTo(userid).andTimeBetween(startTime, endTime);
        return bloodGlucoseDAO.selectByExample(example);
    }

    @Override
    public List<BloodGlucose> listBloodGlucose(int userid) {
        BloodGlucoseExample example = new BloodGlucoseExample();
        example.or().andUserIdEqualTo(userid);
        return bloodGlucoseDAO.selectByExample(example);
    }

    @Override
    public List<BloodPressure> listBloodPressure(int userid, Date startTime, Date endTime) {
        BloodPressureExample example = new BloodPressureExample();
        example.or().andUserIdEqualTo(userid).andTimeBetween(startTime, endTime);
        return bloodPressureDAO.selectByExample(example);
    }

    @Override
    public List<BloodPressure> listBloodPressure(int userid) {
        BloodPressureExample example = new BloodPressureExample();
        example.or().andUserIdEqualTo(userid);
        return bloodPressureDAO.selectByExample(example);
    }

    @Override
    public List<UserWeight> listUserWeight(int userid, Date startTime, Date endTime) {
        UserWeightExample example = new UserWeightExample();
        example.or().andUserIdEqualTo(userid).andTimeBetween(startTime, endTime);
        return userWeightDAO.selectByExample(example);
    }

    @Override
    public List<UserWeight> listUserWeight(int userid) {
        UserWeightExample example = new UserWeightExample();
        example.or().andUserIdEqualTo(userid);
        return userWeightDAO.selectByExample(example);
    }

    @Override
    public int saveHeartRate(HeartRate heartRate) {
        heartRate.setId(null);
        return heartRateDAO.insert(heartRate);
    }

    @Override
    public int saveBloodGlucose(BloodGlucose bloodGlucose) {
        bloodGlucose.setId(null);
        return bloodGlucoseDAO.insert(bloodGlucose);
    }

    @Override
    public int saveBloodPressure(BloodPressure bloodPressure) {
        bloodPressure.setId(null);
        return bloodPressureDAO.insert(bloodPressure);
    }

    @Override
    public int saveUserWeight(UserWeight userWeight) {
        userWeight.setId(null);
        return userWeightDAO.insert(userWeight);
    }
}
