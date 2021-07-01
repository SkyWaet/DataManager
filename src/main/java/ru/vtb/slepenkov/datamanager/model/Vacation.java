package ru.vtb.slepenkov.datamanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ru.vtb.slepenkov.datamanager.model.base.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Table(name = "vacations")
@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Vacation extends BaseEntity {

    @Column(name = "date_from")
    private LocalDate from;

    @Column(name = "date_to")
    private LocalDate to;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
