package cn.yanweijia.slimming.controller;


import cn.yanweijia.slimming.service.IFoodService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/api/food")
public class FoodController {
    @Resource
    private IFoodService foodService;
}
