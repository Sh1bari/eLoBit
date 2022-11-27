package com.example.elobit.mail.MailControllers;

import com.example.elobit.mail.mailRepo.MailRepo;
import com.example.elobit.mail.mailService.EmailServiceInterface;
import com.example.elobit.mail.models.Mail;
import com.example.elobit.models.entity.Users;
import com.example.elobit.models.response.Status;
import com.example.elobit.models.samples.SampleUser;
import com.example.elobit.passwordEncoder.PasswordEncoder;
import com.example.elobit.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalTime;

@CrossOrigin
@RestController
@RequestMapping("/mail/forgotPassword")
public class ForgotPasswordController {

    @Autowired
    private EmailServiceInterface emailService;
    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private MailRepo mailRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * @author Vladimir Krasnov
     * @param sampleUser входные данные mail
     * @return status = mail exists/success
     * success - создали заявку на проверку кода
     */
    @PostMapping("/form")
    private Status forgotPasswordForm(@RequestBody SampleUser sampleUser){
        Status status = new Status();
        if(!usersRepo.existsByMail(sampleUser.getMail())){
            status.setStatus("mail not exists");
        }else {
            String code = Integer.toString(100000 + (int) (Math.random() * 899999));
            Mail mail = new Mail();
            mail.setMail(sampleUser.getMail());
            mail.setCode(code);
            LocalTime time = LocalTime.now().plusMinutes(5).withNano(0).withSecond(0);
            mail.setTime(time);
            mailRepo.save(mail);
            status.setStatus("success");
        }
        return status;
    }

    /**
     * @author Vladimir Krasnov
     * @param sampleUser входные данные mail
     */
    @PostMapping("/sendCode")
    private void forgotPasswordSendCode(@RequestBody SampleUser sampleUser){
        try {
            String code = mailRepo.findByMail(sampleUser.getMail()).getCode();
            emailService.sendSimpleMessage(sampleUser.getMail(), "Восстановление пароля на сайте Bauman Organaizer", "Ваш код для сброса пароля: " + code);
        }catch (Exception exception){
            System.out.println("Ошибка в почтовом адрессе" + sampleUser.getMail());
        }
    }

    /**
     * @author Vladimir Krasnov
     * @param sampleUser входные данные mail, newPassword
     * @param code код из смс
     * @return status: denied/success
     * success - добавление пользователя в БД
     */
    @PostMapping("/checkCode/{code}")
    private Status forgotPasswordRegistrationCode(@RequestBody SampleUser sampleUser, @PathVariable String code){
        Status status = new Status("denied");
        if(mailRepo.findByMail(sampleUser.getMail()).getCode().equals(code)){
            String password = sampleUser.getNewPassword();
            sampleUser.setNewPassword(passwordEncoder.encode(password));
            Users users = usersRepo.findByMail(sampleUser.getMail());
            users.setPassword(sampleUser.getNewPassword());
            usersRepo.save(users);
            status.setStatus("success");
            mailRepo.deleteById(sampleUser.getMail());
        }
        return status;
    }
}
