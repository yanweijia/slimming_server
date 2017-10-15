package cn.yanweijia.slimming.controller;

import cn.yanweijia.slimming.model.RunRecord;
import cn.yanweijia.slimming.service.ISportService;
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
@RequestMapping("api/sport")
public class SportController {
    @Resource
    private ISportService sportService;

    /**
     * 获取用户运动数据
     *
     * @param session   http session
     * @param startTime 区间开始时间,long类型,时间戳
     * @param endTime   区间结束时间,long类型,时间戳
     * @return json
     * @throws IOException
     */
    @RequestMapping(value = "/run/listrecord")
    public ResponseEntity<Map> listRunRecord(HttpSession session, @RequestParam long startTime, @RequestParam long endTime) throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("message", "");
        int userid = (int) session.getAttribute("id");
        map.put("runRecord", sportService.listRunRecoard(userid, new Date(startTime), new Date(endTime)));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    /**
     * 上传运动数据,仅限 POST<br/>
     * 记得请求的时候 contentType设置为:
     * <strong style='color:green'>
     * application/json;charset=UTF-8
     * </strong>
     *
     * @param session   http session
     * @param runRecord 跑步数据
     * @return json
     * @throws IOException
     */
    @RequestMapping(value = "run/upload", method = RequestMethod.POST)
    public ResponseEntity<Map> uploadRunRecord(HttpSession session, @RequestBody RunRecord runRecord) throws IOException {
        int userid = (int) session.getAttribute("id");
        runRecord.setUserId(userid);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("message", null);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
