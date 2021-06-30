package ru.vtb.slepenkov.datamanager.model.base;

import java.io.Serializable;

public interface INamedEntity<T extends Serializable> extends IEntity<T> {

    String getName();

    void setName(String name);
}
