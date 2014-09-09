package org.spring_quartz.schedulling;

import org.quartz.CronExpression;
import org.quartz.ScheduleBuilder;
import org.quartz.Trigger;
import org.springframework.beans.factory.InitializingBean;

import static org.quartz.CronScheduleBuilder.cronSchedule;

public class CronScheduleBuilder implements QuartzScheduleBuilder, InitializingBean {
    private String schedule;
    private ScheduleBuilder<? extends Trigger> scheduleBuilder;

    @Override
    public ScheduleBuilder<? extends Trigger> getScheduleBuilder() {
        return scheduleBuilder;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (!CronExpression.isValidExpression(schedule))
            throw new RuntimeException("Cron Expression is not valid");
        this.scheduleBuilder = cronSchedule(schedule);
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
}
