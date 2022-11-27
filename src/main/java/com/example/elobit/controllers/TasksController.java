package com.example.elobit.controllers;

import com.example.elobit.models.entity.Tasks;
import com.example.elobit.models.entity.Users;
import com.example.elobit.models.response.Status;
import com.example.elobit.repo.TasksRepo;
import com.example.elobit.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/tasks")
public class TasksController {
    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private TasksRepo tasksRepo;

    /**
     * @author Vladimir Krasnov
     * @param username имя пользователя из cookie
     * @param tasks поиск по полям year и month
     * @return List<Tasks> из всех задач пользователя по году и месяцу
     */
    @PostMapping("/show/{username}")
    private List<Tasks> showTasks(@PathVariable String username, @RequestBody Tasks tasks){
        Users user = usersRepo.findByUsername(username);
        String year = tasks.getYear();
        String month = tasks.getMonth();
        return user.getTasks().stream()
                .filter(o -> o.getYear().equals(year))
                .filter(o -> o.getMonth().equals(month))
                .collect(Collectors.toList());
    }

    /**
     * @author Vladimir Krasnov
     * @param username cookie username
     * @param task входные данные модели Tasks
     * @return status = success/denied
     * success - задача добавлена
     */
    @PostMapping("/add/{username}")
    private Status addTask(@PathVariable String username, @RequestBody Tasks task){
        Status status = new Status("success");
        try {
            task.setId(0);
            Users user = usersRepo.findByUsername(username);
            user.getTasks().add(task);
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
     * success - задача удалена
     */
    @PostMapping("/delete/{id}")
    private Status deleteTask(@PathVariable Integer id){
        Status status = new Status("success");
        try{
            tasksRepo.deleteById(id);
        }catch (Exception exception){
            status.setStatus("denied");
        }
        return status;
    }

    /**
     * @author Vladimir Krasnov
     * @param task все поля модели Tasks
     * @return status = success/denied
     * success - изменена
     * заметка изменяется по id на новую (рекоммендуемые изменяемые поля text и title)
     */
    @PostMapping("/change")
    private Status changeTask(@RequestBody Tasks task){
        Status status = new Status("success");
        try{
            tasksRepo.save(task);
        }catch (Exception exception){
            status.setStatus("denied");
        }
        return status;
    }

    /**
     * @author Vladimir Krasnov
     * @param tasks входные данные id, status
     * @return status = denied/success
     * denied - ошибка выполнения
     */
    @PostMapping("/setStatus")
    private Status setStatus(@RequestBody Tasks tasks){
        Status status = new Status("denied");
        try {
            Tasks task = tasksRepo.findTasksById(tasks.getId());
            task.setStatus(tasks.getStatus());
            tasksRepo.save(task);
        } catch (Exception e){
            status.setStatus("success");
        }
        return status;
    }

}
