package cn.yanweijia.slimming.controller;


import cn.yanweijia.slimming.model.Food;
import cn.yanweijia.slimming.model.FoodCategory;
import cn.yanweijia.slimming.model.FoodMeasurement;
import cn.yanweijia.slimming.service.IFoodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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
     * 根据食物名称搜索获取食物信息
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


    /**
     * 根据食物编号获取食物计量信息
     *
     * @param foodId 食物编号
     * @return {"success":boolean,"message":String,"foodId":int,"foodMeasurement":JSONArray(FoodMeasurement)}
     * @throws IOException
     */
    @RequestMapping("/listFoodMeasurementByFoodID")
    public ResponseEntity<Map> listFoodMeasurementByFoodID(Integer foodId) throws IOException {
        Map<String, Object> map = new HashMap<>();
        List<FoodMeasurement> list = foodService.listFoodMeasurementByFoodId(foodId);
        map.put("success", true);
        map.put("message", list.isEmpty() ? "该食物没有计量信息" : "");
        map.put("foodId", foodId);
        map.put("foodMeasurement", list);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    /**
     * 推荐食物给用户
     *
     * @param userid 用户编号,根据用户来进行推荐
     * @return
     * @throws IOException
     */
    @RequestMapping("/recommend")
    public ResponseEntity<Map> recommend(Integer userid) throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("message", "推荐成功");
        List<FoodCategory> foodCategories = foodService.listFoodCategory();
        int category = (int) (Math.random() * foodCategories.size());
        List<Food> foods = foodService.listFoodByCategory(category);
        map.put("food", foods.get((int) (Math.random() * foods.size())));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
