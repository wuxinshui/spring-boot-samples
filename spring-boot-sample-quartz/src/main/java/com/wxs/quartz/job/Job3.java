package com.wxs.quartz.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>Job3</p>
 *
 * @author wuxinshui
 */
@DisallowConcurrentExecution
public class Job3 implements Job {
    private static List<Integer> list = new ArrayList<>();

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("This is a test for concurrent--" + new Date());
        try {
            print();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void print() throws InterruptedException {
        synchronized (this) {
            list.add(1);
            list.add(2);
            Thread.sleep(10000);
            list.remove(1);
            list.add(3);
            list.add(4);

            System.out.println(list.toString() + "---" + new Date());
        }
    }
}
