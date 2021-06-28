package ru.vtb.slepenkov.datamanager.exceptions;

public class NoSuchColumnException extends ApiException {
    private int code;

    public NoSuchColumnException(int code, String msg) {
        super(code, msg);
        this.code = code;
    }
}
