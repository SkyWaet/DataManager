package ru.vtb.slepenkov.datamanager.model;


import lombok.Data;
import lombok.ToString;
import ru.vtb.slepenkov.datamanager.model.base.BaseDeleteNamedEntity;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@ToString
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
