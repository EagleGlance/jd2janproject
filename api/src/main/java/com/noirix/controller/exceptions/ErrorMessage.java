package com.noirix.controller.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage {

    private String errorId;

    private Integer errorCode;

    private String errorMessage;
}
