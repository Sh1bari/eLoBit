package com.example.elobit.models.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class UserAll {
    @Id
    private String username;

    private String password;
}
