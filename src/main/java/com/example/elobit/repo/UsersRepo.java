package com.example.elobit.repo;

import com.example.elobit.models.entity.Notices;
import com.example.elobit.models.entity.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsersRepo extends CrudRepository<Users, String> {
    Users findByUsername(String username);

    boolean existsByMail(String mail);
    boolean existsByUsername(String username);

    Users findByPassword(String key);

    Users findByMail(String mail);
}
