package org.skypro.employee.records.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidLineException extends RuntimeException {

    public InvalidLineException() {
    }
}