package cn.yanweijia.slimming.dao;

import cn.yanweijia.slimming.model.Food;
import cn.yanweijia.slimming.model.FoodExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IFoodDAO {
    long countByExample(FoodExample example);

    int deleteByExample(FoodExample example);

    int deleteByPrimaryKey(Integer foodId);

    int insert(Food record);

    int insertSelective(Food record);

    List<Food> selectByExample(FoodExample example);

    Food selectByPrimaryKey(Integer foodId);

    int updateByExampleSelective(@Param("record") Food record, @Param("example") FoodExample example);

    int updateByExample(@Param("record") Food record, @Param("example") FoodExample example);

    int updateByPrimaryKeySelective(Food record);

    int updateByPrimaryKey(Food record);
}