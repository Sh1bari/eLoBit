package com.example.elobit.automation;

import com.example.elobit.mail.mailRepo.MailRepo;
import com.example.elobit.mail.models.Mail;
import com.example.elobit.repo.TasksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Configuration
@EnableAsync
public class MailTasksSender {
    @Autowired
    private MailRepo mailRepo;

    @Autowired
    private TasksRepo tasksRepo;

    @Async
    @Scheduled(cron = "*/1 * * * * *")
    @ConditionalOnProperty(name="scheduler.enabled")
    public void scheduleFixedRateTaskAsync() {
        //LocalTime time = LocalTime.now().withSecond(0).withNano(0);
        Date date = new Date();
        String convertedDate = date.toString();
        if(tasksRepo.existsByTimeOfAlert(convertedDate)){

        }







    }
}
