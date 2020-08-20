package jp.co.task.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.task.dto.dateMeal.DateMealDto;
import jp.co.task.dto.food.FoodDto;
import jp.co.task.dto.task.TaskDto;
import jp.co.task.entity.DateMeal;
import jp.co.task.entity.Food;
import jp.co.task.entity.Task;
import jp.co.task.mapper.TaskMapper;

@Service
public class HomeService {

	@Autowired
    private TaskMapper taskMapper;



	public TaskDto getTask(Integer id) {
        TaskDto dto = new TaskDto();
        Task entity = taskMapper.getTask(id);
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }


	public int insertFood(String name, String protein, String fat, String carbo, String per) {
	    int count = taskMapper.insertFood(name, protein, fat, carbo, per);
	    return count;
	}

	public int insertDateMeal(String id, String meal_quantity, String c1) {
	    int count = taskMapper.insertDateMeal(id, meal_quantity, c1);
	    return count;
	}


	public List<FoodDto> getFoodAll() {
	    List<Food> foodList = taskMapper.getFoodAll();
	    List<FoodDto> resultList = convertToDto(foodList);

	    return resultList;
	}

	private List<FoodDto> convertToDto(List<Food> foodList) {
	    List<FoodDto> resultList = new LinkedList<>();
	    for (Food entity : foodList) {
	        FoodDto dto = new FoodDto();
	        BeanUtils.copyProperties(entity, dto);
	        resultList.add(dto);
	    }
	    return resultList;
	}

	public List<DateMealDto> getDateMealAll() {
	    List<DateMeal> dateMealList = taskMapper.getDateMealAll();
	    List<DateMealDto> resultList2 = convertToDto2(dateMealList);

	    return resultList2;
	}

	private List<DateMealDto> convertToDto2(List<DateMeal> dateMealList) {
	    List<DateMealDto> resultList2 = new LinkedList<>();
	    for (DateMeal entity : dateMealList) {
	    	DateMealDto dto = new DateMealDto();
	        BeanUtils.copyProperties(entity, dto);
	        resultList2.add(dto);
	    }
	    return resultList2;
	}

	public int deleteDateMeal(int id) {
		int count = taskMapper.deleteDateMeal(id);
	    return count;
	}










//	private List<TaskDto> convertToDto(List<Task> taskList) {
//	    List<TaskDto> resultList = new LinkedList<>();
//	    for (Task entity : taskList) {
//	        TaskDto dto = new TaskDto();
//	        BeanUtils.copyProperties(entity, dto);
//	        resultList.add(dto);
//	    }
//	    return resultList;
//	}
//
//	public int insertTask(String task, String progress, String priority) {
//	    int count = taskMapper.insertTask(task, progress, priority);
//	    return count;
//	}
//
//	public int deleteTask(int id) {
//	    int count = taskMapper.deleteTask(id);
//	    return count;
//	}
//
//	public int updateTask(int id, String task, String progress, String priority) {
//	    int count = taskMapper.updateTask(id, task, progress, priority);
//	    return count;
//	}

//	public TaskDto getTask(Integer id) {
//		TaskDto dto = new TaskDto();
//	    Task entity = taskMapper.getTask(id);
//	    BeanUtils.copyProperties(entity, dto);
//	    return dto;
//	}

//	public int progressUpdate(int id, String progress) {
//		int count = taskMapper.progressUpdate(id, progress);
//	    return count;
//	}
}
