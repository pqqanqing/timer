package com.wjs.timer.domain.scheduler;

import com.wjs.common.base.rpt.BaseRpt;
import com.wjs.timer.api.dto.SchedulerDTO;

import java.io.Serializable;
import java.util.List;

public interface SchedulerRpt extends BaseRpt<Scheduler, Serializable> {
    List<SchedulerDTO> queryAll();
}
