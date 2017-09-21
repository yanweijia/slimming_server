package cn.yanweijia.slimming.service.impl;

import cn.yanweijia.slimming.dao.IFoodCategoryDAO;
import cn.yanweijia.slimming.dao.IFoodDAO;
import cn.yanweijia.slimming.dao.IFoodMeasurementDAO;
import cn.yanweijia.slimming.model.*;
import cn.yanweijia.slimming.service.IFoodService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("foodService")
public class FoodServiceImpl implements IFoodService {
    @Resource
    private IFoodDAO foodDAO;
    @Resource
    private IFoodCategoryDAO foodCategoryDAO;
    @Resource
    private IFoodMeasurementDAO foodMeasurementDAO;


    @Override
    public List<FoodCategory> listFoodCategory() {
        FoodCategoryExample foodCategoryExample = new FoodCategoryExample();
        return foodCategoryDAO.selectByExample(foodCategoryExample);
    }

    @Override
    public List<FoodMeasurement> listFoodMeasurementByFoodId(int foodId) {
        FoodMeasurementExample foodMeasurementExample = new FoodMeasurementExample();
        foodMeasurementExample.or().andFoodIdEqualTo(foodId);
        return foodMeasurementDAO.selectByExample(foodMeasurementExample);
    }

    @Override
    public Food getFoodInfoById(int foodId) {
        return foodDAO.selectByPrimaryKey(foodId);
    }

    @Override
    public List<Food> listFoodByName(String name) {
        FoodExample foodExample = new FoodExample();
        foodExample.or().andNameLike("%" + name + "%");
        return foodDAO.selectByExample(foodExample);
    }

    @Override
    public List<Food> listFoodByName(String name, int perPage, int pageNum) {
        List<Food> list = listFoodByName(name);
        return listWithPage(list, perPage, pageNum);
    }

    @Override
    public List<Food> listFoodByCategory(int category) {
        FoodExample foodExample = new FoodExample();
        foodExample.or().andCategoryEqualTo(category);
        return foodDAO.selectByExample(foodExample);
    }

    @Override
    public List<Food> listFoodByCategory(int category, int perPage, int pageNum) {
        List<Food> list = listFoodByCategory(category);
        return listWithPage(list, perPage, pageNum);
    }


    /**
     * 对List进行分页处理
     *
     * @param list    待处理的List
     * @param perPage 每页个数
     * @param pageNum 第几页
     * @return 满足结果的subList
     */
    private List<Food> listWithPage(List<Food> list, int perPage, int pageNum) {
        //参数错误或超过已有个数,返回空
        if (pageNum <= 0 || list.size() <= (pageNum - 1) * perPage)
            return new ArrayList<>();
        else {
            int fromIndex = (pageNum - 1) * perPage;
            int toIndex = (list.size() > pageNum * perPage) ? pageNum * perPage : list.size();
            return list.subList(fromIndex, toIndex);
        }
    }
}
