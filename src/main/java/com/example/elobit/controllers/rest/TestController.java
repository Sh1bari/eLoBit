package com.example.elobit.controllers.rest;

import com.example.elobit.models.UserAuthorisation;
import com.example.elobit.repo.UserAuthorisationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class TestController {
    @Autowired
    private UserAuthorisationRepo userAuthorisationRepo;

    @GetMapping("/")
    private List<UserAuthorisation> home(){
        List<UserAuthorisation> user = (List<UserAuthorisation>) userAuthorisationRepo.findAll();
        return user;
    }
}
