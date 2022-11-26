package com.example.elobit.repo;

import com.example.elobit.models.entity.Notices;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NoticesRepo extends CrudRepository<Notices, Integer> {
    void deleteById(Integer id);
}
