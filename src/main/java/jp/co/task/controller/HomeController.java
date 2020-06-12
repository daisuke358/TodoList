package jp.co.task.controller;

import java.util.List;

import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.task.dto.task.TaskDto;
import jp.co.task.form.TaskForm;
import jp.co.task.service.HomeService;

@Controller
public class HomeController {

	@Autowired
    private HomeService homeService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String taskAll(Model model) {
	    List<TaskDto> tasks = homeService.getTaskAll();
	    model.addAttribute("tasks", tasks);
	    TaskForm form = new TaskForm();
	    model.addAttribute("taskForm", form);
	    return "home";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, produces="text/plain;charset=utf-8")
	public String taskInsert(@Valid @ModelAttribute TaskForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("message", "エラーが発生しました");
			List<TaskDto> tasks = homeService.getTaskAll();
		    model.addAttribute("tasks", tasks);
			return "home";
	    }else {
	    int count = homeService.insertTask(form.getTask(), form.getProgress(), form.getPriority());
	    Logger.getLogger(HomeController.class).log(Level.INFO, "挿入件数は" + count + "件です。");
	    return "redirect:/";
	    }
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public String taskDelete(@ModelAttribute TaskForm form, Model model) {
	    int count = homeService.deleteTask(form.getId());
	    Logger.getLogger(HomeController.class).log(Level.INFO, "削除件数は" + count + "件です。");
	    return "redirect:/";
	}

	@RequestMapping(value = "/update/{id}/", method = RequestMethod.GET)
	public String taskUpdate(Model model, @PathVariable int id) {
	    TaskDto task = homeService.getTask(id);
	    model.addAttribute("task", task);
	    TaskForm formUpdate = new TaskForm();
	    formUpdate.setId(task.getId());
	    formUpdate.setTask(task.getTask());
	    formUpdate.setProgress(task.getProgress());
	    formUpdate.setPriority(task.getPriority());
	    model.addAttribute("formUpdate", formUpdate);

	    List<TaskDto> tasks = homeService.getTaskAll();
	    model.addAttribute("tasks", tasks);
	    TaskForm form = new TaskForm();
	    model.addAttribute("taskForm", form);
	    return "update";
	}

	@RequestMapping(value = "/update/{id}/", method = RequestMethod.POST)
	public String taskUpdate(@Valid @ModelAttribute TaskForm form, BindingResult result, Model model) {

	    if (result.hasErrors()) {
	        model.addAttribute("message", result);
	        model.addAttribute("taskForm", form);
	    }else {
	    	TaskDto dto = new TaskDto();
		    BeanUtils.copyProperties(form, dto);
		    int count = homeService.updateTask(form.getId(), form.getTask(), form.getProgress(), form.getPriority());
		    Logger.getLogger(HomeController.class).log(Level.INFO, "更新件数は" + count + "件です。");
	    }
	    return "redirect:/";

	}
	@RequestMapping(value = "progressUpdate", method = RequestMethod.POST)
	public String progressUpdate(@ModelAttribute TaskForm form, Model model) {
	    int count = homeService.progressUpdate(form.getId(), form.getProgress());
	    Logger.getLogger(HomeController.class).log(Level.INFO, "削除件数は" + count + "件です。");
	    return "redirect:/";
	}
}
