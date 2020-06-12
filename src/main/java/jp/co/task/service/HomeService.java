package jp.co.task.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.task.dto.task.TaskDto;
import jp.co.task.entity.Task;
import jp.co.task.mapper.TaskMapper;

@Service
public class HomeService {

	@Autowired
    private TaskMapper taskMapper;

	public List<TaskDto> getTaskAll() {
	    List<Task> taskList = taskMapper.getTaskAll();
	    List<TaskDto> resultList = convertToDto(taskList);
	    return resultList;
	}

	private List<TaskDto> convertToDto(List<Task> taskList) {
	    List<TaskDto> resultList = new LinkedList<>();
	    for (Task entity : taskList) {
	        TaskDto dto = new TaskDto();
	        BeanUtils.copyProperties(entity, dto);
	        resultList.add(dto);
	    }
	    return resultList;
	}

	public int insertTask(String task, String progress, String priority) {
	    int count = taskMapper.insertTask(task, progress, priority);
	    return count;
	}

	public int deleteTask(int id) {
	    int count = taskMapper.deleteTask(id);
	    return count;
	}

	public int updateTask(int id, String task, String progress, String priority) {
	    int count = taskMapper.updateTask(id, task, progress, priority);
	    return count;
	}

	public TaskDto getTask(Integer id) {
		TaskDto dto = new TaskDto();
	    Task entity = taskMapper.getTask(id);
	    BeanUtils.copyProperties(entity, dto);
	    return dto;
	}

	public int progressUpdate(int id, String progress) {
		int count = taskMapper.progressUpdate(id, progress);
	    return count;
	}
}
