package jp.co.task.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import jp.co.task.entity.DateMeal;
import jp.co.task.entity.Food;
import jp.co.task.entity.Task;

public interface TaskMapper {


    Task getTask(int id);
    int insertFood(
    		@Param("name") String name, @Param("protein") String protein,
    	@Param("fat") String fat, @Param("carbo") String carbo, @Param("per") String per);
    List<Food> getFoodAll();
    List<DateMeal> getDateMealAll();
	int insertDateMeal(@Param("id") String id, @Param("meal_quantity") String meal_quantity, @Param("c1") String c1);
	int deleteDateMeal(int id);



//	List<Task> getTaskAll();
//	int insertTask(@Param("task") String task, @Param("progress") String progress, @Param("priority") String priority);
//	int deleteTask(int id);
//	int updateTask(@Param("id") int id, @Param("task") String task, @Param("progress") String progress, @Param("priority") String priority);
//	int progressUpdate(@Param("id") int id, @Param("progress") String progress);
}