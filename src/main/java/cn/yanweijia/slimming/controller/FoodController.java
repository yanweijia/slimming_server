package cn.yanweijia.slimming.controller;


import cn.yanweijia.slimming.service.IFoodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * 食物相关Controller
 */
@Controller
@RequestMapping("/api/food")
public class FoodController {
    @Resource
    private IFoodService foodService;

    /**
     * 获取所有食物分类
     *
     * @return {"success",boolean,"message",String,"category":JSONArray(FoodCategory)>}
     * @throws IOException
     */
    @RequestMapping("/listCategory")
    public ResponseEntity<Map> listCategory() throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("message", "");
        map.put("category", foodService.listFoodCategory());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }


    /**
     * 根据食物分类获取食物信息
     *
     * @param categoryId 分类编号
     * @return {"success":boolean,"message":String,"category":int,"foods":JSONArray(foods)}
     * @throws IOException
     */
    @RequestMapping("/listFoodByCategory")
    public ResponseEntity<Map> listFoodByCategory(Integer categoryId) throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("message", "");
        map.put("category", categoryId);
        map.put("foods", foodService.listFoodByCategory(categoryId));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    /**
     * 根据食物名称获取食物信息
     *
     * @param name 食物名称
     * @return {"success":boolean,"message":String,"foodName":String,"foods":JSONArray(foods)}
     * @throws IOException
     */
    @RequestMapping("/listFoodByName")
    public ResponseEntity<Map> listFoodByCategory(String name) throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("message", "");
        map.put("foodName", name);
        map.put("foods", foodService.listFoodByName(name));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
