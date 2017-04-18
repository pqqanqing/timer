package com.wjs.timer.api.dto;

import com.wjs.common.base.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
public class SchedulerDTO extends BaseDTO {

    /**
     * 触发组
     */
    private String triggerGroup;
    /**
     * 触发名
     */
    private String triggerName;
    /**
     * 触发状态
     */
    private String triggerState;
    /**
     * 下次激起时间
     */
    private Timestamp nextFireTime;
    /**
     * 上次激起时间
     */
    private Timestamp prevFireTime;
    /**
     * 开始时间
     */
    private Timestamp startTime;
    /**
     * 结束时间
     */
    private Timestamp endTime;

    public SchedulerDTO() {
    }
}
