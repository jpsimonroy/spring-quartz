package org.opensource.spring;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.List;
import java.util.Map;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class SpringQuartzScheduler implements InitializingBean, DisposableBean {

    private StdSchedulerFactory schedulerFactory;
    private List<Map<String, QuartzJobHolder>> jobSchedule;


    public void setSchedulerFactory(StdSchedulerFactory schedulerFactory) {
        this.schedulerFactory = schedulerFactory;
    }

    public void setJobSchedule(List<Map<String, QuartzJobHolder>> jobSchedule) {
        this.jobSchedule = jobSchedule;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Scheduler defaultScheduler = StdSchedulerFactory.getDefaultScheduler();
        jobSchedule.stream().forEach(f -> {
            QuartzJobHolder jobHolder = f.get("jobHolder");
            try {
                defaultScheduler.scheduleJob(newJob(jobHolder.getClass())
                        .withIdentity(jobHolder.getName() + "-job", "spring-batch")
                        .setJobData(jobHolder.getJobDataMap())
                        .build(), newTrigger()
                        .withIdentity(jobHolder.getName() + "-trigger")
                        .withSchedule(jobHolder.getSchedule().getScheduleBuilder())
                        .build());
            } catch (SchedulerException e) {
                throw new RuntimeException(e);
            }

        });
        schedulerFactory.getDefaultScheduler().start();
    }

    @Override
    public void destroy() throws Exception {
        schedulerFactory.getDefaultScheduler().shutdown();
    }
}
