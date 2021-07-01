package ru.vtb.slepenkov.datamanager.exceptions;

import lombok.Data;

@Data
public abstract class BaseException extends RuntimeException {
    private String message;

    public BaseException(String message) {
        this.message = message;
    }
}
