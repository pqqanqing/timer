package com.wjs.timer.service.task;

import com.wjs.timer.api.dto.TaskDTO;
import org.quartz.TriggerKey;

import java.util.List;

public interface TaskService {

    TaskDTO create(TaskDTO taskDTO);

    List<TaskDTO> queryAll();

    List<String> getJobGroups();

    List<String> getJobNames();

    void exec(TriggerKey triggerKey);

    void delete(Long taskId);

    TaskDTO view(Long taskId);
}
