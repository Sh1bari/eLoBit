package com.example.elobit.controllers;

import com.example.elobit.mail.EmailServiceImpl;
import com.example.elobit.mail.mailService.EmailService;
import com.example.elobit.models.entity.Users;
import com.example.elobit.models.response.AuthorisationAnswer;
import com.example.elobit.models.response.RegistrationAnswer;
import com.example.elobit.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class AuthorisationController {
    @Autowired
    private UsersRepo usersRepo;

    /**
     * @author Vladimir Krasnov
     * @param user входные параметры username и password
     * @return модель AuthorisationAnswer с ответами correct/incorrect
     *
     * проверка на существование пользователя и сопоставление с паролем
     */
    @PostMapping("/authorisation/login")
    private AuthorisationAnswer authorisationLogin(@RequestBody Users user){
        AuthorisationAnswer answer = new AuthorisationAnswer();
        if (!usersRepo.existsByUsername(user.getUsername())){
            answer.setUsernameAnswer("incorrect");
        }else{
            answer.setUsernameAnswer("correct");
            if(!user.getPassword().equals(usersRepo.findByUsername(user.getUsername()).getPassword())){
                answer.setPasswordAnswer("incorrect");
            } else{
                answer.setPasswordAnswer("correct");
            }
        }
        return answer;
    }

    /**
     * @author Vladimir Krasnov
     * @param user входные параметры username и password;
     * @return status: denied/success
     * success - добавление пользователя в БД
     */
    @PostMapping("/registration")
    private RegistrationAnswer registration(@RequestBody Users user){
        RegistrationAnswer registrationAnswer = new RegistrationAnswer();
        if(usersRepo.existsByUsername(user.getUsername())){
            registrationAnswer.setStatus("denied");
        }else{
            registrationAnswer.setStatus("success");
            usersRepo.save(user);
        }
        return registrationAnswer;
    }
    @Autowired
    private EmailService emailService;

    @GetMapping("/")
    private void test(){
        emailService.sendSimpleMessage(
                "vova_krasnov_2004@mail.ru",
                "АЗАТ Я В АХУЕ",
                "АЗАТ Я В АХУЕ");
    }
}