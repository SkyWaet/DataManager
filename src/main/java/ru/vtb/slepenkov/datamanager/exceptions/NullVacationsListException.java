package ru.vtb.slepenkov.datamanager.exceptions;

public class NullVacationsListException extends BaseException{

    public NullVacationsListException() {
        super("Expected \"vacations\" to be not null");
    }
}
