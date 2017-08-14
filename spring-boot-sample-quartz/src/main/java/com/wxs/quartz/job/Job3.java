package com.wxs.quartz.job;

import org.quartz.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>Job3</p>
 *
 * @author wuxinshui
 */
@DisallowConcurrentExecution
public class Job3 implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("This is a test for concurrent--"+new Date());
        try {
            print();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public  void print() throws InterruptedException {
        synchronized (this) {
            List<Integer> list=new ArrayList<>();
            list.add(1);
            list.add(2);
            Thread.sleep(10000);
            list.remove(1);
            list.add(3);
            list.add(4);

            System.out.println(list.toString()+"---"+new Date());
        }
    }
}
