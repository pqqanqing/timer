package com.wjs.timer.rpt.task;

import com.wjs.common.base.rpt.BaseRptImpl;
import com.wjs.timer.domain.task.Task;
import com.wjs.timer.domain.task.TaskRpt;
import org.hibernate.Query;
import org.quartz.TriggerKey;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class TaskRptImpl extends BaseRptImpl<Task, Serializable> implements TaskRpt {
    @Override
    public Task queryByTriggerKey(TriggerKey triggerKey) {
        String hql = "from Task t where t.triggerName = :triggerName and t.triggerGroup = :triggerGroup";
        Query query = getSession().createQuery(hql);
        query.setParameter("triggerName", triggerKey.getName());
        query.setParameter("triggerGroup", triggerKey.getGroup());
        return (Task) query.uniqueResult();
    }
}
