package com.example.elobit.automation;

import com.example.elobit.mail.mailRepo.MailRepo;
import com.example.elobit.mail.models.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalTime;

@Configuration
@EnableAsync
public class MailCodeDeleter {

    @Autowired
    private MailRepo mailRepo;

//    @Async
//    @Scheduled(cron = "* */1 * * * *")
//    @ConditionalOnProperty(name="scheduler.enabled", matchIfMissing = true)
//    public void scheduleFixedRateTaskAsync() {
//        LocalTime time = LocalTime.now();
//        Mail mail = new Mail();
//        mail.setMail(null);
//        mail = mailRepo.findExpiredCode(time);
//        if(mail.getMail() != null) {
//            mailRepo.deleteById(mail.getMail());
//        }
//    }
}
