package com.example.scheduler;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.example.scheduler.DateTimeFormatterConstant.DATE_TIME_FORMATTER;

@Slf4j
@Component
public class SchedulerExampleJob {
    private static final String CRON_EXPRESSION_EXAMPLE = "30 * * * * *";

    @SchedulerLock(name = "cron_lock", lockAtLeastFor = "20s", lockAtMostFor = "50s")
    @Scheduled(cron = CRON_EXPRESSION_EXAMPLE)
    public void cron() {
        log.info(">>>>>시작 시간 {}", LocalDateTime.now().format(DATE_TIME_FORMATTER));
        log.info(">>>>>get shed lock {}", LocalDateTime.now().format(DATE_TIME_FORMATTER));
    }

//    @Scheduled(fixedRate = 3000)
    public void fixedRate1() throws InterruptedException {
        log.info(">>>>>fixedRate1 시작 시간 {}", LocalDateTime.now().format(DATE_TIME_FORMATTER));
        Thread.sleep(5000);
    }

//    @Scheduled(fixedRate = 3000)
    public void fixedRate2() throws InterruptedException {
        log.info(">>>>>fixedRate2 시작 시간 {}", LocalDateTime.now().format(DATE_TIME_FORMATTER));
        Thread.sleep(5000);
    }

//    @Scheduled(fixedDelay = 3000)
    public void fixedDelay() throws InterruptedException {
        log.info(">>>>>시작 시간 {}", LocalDateTime.now().format(DATE_TIME_FORMATTER));
        Thread.sleep(5000);
        log.info(">>>>>종료 {}", LocalDateTime.now().format(DATE_TIME_FORMATTER));
    }

//    @Scheduled(fixedRate = 2000, initialDelay = 3000)
    public void initialDelayWithFixedRate() {
        log.info(">>>>>실행 시간 {}", LocalDateTime.now().format(DATE_TIME_FORMATTER));
    }

//    @Scheduled(fixedDelay = 2000, initialDelay = 3000)
    public void initialDelayWithFixedDelay() {
        log.info(">>>>>실행 시간 {}", LocalDateTime.now().format(DATE_TIME_FORMATTER));
    }


}
