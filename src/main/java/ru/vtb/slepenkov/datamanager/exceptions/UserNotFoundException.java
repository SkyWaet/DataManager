package ru.vtb.slepenkov.datamanager.exceptions;

public class UserNotFoundException extends BaseException{

    public UserNotFoundException(Long id) {
        super("Пользователь с id "+id+" не найден.");
    }
}
