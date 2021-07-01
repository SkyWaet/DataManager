package ru.vtb.slepenkov.datamanager.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import ru.vtb.slepenkov.datamanager.model.base.BaseDeleteNamedEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Table(name = "users")
@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseDeleteNamedEntity {
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "surname")
    private String surname;
    @Column(name = "patronymic")
    private String patronymic;
    @Column(name = "email")
    private String email;
    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy(value = "id")
    private List<Vacation> vacationsList;

}
