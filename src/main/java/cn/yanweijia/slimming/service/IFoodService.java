package cn.yanweijia.slimming.service;

import cn.yanweijia.slimming.model.Food;
import cn.yanweijia.slimming.model.FoodCategory;
import cn.yanweijia.slimming.model.FoodMeasurement;

import java.util.List;


/**
 * @author weijia
 * @date 2017.09.21
 * @apiNote 实物相关service
 */
public interface IFoodService {
    /**
     * 列出所有食物分类列表
     *
     * @return
     */
    List<FoodCategory> listFoodCategory();

    /**
     * 列出所有指定食物的计量单位&计量方式
     *
     * @param foodId 食物编号
     * @return
     */
    List<FoodMeasurement> listFoodMeasurementByFoodId(int foodId);

    /**
     * 根据食物编号获取食物食物信息
     *
     * @param foodId 食物编号
     * @return
     */
    Food getFoodInfoById(int foodId);

    /**
     * 根据食物名称列出食物
     *
     * @param name 食物名称
     * @return
     */
    List<Food> listFoodByName(String name);

    /**
     * 根据食物名称分页列出食物
     *
     * @param name    食物名称
     * @param perPage 每页个数
     * @param pageNum 当前页数,从1开始.若超过范围,则返回 空List
     * @return
     */
    List<Food> listFoodByName(String name, int perPage, int pageNum);

    /**
     * 根据食物分类列出食物
     *
     * @param category 食物分类编号
     * @return
     */
    List<Food> listFoodByCategory(int category);

    /**
     * 根据食物分类分页列出食物
     *
     * @param category 分类
     * @param perPage  每页个数
     * @param pageNum  当前页数,从1开始,若超过范围,返回空List
     * @return
     */
    List<Food> listFoodByCategory(int category, int perPage, int pageNum);
}
