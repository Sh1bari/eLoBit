package com.example.elobit.repo;

import com.example.elobit.models.entity.Tasks;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TasksRepo extends CrudRepository<Tasks, Integer> {
    void deleteById(Integer id);

    Tasks findTasksById(Integer id);

    List<Tasks> findByTimeOfAlert(String timeOfAlert);

    boolean existsByTimeOfAlert(String timeOfAlert);
}
