package com.wjs.timer.domain.history.status;

import com.wjs.common.base.annotation.StatusAndClassNum;
import com.wjs.timer.domain.history.TaskHistory;

import static com.wjs.timer.api.dto.TaskHistoryDTO.STATUS_TYPE_SUCC;

@StatusAndClassNum(superClass = TaskHistoryStatus.class, number = STATUS_TYPE_SUCC, describe = "成功状态", parasitClass = TaskHistory.class)
public class TaskHistoryStatus4Succ extends TaskHistoryStatus {
    private static TaskHistoryStatus instance = new TaskHistoryStatus4Succ();

    private TaskHistoryStatus4Succ() {
    }

    public static TaskHistoryStatus getInstance() {
        return instance;
    }
}
