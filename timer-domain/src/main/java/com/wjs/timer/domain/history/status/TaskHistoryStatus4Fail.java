package com.wjs.timer.domain.history.status;

import com.wjs.common.base.annotation.StatusAndClassNum;
import com.wjs.timer.domain.history.TaskHistory;

import static com.wjs.timer.api.dto.TaskHistoryDTO.STATUS_TYPE_FAIL;

@StatusAndClassNum(superClass = TaskHistoryStatus.class, number = STATUS_TYPE_FAIL, describe = "失败状态", parasitClass = TaskHistory.class)
public class TaskHistoryStatus4Fail extends TaskHistoryStatus {

    private static TaskHistoryStatus instance = new TaskHistoryStatus4Fail();

    private TaskHistoryStatus4Fail() {
    }

    public static TaskHistoryStatus getInstance() {
        return instance;
    }
}
