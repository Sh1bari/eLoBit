package com.example.elobit.controllers;

import com.example.elobit.models.entity.Notices;
import com.example.elobit.models.entity.Users;
import com.example.elobit.models.response.Status;
import com.example.elobit.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class NoticesController {

    @Autowired
    private UsersRepo usersRepo;

    /**
     *
     * @param username имя пользователя из cookie
     * @param notices поиск по полям year and month
     * @return List<Notices> из всех заметок пользователя по году и месяцу
     */
    @PostMapping("/notices/show/{username}")
    private List<Notices> showNotices(@PathVariable String username, @RequestBody Notices notices){
        Users user = usersRepo.findByUsername(username);
        String year = notices.getYear();
        String month = notices.getMonth();
        List<Notices> userNotices = user.getNotices().stream()
                .filter(o -> o.getYear().equals(year))
                .filter(o -> o.getMonth().equals(month))
                .collect(Collectors.toList());
        return userNotices;
    }

    @PostMapping("/notices/add/{username}")
    private Status addNotices(@PathVariable String username, @RequestBody Notices notices){
        Status status = new Status("success");
        try {
            notices.setId(0);
            Users user = usersRepo.findByUsername(username);
            user.getNotices().add(notices);
            usersRepo.save(user);
        }catch (Exception exception){
            status.setStatus("denied");
        }
        return status;
    }
}
