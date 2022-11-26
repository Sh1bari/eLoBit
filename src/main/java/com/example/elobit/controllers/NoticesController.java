package com.example.elobit.controllers;

import com.example.elobit.models.entity.Notices;
import com.example.elobit.models.entity.Users;
import com.example.elobit.models.response.Status;
import com.example.elobit.repo.NoticesRepo;
import com.example.elobit.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/notices")
public class NoticesController {

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private NoticesRepo noticesRepo;

    /**
     * @author Vladimir Krasnov
     * @param username имя пользователя из cookie
     * @param notices поиск по полям year и month
     * @return List<Notices> из всех заметок пользователя по году и месяцу
     */
    @PostMapping("/show/{username}")
    private List<Notices> showNotices(@PathVariable String username, @RequestBody Notices notices){
        Users user = usersRepo.findByUsername(username);
        String year = notices.getYear();
        String month = notices.getMonth();
        return user.getNotices().stream()
                .filter(o -> o.getYear().equals(year))
                .filter(o -> o.getMonth().equals(month))
                .collect(Collectors.toList());
    }

    /**
     * @author Vladimir Krasnov
     * @param username cookie username
     * @param notice входные данные модели Notices
     * @return status = success/denied
     * success - заметка добавлена
     */
    @PostMapping("/add/{username}")
    private Status addNotice(@PathVariable String username, @RequestBody Notices notice){
        Status status = new Status("success");
        try {
            notice.setId(0);
            Users user = usersRepo.findByUsername(username);
            user.getNotices().add(notice);
            usersRepo.save(user);
        }catch (Exception exception){
            status.setStatus("denied");
        }
        return status;
    }

    /**
     * @author Vladimir Krasnov
     * @param id id заметки для удаления из БД
     * @return status = success/denied
     * success - заметка удалена
     */
    @PostMapping("/delete/{id}")
    private Status deleteNotice(@PathVariable Integer id){
        Status status = new Status("success");
        try{
            noticesRepo.deleteById(id);
        }catch (Exception exception){
            status.setStatus("denied");
        }
        return status;
    }

    /**
     * @author Vladimir Krasnov
     * @param notice все поля модели Notices
     * @return status = success/denied
     * success - изменена
     * заметка изменяется по id на новую (рекоммендуемые изменяемые поля text и title)
     */
    @PostMapping("/change")
    private Status changeNotice(@RequestBody Notices notice){
        Status status = new Status("success");
        try{
            noticesRepo.save(notice);
        }catch (Exception exception){
            status.setStatus("denied");
        }
        return status;
    }
}
