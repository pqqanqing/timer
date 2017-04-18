package com.wjs.timer.rpt.history;

import com.wjs.common.base.rpt.BaseRptImpl;
import com.wjs.timer.domain.history.TaskHistory;
import com.wjs.timer.domain.history.TaskHistoryRpt;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class TaskHistoryRptImpl extends BaseRptImpl<TaskHistory, Serializable> implements TaskHistoryRpt {
}
