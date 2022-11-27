package com.example.elobit.mail.mailRepo;

import com.example.elobit.mail.models.Mail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalTime;

public interface MailRepo extends CrudRepository<Mail, String> {
    Mail findByMail(String Mail);

    @Query(value = "SELECT u FROM Mail u WHERE u.time = ?1")
    Mail findExpiredCode(LocalTime localTime);
}
