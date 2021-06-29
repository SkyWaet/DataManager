package ru.vtb.slepenkov.datamanager.model.base;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.vtb.slepenkov.datamanager.model.IEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@ToString
public abstract class BaseEntity implements IEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_created")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "date_updated")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
