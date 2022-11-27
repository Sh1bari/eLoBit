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
import java.util.List;

@Configuration
@EnableAsync
public class MailCodeDeleter {

    @Autowired
    private MailRepo mailRepo;

    /**
     * @author Vladimir Krasnov
     * очистка ненужных ключей
     */
    @Async
    @Scheduled(cron = "0 */1 * ? * *")
    @ConditionalOnProperty(name="scheduler.enabled")
    public void scheduleFixedRateTaskAsync() {
        LocalTime time = LocalTime.now().withSecond(0).withNano(0);
        if(mailRepo.existsByTime(time)){
            List<Mail> mails = mailRepo.findByTime(time);
            for (Mail mail : mails) {
                mailRepo.deleteById(mail.getMail());
            }
        }
    }
}
