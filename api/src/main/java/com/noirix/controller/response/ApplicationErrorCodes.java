package com.noirix.controller.response;

public enum ApplicationErrorCodes {
    SQL_ERROR(10),
    BAD_REQUEST_USER_CREATE(66),
    USER_NOT_FOUND(40),
    FATAL_ERROR(1);

    public int getCodeId() {
        return codeId;
    }

    private int codeId;

    ApplicationErrorCodes(int codeId) {
        this.codeId = codeId;
    }
}
