package ru.vtb.slepenkov.datamanager.exceptions;

public class NullVacationsListException extends BaseException{

    public NullVacationsListException() {
        super(" Параметр \"vacations\" является обязательным и не может быть null.");
    }
}
