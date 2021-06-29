package ru.vtb.slepenkov.datamanager.model;

import java.io.Serializable;

public interface IEntity<T extends Serializable> {

    T getId();
}
