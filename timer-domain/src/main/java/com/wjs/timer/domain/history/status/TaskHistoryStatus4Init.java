package com.wjs.timer.domain.history.status;

import com.wjs.common.base.annotation.StatusAndClassNum;
import com.wjs.timer.domain.history.TaskHistory;

import static com.wjs.timer.api.dto.TaskHistoryDTO.STATUS_TYPE_INIT;

@StatusAndClassNum(superClass = TaskHistoryStatus.class, number = STATUS_TYPE_INIT, describe = "初始状态", parasitClass = TaskHistory.class)
public class TaskHistoryStatus4Init extends TaskHistoryStatus {

    private static TaskHistoryStatus instance = new TaskHistoryStatus4Init();

    private TaskHistoryStatus4Init() {
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

    @Override
    public TaskHistoryStatus unknown() {
        return TaskHistoryStatus4Unknown.getInstance();
    }
}
