package com.wxs.schedule.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * <p>ScheduleTask</p>
 *
 * @author wuxinshui
 */
@Component
public class ScheduleTask {
    @Scheduled(cron = "0/1 0 0 * * ?")
    public void printSay() {
        System.out.println("This is a say method!");
    }

}
