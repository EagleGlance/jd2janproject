package com.noirix.exception;

public class EntityNotFoundException extends RuntimeException {

    private String customMessage;

    public EntityNotFoundException() {
    }

    public EntityNotFoundException(String customMessage) {
        this.customMessage = customMessage;
    }

    public EntityNotFoundException(String message, String customMessage) {
        super(message);
        this.customMessage = customMessage;
    }

    public EntityNotFoundException(String message, Throwable cause, String customMessage) {
        super(message, cause);
        this.customMessage = customMessage;
    }

    public EntityNotFoundException(Throwable cause, String customMessage) {
        super(cause);
        this.customMessage = customMessage;
    }

    public EntityNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String customMessage) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.customMessage = customMessage;
    }

    public String getCustomMessage() {
        return customMessage;
    }

    public void setCustomMessage(String customMessage) {
        this.customMessage = customMessage;
    }

    @Override
    public String toString() {
        return "EntityNotFoundException{" +
                "customMessage='" + customMessage + '\'' +
                "} " + super.toString();
    }
}
