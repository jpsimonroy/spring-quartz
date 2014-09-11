package examples;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.stream.LongStream;

public class ISayHiEvery10Seconds implements Job {
    private Long times;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LongStream.range(0, times).forEach(f -> System.out.println("Hi There"));
    }

    public void setTimes(Long times) {
        this.times = times;
    }
}
