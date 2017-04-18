package com.wjs.timer.domain.scheduler;

import com.wjs.common.base.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;

@Setter
@Getter
@ToString
public class Scheduler extends BaseEntity {
    private String triggerGroup;
    private String triggerName;
    private BigInteger nextFireTime;
    private BigInteger prevFireTime;
    private BigInteger startTime;
    private BigInteger endTime;
    private String triggerState;

    public Scheduler() {
    }

}
