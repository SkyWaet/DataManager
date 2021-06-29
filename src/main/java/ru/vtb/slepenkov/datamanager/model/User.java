package ru.vtb.slepenkov.datamanager.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.vtb.slepenkov.datamanager.model.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@Data
@ToString
public class User extends BaseEntity {
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name="name")
    private String name;
    @Column(name="surname")
    private String surname;
    @Column(name="patronymic")
    private String patronymic;
    @Column(name = "email")
    private String email;
    @Column(name = "description")
    private String description;

}
