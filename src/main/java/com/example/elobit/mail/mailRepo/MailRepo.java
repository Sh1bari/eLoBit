package com.example.elobit.mail.mailRepo;

import com.example.elobit.mail.models.Mail;
import org.springframework.data.repository.CrudRepository;

public interface MailRepo extends CrudRepository<Mail, String> {
    Mail findByMail(String Mail);
}
