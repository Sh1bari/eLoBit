package com.example.elobit.automation;

import com.example.elobit.mail.mailRepo.MailRepo;
import com.example.elobit.mail.mailService.EmailServiceImpl;
import com.example.elobit.mail.models.Mail;
import com.example.elobit.models.entity.Tasks;
import com.example.elobit.repo.TasksRepo;
import com.example.elobit.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Configuration
@EnableAsync
public class MailTasksSender {
    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private TasksRepo tasksRepo;

    /**
     * @author Vladimir Krasnov
     * рассылка сообщений
     */
    @Async
    @Scheduled(cron = "*/1 * * * * *")
    @ConditionalOnProperty(name="scheduler.enabled")
    public void scheduleFixedRateTaskAsync() {
        long curMil1 = System.currentTimeMillis()/1000;

        String curMil = Long.toString(curMil1) + "000";
        if(tasksRepo.existsByTimeOfAlert(curMil)){
            List<Tasks> tasks = tasksRepo.findByTimeOfAlert(curMil);
            for (Tasks task : tasks) {
                if(task.getStatus().equals("in progress")) {
                    emailService.sendSimpleMessage(task.getMail(), "Оповещение о задаче",
                            "Здравствуйте, у вас есть невыполненное задание: " + task.getTitle() + "\nОписание: " + task.getText() + "\nУспейте к "  + task.getTime() + "\n\nУдачного дня!");
                }
            }
        }

    }
}
