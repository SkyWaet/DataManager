package ru.vtb.slepenkov.datamanager.exceptions;

public class UserNotFoundException extends BaseException{

    public UserNotFoundException(Long id) {
        super("User with id "+id+" not found");
    }
}
