package com.wjs.timer.service.scheduler;

import com.wjs.timer.api.dto.SchedulerDTO;

import java.util.List;


public interface SchedulerService {
    List<SchedulerDTO> queryAll();

    void addScheduleJob(Long taskId);

    void pauseTrigger(String triggerGroup, String triggerName);

    void resumeTrigger(String triggerGroup, String triggerName);

    boolean unscheduleJob(String triggerGroup, String triggerName);
}
