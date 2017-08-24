package com.wxs.schedule.task;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * <p>ScheduleTask</p>
 *
 * @author wuxinshui
 */
@Component
public class ScheduleTask {
    @Scheduled(cron = "0/1 * * * * ?")
    public void printSay() {
        System.out.println("This is a say method!"+new Date());
    }
}
