package com.example.elobit.repo;

import com.example.elobit.models.entity.UserAuthorisation;
import org.springframework.data.repository.CrudRepository;

public interface UserAuthorisationRepo extends CrudRepository<UserAuthorisation, String> {
    UserAuthorisation findByUsername(String username);
    boolean existsByUsername(String username);
}
