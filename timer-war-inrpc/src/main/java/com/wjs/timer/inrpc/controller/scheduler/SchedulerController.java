package com.wjs.timer.inrpc.controller.scheduler;

import com.wjs.timer.api.dto.SchedulerDTO;
import com.wjs.timer.service.scheduler.SchedulerService;
import com.wjs.timer.service.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by panqingqing on 16/6/10.
 */
@Controller("scheduler.shtml")
public class SchedulerController {

    private static final String BASE_PATH = "scheduler/";
    private static final String BASE_REDIRECT_PATH = "redirect:scheduler.shtml?action=toList";

    @Autowired
    private SchedulerService schedulerService;

    @Autowired
    private TaskService taskService;

    @RequestMapping(params = "action=toList")
    public String toList(HttpServletRequest request, HttpServletResponse response) {
        List<SchedulerDTO> schedulers = schedulerService.queryAll();
        request.setAttribute("scheduler", schedulers);
        return BASE_PATH + "list";
    }

    @RequestMapping(params = "action=toAdd")
    public String toAdd(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("business", taskService.queryAll());
        return BASE_PATH + "add";
    }

    @RequestMapping(params = "action=doAdd")
    public String doAdd(HttpServletRequest request, HttpServletResponse response, Long businessId) {
        schedulerService.addScheduleJob(businessId);
        return BASE_REDIRECT_PATH;
    }

    @RequestMapping(params = "action=doPause")
    @ResponseBody
    public String doPause(HttpServletRequest request, HttpServletResponse response) {
        String triggerGroup = request.getParameter("triggerGroup");
        String triggerName = request.getParameter("triggerName");
        schedulerService.pauseTrigger(triggerGroup, triggerName);
        return "0";
    }

    @RequestMapping(params = "action=doDelete")
    @ResponseBody
    public String doDelete(HttpServletRequest request, HttpServletResponse response) {
        String triggerGroup = request.getParameter("triggerGroup");
        String triggerName = request.getParameter("triggerName");
        boolean b = schedulerService.unscheduleJob(triggerGroup, triggerName);
        if (b) return "0";
        return "1";
    }

    @RequestMapping(params = "action=doResume")
    @ResponseBody
    public String doResume(HttpServletRequest request, HttpServletResponse response) {
        String triggerGroup = request.getParameter("triggerGroup");
        String triggerName = request.getParameter("triggerName");
        schedulerService.resumeTrigger(triggerGroup, triggerName);
        return "0";
    }

}
