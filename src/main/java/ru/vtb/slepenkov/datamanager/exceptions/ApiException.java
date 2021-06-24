package ru.vtb.slepenkov.datamanager.exceptions;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-22T11:03:20.924Z[GMT]")
public class ApiException extends RuntimeException {
    private int code;
    public ApiException (int code, String msg) {
        super(msg);
        this.code = code;
    }
}
