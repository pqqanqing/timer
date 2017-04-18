package com.wjs.timer.domain.history.status;

import com.wjs.common.base.annotation.StatusAndClassNum;
import com.wjs.timer.domain.history.TaskHistory;

import static com.wjs.timer.api.dto.TaskHistoryDTO.STATUS_TYPE_UNKNOW;

@StatusAndClassNum(superClass = TaskHistoryStatus.class, number = STATUS_TYPE_UNKNOW, describe = "未知状态", parasitClass = TaskHistory.class)
public class TaskHistoryStatus4Unknown extends TaskHistoryStatus {
    private static TaskHistoryStatus instance = new TaskHistoryStatus4Unknown();

    private TaskHistoryStatus4Unknown() {
    }

    public static TaskHistoryStatus getInstance() {
        return instance;
    }

    @Override
    public TaskHistoryStatus succ() {
        return TaskHistoryStatus4Succ.getInstance();
    }

    @Override
    public TaskHistoryStatus fail() {
        return TaskHistoryStatus4Fail.getInstance();
    }
}
