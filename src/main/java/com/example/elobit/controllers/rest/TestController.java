package com.example.elobit.controllers.rest;

import com.example.elobit.models.entity.UserAuthorisation;
import com.example.elobit.models.response.AuthorisationAnswer;
import com.example.elobit.repo.UserAuthorisationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@CrossOrigin
@RestController
public class TestController {
    @Autowired
    private UserAuthorisationRepo userAuthorisationRepo;

    @PostMapping("/authorisation/login")
    private AuthorisationAnswer authorisation(@RequestBody UserAuthorisation userAuthorisation, HttpSession httpSession){
        AuthorisationAnswer answer = new AuthorisationAnswer();
        if (!userAuthorisationRepo.existsByUsername(userAuthorisation.getUsername())){
            answer.setUsernameAnswer("incorrect");
        }else{
            answer.setUsernameAnswer("correct");
            if(!userAuthorisation.getPassword().equals(userAuthorisationRepo.findByUsername(userAuthorisation.getUsername()).getPassword())){
                answer.setPasswordAnswer("incorrect");
            } else{
                answer.setPasswordAnswer("correct");
                httpSession.setAttribute("username", userAuthorisation.getUsername());
            }
        }
        return answer;
    }
}
