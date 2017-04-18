package com.wjs.timer.service.job;

import com.wjs.common.base.quartz.BaseQuartzJobBean;
import com.wjs.timer.service.task.TaskService;
import org.quartz.*;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class SerialQuartzJob extends BaseQuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        TaskService taskService = getApplicationContext(jobExecutionContext).getBean("taskService", TaskService.class);
        Trigger trigger = jobExecutionContext.getTrigger();
        TriggerKey triggerKey = trigger.getKey();
        taskService.exec(triggerKey);
    }

}
