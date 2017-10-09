package cn.yanweijia.slimming.controller;


import cn.yanweijia.slimming.model.BloodGlucose;
import cn.yanweijia.slimming.model.BloodPressure;
import cn.yanweijia.slimming.model.HeartRate;
import cn.yanweijia.slimming.model.UserWeight;
import cn.yanweijia.slimming.service.IHealthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/health")
public class HealthController {
    @Resource
    private IHealthService healthService;


    /**
     * 获取用户心率数据
     *
     * @param session   http session
     * @param startTime 区间开始时间,long类型,时间戳
     * @param endTime   区间结束时间,long类型,时间戳
     * @return json
     * @throws IOException
     */
    @RequestMapping(value = "/heartrate/download")
    public ResponseEntity<Map> listHeartRate(HttpSession session, @RequestParam long startTime, @RequestParam long endTime) throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("message", "");
        int userid = (int) session.getAttribute("id");
        map.put("heartRate", healthService.listHeartRate(userid, new Date(startTime), new Date(endTime)));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    /**
     * 获取用户体重数据
     *
     * @param session   http session
     * @param startTime 区间开始时间,long类型,时间戳
     * @param endTime   区间结束时间,long类型,时间戳
     * @return json
     * @throws IOException
     */
    @RequestMapping(value = "/weight/download")
    public ResponseEntity<Map> listUserWeight(HttpSession session, @RequestParam long startTime, @RequestParam long endTime) throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("message", "");
        int userid = (int) session.getAttribute("id");
        map.put("userWeight", healthService.listUserWeight(userid, new Date(startTime), new Date(endTime)));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    /**
     * 获取用户血压数据
     *
     * @param session   http session
     * @param startTime 区间开始时间,long类型,时间戳
     * @param endTime   区间结束时间,long类型,时间戳
     * @return json
     * @throws IOException
     */
    @RequestMapping(value = "/bloodpressure/download")
    public ResponseEntity<Map> listBloodPressure(HttpSession session, @RequestParam long startTime, @RequestParam long endTime) throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("message", "");
        int userid = (int) session.getAttribute("id");
        map.put("bloodPressure", healthService.listBloodPressure(userid, new Date(startTime), new Date(endTime)));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    /**
     * 获取用户血糖数据
     *
     * @param session   http session
     * @param startTime 区间开始时间,long类型,时间戳
     * @param endTime   区间结束时间,long类型,时间戳
     * @return json
     * @throws IOException
     */
    @RequestMapping(value = "/bloodglucose/download")
    public ResponseEntity<Map> listBloodGlucose(HttpSession session, @RequestParam long startTime, @RequestParam long endTime) throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("message", "");
        int userid = (int) session.getAttribute("id");
        map.put("bloodGlucose", healthService.listBloodGlucose(userid, new Date(startTime), new Date(endTime)));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    /**
     * 上传心率数据,仅限 POST<br/>
     * 记得请求的时候 contentType设置为:
     * <strong style='color:green'>
     * application/json;charset=UTF-8
     * </strong>
     *
     * @param session   httpSession
     * @param heartRate 心率数据
     * @return json
     * @throws IOException
     */
    @RequestMapping(value = "/heartrate/upload", method = RequestMethod.POST)
    public ResponseEntity<Map> uploadHeartRate(HttpSession session, @RequestBody HeartRate heartRate) throws IOException {
        int userid = (int) session.getAttribute("id");
        heartRate.setUserId(userid);
        Map<String, Object> map = new HashMap<>();
        boolean success = healthService.saveHeartRate(heartRate) != 0;
        map.put("success", success);
        map.put("message", null);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    /**
     * 上传血压数据,仅限 POST<br/>
     * 记得请求的时候 contentType设置为:
     * <strong style='color:green'>
     * application/json;charset=UTF-8
     * </strong>
     *
     * @param session       httpSession
     * @param bloodPressure 血压数据
     * @return json
     * @throws IOException
     */
    @RequestMapping(value = "/bloodpressure/upload", method = RequestMethod.POST)
    public ResponseEntity<Map> uploadBloodPressure(HttpSession session, @RequestBody BloodPressure bloodPressure) throws IOException {
        int userid = (int) session.getAttribute("id");
        bloodPressure.setUserId(userid);
        Map<String, Object> map = new HashMap<>();
        boolean success = healthService.saveBloodPressure(bloodPressure) != 0;
        map.put("success", success);
        map.put("message", null);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    /**
     * 上传血糖数据,仅限 POST<br/>
     * 记得请求的时候 contentType设置为:
     * <strong style='color:green'>
     * application/json;charset=UTF-8
     * </strong>
     *
     * @param session      httpSession
     * @param bloodGlucose 血糖数据
     * @return json
     * @throws IOException
     */
    @RequestMapping(value = "/bloodglucose/upload", method = RequestMethod.POST)
    public ResponseEntity<Map> uploadBloodGlucose(HttpSession session, @RequestBody BloodGlucose bloodGlucose) throws IOException {
        int userid = (int) session.getAttribute("id");
        bloodGlucose.setUserId(userid);
        Map<String, Object> map = new HashMap<>();
        boolean success = healthService.saveBloodGlucose(bloodGlucose) != 0;
        map.put("success", success);
        map.put("message", null);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    /**
     * 上传体重数据,仅限 POST<br/>
     * 记得请求的时候 contentType设置为:
     * <strong style='color:green'>
     * application/json;charset=UTF-8
     * </strong>
     *
     * @param session    httpSession
     * @param userWeight 体重数据
     * @return json
     * @throws IOException
     */
    @RequestMapping(value = "/weight/upload", method = RequestMethod.POST)
    public ResponseEntity<Map> uploadWeight(HttpSession session, @RequestBody UserWeight userWeight) throws IOException {
        int userid = (int) session.getAttribute("id");
        userWeight.setUserId(userid);
        Map<String, Object> map = new HashMap<>();
        boolean success = healthService.saveUserWeight(userWeight) != 0;
        map.put("success", success);
        map.put("message", null);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
