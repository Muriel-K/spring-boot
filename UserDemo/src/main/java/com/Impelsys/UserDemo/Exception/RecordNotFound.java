package com.Impelsys.UserDemo.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RecordNotFound extends Exception {

    private static final long serialVersionUID = 1L;

    public RecordNotFound(String message) {
        super(message);
    }

    public RecordNotFound(String message, Throwable t) {
        super(message, t);
    }
}

