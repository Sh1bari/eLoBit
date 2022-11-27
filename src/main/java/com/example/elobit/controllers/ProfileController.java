package com.example.elobit.controllers;

import com.example.elobit.models.entity.Users;
import com.example.elobit.models.response.Statistic;
import com.example.elobit.models.response.Status;
import com.example.elobit.models.samples.SampleUser;
import com.example.elobit.passwordEncoder.PasswordEncoder;
import com.example.elobit.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * @author Vladimir Krasnov
     * @param sampleUser входные данные username, newUsername
     * @return status = success
     */
    @PostMapping("/changeUsername/")
    private Status changeUsername(@RequestBody SampleUser sampleUser){
        Status status = new Status("username exists");
        if(!usersRepo.existsByUsername(sampleUser.getNewUsername())) {
            Users user = usersRepo.findByUsername(sampleUser.getUsername());
            user.setUsername(sampleUser.getNewUsername());
            usersRepo.save(user);
            status.setStatus("success");
        }
        return status;
    }

    /**
     * @author Vladimir Krasnov
     * @param sampleUser входные данные username, newPassword
     * @return status = success
     */
    @PostMapping("/changePassword")
    private Status changePassword(@RequestBody SampleUser sampleUser){
        Users users = usersRepo.findByUsername(sampleUser.getUsername());
        users.setPassword(passwordEncoder.encode(sampleUser.getNewPassword()));
        usersRepo.save(users);
        return new Status("success");
    }

    @PostMapping("/findAll")
    private Statistic stats(@RequestBody SampleUser sampleUser){
        Users user = usersRepo.findByMail(sampleUser.getUsername());
        Statistic stats = new Statistic();
        int notices;
        int tasks;

        notices = user.getNotices().size();
        stats.setNotices(Integer.toString(notices));

        tasks = user.getTasks().size();
        stats.setTasks(Integer.toString(tasks));


        return stats;
    }

}
