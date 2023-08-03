package net.sitir.message.component.exception;

import lombok.Getter;

@Getter
public class APIException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String message;

    public APIException(String message) {
        this.message = message;
    }

    public APIException() {

    }

}