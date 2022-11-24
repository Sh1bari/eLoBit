package com.example.elobit.controllers.rest;

import com.example.elobit.models.entity.UserAll;
import com.example.elobit.models.response.AuthorisationAnswer;
import com.example.elobit.models.response.RegistrationAnswer;
import com.example.elobit.models.response.SessionUsername;
import com.example.elobit.repo.UserAllRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@CrossOrigin
@RestController
public class TestController {
    @Autowired
    private UserAllRepo userAllRepo;

    @PostMapping("/authorisation/login")
    private AuthorisationAnswer authorisationLogin(@RequestBody UserAll user, HttpSession httpSession){
        AuthorisationAnswer answer = new AuthorisationAnswer();
        if (!userAllRepo.existsByUsername(user.getUsername())){
            answer.setUsernameAnswer("incorrect");
        }else{
            answer.setUsernameAnswer("correct");
            if(!user.getPassword().equals(userAllRepo.findByUsername(user.getUsername()).getPassword())){
                answer.setPasswordAnswer("incorrect");
            } else{
                answer.setPasswordAnswer("correct");
                httpSession.setAttribute("username", user.getUsername());
            }
        }
        return answer;
    }

    @GetMapping("/authorisation/session/getUsername")
    private SessionUsername sessionUsername(HttpSession httpSession){
        SessionUsername sessionUsername = new SessionUsername();
        sessionUsername.setSessionUsername((String) httpSession.getAttribute("username"));
        return sessionUsername;
    }

    @GetMapping("/authorisation/logout")
    private void authorisationLogout(HttpSession httpSession){
        httpSession.setAttribute("username", null);
    }

    @PostMapping("/registration")
    private RegistrationAnswer registration(@RequestBody UserAll user){
        RegistrationAnswer registrationAnswer = new RegistrationAnswer();
        if(!userAllRepo.existsByUsername(user.getUsername())){
            registrationAnswer.setUsernameAnswer("denied");
        }else{
            registrationAnswer.setUsernameAnswer("success");
            userAllRepo.save(user);
        }
        return registrationAnswer;
    }
}
