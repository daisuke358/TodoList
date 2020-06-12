package jp.co.task.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import jp.co.task.entity.Task;

public interface TaskMapper {
    Task getTask(int id);
	List<Task> getTaskAll();
	int insertTask(@Param("task") String task, @Param("progress") String progress, @Param("priority") String priority);
	int deleteTask(int id);
	int updateTask(@Param("id") int id, @Param("task") String task, @Param("progress") String progress, @Param("priority") String priority);
	int progressUpdate(@Param("id") int id, @Param("progress") String progress);
}