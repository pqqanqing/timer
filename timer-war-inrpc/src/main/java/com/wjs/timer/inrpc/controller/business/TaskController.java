package com.wjs.timer.inrpc.controller.business;


import com.wjs.timer.api.dto.TaskDTO;
import com.wjs.timer.service.module.ModuleService;
import com.wjs.timer.service.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/task.shtml")
public class TaskController {

    private static final String BASE_PATH = "task/";
    private static final String BASE_REDIRECT_PATH = "redirect:task.shtml?action=toList";

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private TaskService taskService;

    @RequestMapping(params = "action=toList")
    public String toList(HttpServletRequest request, HttpServletResponse response) {
        List<TaskDTO> businessDTOs = taskService.queryAll();
        request.setAttribute("tasks", businessDTOs);
        return BASE_PATH + "list";
    }

    @RequestMapping(params = "action=toAdd")
    public String toAdd(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("modules", moduleService.queryAll());
        request.setAttribute("jobGroups", taskService.getJobGroups());
        request.setAttribute("jobNames", taskService.getJobNames());
        return BASE_PATH + "add";
    }

    @RequestMapping(params = "action=doAdd")
    public String doAdd(HttpServletRequest request, HttpServletResponse response, TaskDTO taskDTO) {
        taskService.create(taskDTO);
        return BASE_REDIRECT_PATH;
    }

    @RequestMapping(params = "action=doView")
    public String doView(HttpServletRequest request, HttpServletResponse response, Long taskId) {
        TaskDTO taskDTO = taskService.view(taskId);
        request.setAttribute("task", taskDTO);
        return BASE_PATH + "view";
    }

    @RequestMapping(params = "action=doDelete")
    public String doDelete(HttpServletRequest request, HttpServletResponse response, Long taskId) {
        taskService.delete(taskId);
        return BASE_REDIRECT_PATH;
    }

}
