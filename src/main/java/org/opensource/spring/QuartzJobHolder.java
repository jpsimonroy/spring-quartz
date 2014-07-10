package org.opensource.spring;


import org.opensource.schedulling.QuartzScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.BeanNameAware;

public class QuartzJobHolder implements Job, BeanNameAware {

    private String name;
    private JobDataMap jobDataMap;
    private QuartzScheduleBuilder schedule;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        ((Job) context.getJobDetail().getJobDataMap().get("job")).execute(context);
    }

    @Override
    public void setBeanName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setJobDataMap(JobDataMap jobDataMap) {
        this.jobDataMap = jobDataMap;
    }

    public JobDataMap getJobDataMap() {
        return jobDataMap;
    }

    public QuartzScheduleBuilder getSchedule() {
        return schedule;
    }

    public void setScheduleBuilder(QuartzScheduleBuilder schedule) {
        this.schedule = schedule;
    }
}
