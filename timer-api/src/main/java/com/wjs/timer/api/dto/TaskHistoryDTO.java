package com.wjs.timer.api.dto;

import com.wjs.common.base.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TaskHistoryDTO extends BaseDTO {
    /**
     * 初始化
     */
    public static final String STATUS_TYPE_INIT = "1";
    /**
     * 成功
     */
    public static final String STATUS_TYPE_SUCC = "2";
    /**
     * 失败
     */
    public static final String STATUS_TYPE_FAIL = "3";
    /**
     * 未知
     */
    public static final String STATUS_TYPE_UNKNOW = "4";

    /**
     * 所属task
     */
    private TaskDTO task;
    /**
     * 状态码
     */
    private Integer statusCode;
    /**
     * 执行时间
     */
    private Long execTime;
    /**
     * 执行结果
     */
    private String execResult;

    public TaskHistoryDTO() {
    }
}
