package ru.vtb.slepenkov.datamanager.model.base;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Data
@SuperBuilder
@NoArgsConstructor
public class BaseDeleteEntity extends BaseEntity implements IDeleteEntity<Long> {

    @Column(name = "is_deleted")
    private boolean isDeleted=false;
}

