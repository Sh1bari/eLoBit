package com.example.elobit.repo;

import com.example.elobit.models.entity.UserAll;
import org.springframework.data.repository.CrudRepository;

public interface UserAllRepo extends CrudRepository<UserAll, String> {
    UserAll findByUsername(String username);
    boolean existsByUsername(String username);
}
