package com.chenhao.admin.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Lazy(false)
public class SimpleJobA {

    private static final Logger logger = LoggerFactory.getLogger(SimpleJobA.class);


    @Scheduled(cron = "0/30 * * * * ?")
    public void process() {
        String time = System.currentTimeMillis() + "";

        logger.info(time + ": " + "A执行任务开始...");
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            logger.info(time + ": " + "A执行任务结束...");
        }
    }

}