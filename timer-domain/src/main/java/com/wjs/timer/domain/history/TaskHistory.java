package com.wjs.timer.domain.history;

import com.wjs.common.base.base.BaseEntity;
import com.wjs.timer.domain.history.status.TaskHistoryStatus;
import com.wjs.timer.domain.history.status.TaskHistoryStatus4Init;
import com.wjs.timer.domain.task.Task;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class TaskHistory extends BaseEntity<TaskHistoryStatus> {
    private Task task;
    private Integer statusCode;
    private Long execTime;
    private String execResult;
    private String url;

    public TaskHistory() {

    }

    public TaskHistory(String result, Task task, long time) {
        this.execResult = result;
        this.execTime = time;
        this.task = task;
        this.statusCode = task.getStatusCode();
        this.url = task.getUrl();
        this.status = TaskHistoryStatus4Init.getInstance();
    }

    public void execSucc() {
        status = status.succ();
    }
}
