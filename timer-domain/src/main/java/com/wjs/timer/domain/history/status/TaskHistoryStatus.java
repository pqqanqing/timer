package com.wjs.timer.domain.history.status;

import com.wjs.common.base.base.BaseObject;

public abstract class TaskHistoryStatus extends BaseObject {

    public TaskHistoryStatus init() {
        throw new RuntimeException("当前状态[" + this + "]不能转换为初始状态!");
    }

    public TaskHistoryStatus succ() {
        throw new RuntimeException("当前状态[" + this + "]不能转换为成功状态!");
    }

    public TaskHistoryStatus fail() {
        throw new RuntimeException("当前状态[" + this + "]不能转换为失败状态!");
    }

    public TaskHistoryStatus unknown() {
        throw new RuntimeException("当前状态[" + this + "]不能转换为未知状态!");
    }
}
