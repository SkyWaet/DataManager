package ru.vtb.slepenkov.datamanager.model.base;


import com.querydsl.core.annotations.QueryEntity;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@MappedSuperclass
@Data
@SuperBuilder
@QueryEntity
@NoArgsConstructor
public class BaseNamedEntity extends BaseEntity implements INamedEntity<Long> {

    @Column(name = "name")
    private String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
