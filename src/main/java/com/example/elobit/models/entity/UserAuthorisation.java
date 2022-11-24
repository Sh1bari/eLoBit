package com.example.elobit.models.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class UserAuthorisation {
    @Id
    private String username;

    private String password;
}
