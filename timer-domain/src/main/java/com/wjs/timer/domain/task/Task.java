package com.wjs.timer.domain.task;

import com.wjs.common.base.annotation.StatusAndClassNum;
import com.wjs.common.base.base.BaseEntity;
import com.wjs.common.base.execption.BusinessExecption;
import com.wjs.common.base.request.Request;
import com.wjs.common.base.util.ScanClassUtil;
import com.wjs.timer.api.dto.TaskDTO;
import com.wjs.timer.domain.history.TaskHistory;
import com.wjs.timer.domain.module.Module;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.quartz.TriggerKey;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.core.annotation.AnnotationUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static com.wjs.common.base.util.BeanPropertiesUtil.copyProperties;
import static java.util.Collections.unmodifiableList;
import static org.quartz.CronExpression.isValidExpression;

@Setter
@Getter
@ToString
public class Task extends BaseEntity {
    private Module module;
    private String url;
    private String code;
    private Integer overtime;
    private String cronExpression;
    /**
     * the name must be unique within the group
     */
    private String triggerGroup;
    private String triggerName;
    private String jobGroup;
    private String jobName;
    /**
     * 不同的策略,不同的调用方式,应用策略模式
     */
    private Request request;
    private String requestVal;
    private List<TaskHistory> taskHistorys = new ArrayList<>();

    public Task() {
    }

    public Task(TaskDTO taskDTO, Module module) {
        this.module = module;
        setProperties(taskDTO);
    }

    private void setProperties(TaskDTO taskDTO) {
        setBaseProperties(taskDTO);
        this.url = taskDTO.getUrl();
        this.code = taskDTO.getCode();
        this.overtime = taskDTO.getOvertime();
        this.cronExpression = taskDTO.getCronExpression();
        this.triggerGroup = taskDTO.getTriggerGroup();
        this.triggerName = taskDTO.getTriggerName();
        this.jobGroup = taskDTO.getJobGroup();
        this.jobName = taskDTO.getJobName();
        this.request = (Request) ScanClassUtil.getClassMap().get(Request.class + taskDTO.getRequestVal());
    }

    public CronTriggerImpl getCronTriggerImpl() {
        if (!isValidExpression(cronExpression)) throw new BusinessExecption("timer1", cronExpression);
        CronTriggerImpl cronTrigger = new CronTriggerImpl();
        try {
            cronTrigger.setCronExpression(cronExpression);
            cronTrigger.setKey(getTriggerKey());
            cronTrigger.setJobGroup(jobGroup);
            cronTrigger.setJobName(jobName);
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return cronTrigger;
    }

    public TriggerKey getTriggerKey() {
        return new TriggerKey(triggerName, triggerGroup);
    }

    public boolean isSerialJob(String name) {
        return jobName.equals(name);
    }

    public void exec() {
        Long l1 = System.currentTimeMillis();
        String result = request.defaultExec(url);
        Long l2 = System.currentTimeMillis();
        TaskHistory taskHistory = new TaskHistory(result, this, l2 - l1);
        taskHistory.execSucc();
        addTaskHistory(taskHistory);
    }

    private void addTaskHistory(TaskHistory taskHistory) {
        this.taskHistorys.add(taskHistory);
    }

    public int getStatusCode() {
        return request.getStatusCode();
    }

    public String getRequestVal() {
        return AnnotationUtils.findAnnotation(request.getClass(), StatusAndClassNum.class).number();
    }

    public List<TaskHistory> getTaskHistorys() {
        return unmodifiableList(taskHistorys);
    }

    public TaskDTO makeDTO() {
        return copyProperties(this, TaskDTO.class);
    }
}
