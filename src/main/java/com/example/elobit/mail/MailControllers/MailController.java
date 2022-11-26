package com.example.elobit.mail.MailControllers;

import com.example.elobit.mail.mailRepo.MailRepo;
import com.example.elobit.mail.mailService.EmailServiceInterface;
import com.example.elobit.mail.models.Mail;
import com.example.elobit.models.entity.Users;
import com.example.elobit.models.response.RegistrationAnswer;
import com.example.elobit.models.response.Status;
import com.example.elobit.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/mail")
public class MailController {
    @Autowired
    private EmailServiceInterface emailService;
    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private MailRepo mailRepo;

    /**
     * @author Vladimir Krasnov
     * @param user входные параметры username, password, mail
     *             по mail отправляется code из 6 цифр
     * @return status: mail exists/username exists/success
     * success - отправление кода на почту
     */
    @PostMapping("/registration")
    private RegistrationAnswer registration(@RequestBody Users user){
        RegistrationAnswer registrationAnswer = new RegistrationAnswer();
        if(usersRepo.existsByMail(user.getMail())){
            registrationAnswer.setStatus("mail exists");
        } else if(usersRepo.existsByUsername(user.getUsername())){
            registrationAnswer.setStatus("username exists");
        }else{
            registrationAnswer.setStatus("success");
            String code = Integer.toString(100000 + (int) (Math.random() * 899999));
            Mail mail = new Mail(user.getUsername(), code);
            mailRepo.save(mail);
            emailService.sendSimpleMessage(user.getMail(),"Регистрация на сайте Bauman Organaizer", "Ваш код подтверждения: " + code);
        }
        return registrationAnswer;
    }

    /**
     * @author Vladimir Krasnov
     * @param user входные данные username, mail, password
     * @param code код из смс
     * @return status: denied/success
     * success - добавление пользователя в БД
     */
    @PostMapping("/registration/code/{code}")
    private Status registrationCode(@RequestBody Users user, @PathVariable String code){
        Status status = new Status("denied");
        if(mailRepo.findByUsername(user.getUsername()).getCode().equals(code)){
            usersRepo.save(user);
            status.setStatus("success");
            mailRepo.deleteById(user.getUsername());
        }
        return status;
    }

}
