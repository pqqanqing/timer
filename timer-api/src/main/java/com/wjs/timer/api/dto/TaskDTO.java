package com.wjs.timer.api.dto;

import com.wjs.common.base.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TaskDTO extends BaseDTO {
    /**
     * 所属模块
     */
    private ModuleDTO module;
    /**
     * 调用url
     */
    private String url;
    /**
     * 业务码
     */
    private String code;
    /**
     * 超时时间
     */
    private Integer overtime;
    /**
     * cron表达式
     */
    private String cronExpression;
    /**
     * 触发器组
     */
    private String triggerGroup;
    /**
     * 触发器名
     */
    private String triggerName;
    /**
     * job组
     */
    private String jobGroup;
    /**
     * job名
     */
    private String jobName;
    /**
     * 调用请求方式值
     */
    private String requestVal;

    public TaskDTO() {
    }
}
