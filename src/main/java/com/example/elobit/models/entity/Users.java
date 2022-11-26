package com.example.elobit.models.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vladimir Krasnov
 * БД пользователей
 */

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Users {
    @Id
    private String username;

    private String password;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "username")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Notices> notices = new ArrayList<>(); // таблица для заметок

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "username")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Tasks> tasks = new ArrayList<>(); //таблица для задач
}
