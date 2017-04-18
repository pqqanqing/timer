package com.wjs.timer.domain.task;

import com.wjs.common.base.rpt.BaseRpt;
import org.quartz.TriggerKey;

import java.io.Serializable;

public interface TaskRpt extends BaseRpt<Task, Serializable> {
    Task queryByTriggerKey(TriggerKey triggerKey);

}
