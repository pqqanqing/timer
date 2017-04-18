package com.wjs.timer.service.scheduler;

import com.wjs.common.base.execption.BusinessExecption;
import com.wjs.timer.api.dto.SchedulerDTO;
import com.wjs.timer.domain.scheduler.SchedulerRpt;
import com.wjs.timer.domain.task.Task;
import com.wjs.timer.domain.task.TaskRpt;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SchedulerServiceImpl implements SchedulerService {
    @Autowired
    private TaskRpt taskRpt;

    @Autowired
    private Scheduler scheduler;

    @Autowired
    @Qualifier("serialJobDetail")
    private JobDetail serialJobDetail;

    @Autowired
    @Qualifier("concurrentJobDetail")
    private JobDetail concurrentJobDetail;

    @Autowired
    private SchedulerRpt schedulerRpt;

    @Override
    @Transactional
    public List<SchedulerDTO> queryAll() {
        List<SchedulerDTO> schedulers = schedulerRpt.queryAll();
        return schedulers;
    }

    @Override
    @Transactional
    public void addScheduleJob(Long taskId) {
        Task task = taskRpt.getById(taskId);
        CronTriggerImpl cronTrigger = task.getCronTriggerImpl();
        JobDetail jobDetail = task.isSerialJob(serialJobDetail.getKey().getName()) ? serialJobDetail : concurrentJobDetail;
        try {
            scheduler.addJob(jobDetail, true);
            checkExists(cronTrigger);
        } catch (SchedulerException e) {
            throw new BusinessExecption("timer2", task.getTriggerGroup(), task.getTriggerName());
        }
    }

    @Override
    @Transactional
    public void pauseTrigger(String triggerGroup, String triggerName) {
        TriggerKey triggerKey = new TriggerKey(triggerName, triggerGroup);
        try {
            scheduler.pauseTrigger(triggerKey);
        } catch (SchedulerException e) {
            throw new BusinessExecption("timer3", triggerGroup, triggerName);
        }
    }

    @Override
    @Transactional
    public void resumeTrigger(String triggerGroup, String triggerName) {
        TriggerKey triggerKey = new TriggerKey(triggerName, triggerGroup);
        try {
            scheduler.resumeTrigger(triggerKey);
        } catch (SchedulerException e) {
            throw new BusinessExecption("timer4", triggerGroup, triggerName);
        }
    }

    @Override
    @Transactional
    public boolean unscheduleJob(String triggerGroup, String triggerName) {
        TriggerKey triggerKey = new TriggerKey(triggerName, triggerGroup);
        try {
            scheduler.pauseTrigger(triggerKey);
            return scheduler.unscheduleJob(triggerKey);
        } catch (SchedulerException e) {
            throw new BusinessExecption("timer5", triggerGroup, triggerName);
        }
    }

    private void checkExists(CronTriggerImpl cronTriggerImpl) throws SchedulerException {
        TriggerKey key = cronTriggerImpl.getKey();
        if (scheduler.checkExists(key)) {
            scheduler.rescheduleJob(key, cronTriggerImpl);
        } else {
            scheduler.scheduleJob(cronTriggerImpl);
        }
    }
}
