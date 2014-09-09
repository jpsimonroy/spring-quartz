package org.spring_quartz.schedulling;

import org.quartz.ScheduleBuilder;
import org.quartz.Trigger;

public interface QuartzScheduleBuilder {
    public ScheduleBuilder<? extends Trigger> getScheduleBuilder();
}
