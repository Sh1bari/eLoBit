package com.example.elobit.repo;

import com.example.elobit.models.entity.Users;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepo extends CrudRepository<Users, String> {
    Users findByUsername(String username);
    boolean existsByUsername(String username);
}
