package com.example.elobit.models.entity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Vladimir Krasnov
 * БД для за задач, зависимость от Users
 */
@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * формат: (0 <= число <= 31)
     */
    private String day;

    /**
     * формат: (Ноябрь, Декабрь...)
     */
    private String month;

    /**
     * формат: (2022, 2021...)
     */
    private String year;

    /**
     * формат: время(17:05, 19:12...)
     */
    private String time;

    private String title;

    private String text;

    /**
     * формат (done/in progress)
     */
    private String status;

    /**
     * формат: (true/false)
     */
    private String importance;

    /**
     * кол-во минут(15, 20...)
     */
    private String alert;
}
