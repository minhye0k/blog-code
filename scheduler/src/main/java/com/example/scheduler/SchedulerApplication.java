package com.example.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDateTime;

import static com.example.scheduler.DateTimeFormatterConstant.DATE_TIME_FORMATTER;

@Slf4j
@EnableScheduling
@SpringBootApplication
public class SchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchedulerApplication.class, args);
		log.info(">>>>>서버 시작 시간 {}", LocalDateTime.now().format(DATE_TIME_FORMATTER));
	}
}
