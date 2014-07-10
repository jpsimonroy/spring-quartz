package org.opensource.schedulling;

import org.quartz.ScheduleBuilder;
import org.quartz.Trigger;

public interface QuartzScheduleBuilder {
    public ScheduleBuilder<? extends Trigger> getScheduleBuilder();
}
