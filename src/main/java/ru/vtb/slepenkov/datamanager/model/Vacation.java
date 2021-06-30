package ru.vtb.slepenkov.datamanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ru.vtb.slepenkov.datamanager.model.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Table(name = "vacations")
@javax.persistence.Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Vacation extends BaseEntity {

    @Column(name = "from")
    private LocalDate from;

    @Column(name = "to")
    private LocalDate to;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
