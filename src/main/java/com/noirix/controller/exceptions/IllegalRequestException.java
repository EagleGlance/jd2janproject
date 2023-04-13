package com.noirix.controller.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.BindingResult;

@Setter
@Getter
public class IllegalRequestException extends RuntimeException {

    private BindingResult bindingResult;

    public IllegalRequestException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }

    public IllegalRequestException(String message, BindingResult bindingResult) {
        super(message);
        this.bindingResult = bindingResult;
    }

    public IllegalRequestException(String message, Throwable cause, BindingResult bindingResult) {
        super(message, cause);
        this.bindingResult = bindingResult;
    }

    public IllegalRequestException(Throwable cause, BindingResult bindingResult) {
        super(cause);
        this.bindingResult = bindingResult;
    }

    public IllegalRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, BindingResult bindingResult) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.bindingResult = bindingResult;
    }
}
