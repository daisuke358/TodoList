package jp.co.task.form;

import org.hibernate.validator.constraints.NotEmpty;

import jp.co.task.validator.annotation.Task;

public class TaskForm {
	private int id;
	@NotEmpty(message = "タスクを入力してください")
	@Task(30)
    private String task;
    private String progress;
    private String priority;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTask() {
        return task;
    }
    public void setTask(String task) {
        this.task = task;
    }
	public String getProgress() {
		return progress;
	}
	public void setProgress(String progress) {
		this.progress = progress;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}



}
