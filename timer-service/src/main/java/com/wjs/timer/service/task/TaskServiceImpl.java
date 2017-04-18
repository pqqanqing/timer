package com.wjs.timer.service.task;

import com.wjs.timer.api.dto.TaskDTO;
import com.wjs.timer.domain.module.Module;
import com.wjs.timer.domain.module.ModuleRpt;
import com.wjs.timer.domain.task.Task;
import com.wjs.timer.domain.task.TaskRpt;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("taskService")
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRpt taskRpt;

    @Autowired
    private ModuleRpt moduleRpt;

    @Autowired
    private Scheduler scheduler;

    @Autowired
    @Qualifier("serialJobDetail")
    private JobDetail serialJobDetail;

    @Autowired
    @Qualifier("concurrentJobDetail")
    private JobDetail concurrentJobDetail;

    @Override
    @Transactional
    public TaskDTO create(TaskDTO taskDTO) {
        Module module = moduleRpt.getById(taskDTO.getModule().getId());
        Task task = new Task(taskDTO, module);
        taskRpt.save(task);
        return task.makeDTO();
    }

    @Override
    @Transactional
    public List<TaskDTO> queryAll() {
        List<Task> tasks = taskRpt.queryList();
        List<TaskDTO> taskDTOS = new ArrayList<>();
        tasks.stream().forEach(business -> taskDTOS.add(business.makeDTO()));
        return taskDTOS;
    }

    @Override
    public List<String> getJobGroups() {
        List<String> jobGroups = new ArrayList<>();
        jobGroups.add(serialJobDetail.getKey().getGroup());
        return jobGroups;
    }

    @Override
    public List<String> getJobNames() {
        List<String> jobNames = new ArrayList<>();
        jobNames.add(serialJobDetail.getKey().getName());
        jobNames.add(concurrentJobDetail.getKey().getName());
        return jobNames;
    }

    @Override
    @Transactional
    public void exec(TriggerKey triggerKey) {
        Task task = taskRpt.queryByTriggerKey(triggerKey);
        task.exec();
    }

    @Override
    @Transactional
    public void delete(Long taskId) {
        taskRpt.deleteById(taskId);
    }

    @Override
    @Transactional
    public TaskDTO view(Long taskId) {
        Task task = taskRpt.getById(taskId);
        return task.makeDTO();
    }
}
