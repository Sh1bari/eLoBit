package com.example.elobit.repo;

import com.example.elobit.models.entity.Tasks;
import org.springframework.data.repository.CrudRepository;

public interface TasksRepo extends CrudRepository<Tasks, Integer> {
    void deleteById(Integer id);
}
