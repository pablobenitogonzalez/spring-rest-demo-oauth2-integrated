package org.demo.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@SuppressWarnings("unused")
@JsonPropertyOrder({ "timestamp", "status", "error", "exception", "message", "errors", "path", "type", "code" })
public class ErrorResource extends ResourceSupport {

    public Date timestamp;
    public Integer status;
    public String error;
    public String exception;
    public String message;
    @JsonInclude (NON_EMPTY)
    public List<Error> errors = new ArrayList<>();
    public String path;
    public String type;
    public String code;

    public ErrorResource(HttpStatus status, HttpServletRequest req, String code, Exception exception, String type) {
        this.timestamp = new Date();
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.exception = exception.getClass().getName();
        this.message = exception.getMessage();
        this.path = req.getRequestURI();
        this.type = type;
        this.code = code;
    }

    public ErrorResource(HttpStatus status, HttpServletRequest req, String code, Exception exception, String type, String message) {
        this.timestamp = new Date();
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.exception = exception.getClass().getName();
        this.message = message;
        this.path = req.getRequestURI();
        this.type = type;
        this.code = code;
    }

    public void addError(String field, Object rejected, String message) {
        Error error = new Error();
        error.field = field;
        error.rejected = rejected;
        error.message = message;
        errors.add(error);
    }

    public void addError(String message) {
        Error error = new Error();
        error.message = message;
        errors.add(error);
    }

    @JsonInclude (NON_EMPTY)
    static class Error {
        public String field;
        public Object rejected;
        public String message;
    }
}
