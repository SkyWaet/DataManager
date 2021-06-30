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
public class BaseDeleteNamedEntity extends BaseNamedEntity implements IDeleteEntity<Long> {

    @Column(name = "is_deleted")
    private boolean deleted;

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
