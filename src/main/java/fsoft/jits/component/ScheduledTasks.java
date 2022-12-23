package fsoft.jits.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    //@Scheduled(fixedRate = 2000)
    public void scheduleTaskWithFixedRate() {
    	logger.info("Send email to producers to inform quantity sold items");
    }

    //@Scheduled(fixedDelay = 10000)
    public void scheduleTaskWithFixedDelay() {
    	try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    	logger.info("Send email to producers to inform quantity sold items");
    }

    // initial delay in times after project deploy before run method
    //@Scheduled(fixedRate = 2000, initialDelay = 10000)
    public void scheduleTaskWithInitialDelay() {
    }

    //web generate cron: https://www.freeformatter.com/cron-expression-generator-quartz.html
    // 0 0 8 1 * ? * : 8h first day every month
    //@Scheduled(cron = "15 * * * * ?")
    public void scheduleTaskWithCronExpression() {
    	logger.info("Send email to producers to inform quantity sold items");
    }

}
