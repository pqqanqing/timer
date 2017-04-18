package com.wjs.timer.rpt.scheduler;

import com.wjs.common.base.rpt.BaseRptImpl;
import com.wjs.timer.api.dto.SchedulerDTO;
import com.wjs.timer.domain.scheduler.Scheduler;
import com.wjs.timer.domain.scheduler.SchedulerRpt;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public class SchedulerRptImpl extends BaseRptImpl<Scheduler, Serializable> implements SchedulerRpt {

    @Override
    public List<SchedulerDTO> queryAll() {
        String sql = "SELECT T.TRIGGER_GROUP AS triggerGroup,T.TRIGGER_NAME AS triggerName,from_unixtime(T.START_TIME/1000) AS startTime,from_unixtime(T.NEXT_FIRE_TIME/1000) AS nextFireTime,from_unixtime(T.PREV_FIRE_TIME/1000) AS prevFireTime,from_unixtime(T.END_TIME/1000) AS endTime,T.TRIGGER_STATE AS triggerState FROM TB_QRTZ_TRIGGERS T ORDER BY START_TIME";
        Query query = getSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(SchedulerDTO.class));
        return query.list();
    }
}
