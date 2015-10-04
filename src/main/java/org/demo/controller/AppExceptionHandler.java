package org.demo.controller;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.demo.exception.ResourceNotFoundException;
import org.demo.service.MessageService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
@SuppressWarnings("unused")
public class AppExceptionHandler {

    @Autowired
    private MessageService messageService;

    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    @ResponseStatus (value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResource generalExceptionHandler(HttpServletRequest req, Exception e) {
        return new ErrorResource(HttpStatus.BAD_REQUEST, req, "10000", e,
                messageService.getMessage("type.internal.error.server"));
    }

    @ExceptionHandler (HttpMessageNotReadableException.class)
    @ResponseStatus (value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResource handleHttpMessageNotReadableException(HttpServletRequest req, HttpMessageNotReadableException e) {
        return new ErrorResource(HttpStatus.BAD_REQUEST, req, "10001", e,
                messageService.getMessage("type.parse.json"));
    }

    @ExceptionHandler (ResourceNotFoundException.class)
    @ResponseStatus (value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResource handleResourceNotFoundException(HttpServletRequest req, ResourceNotFoundException e) {
        return new ErrorResource(HttpStatus.NOT_FOUND, req, "10002", e,
                messageService.getMessage("type.resource.not.found"));
    }

    @ExceptionHandler (DuplicateKeyException.class)
    @ResponseStatus (value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResource handleDuplicateKeyException(HttpServletRequest req, DuplicateKeyException e) {
        return new ErrorResource(HttpStatus.BAD_REQUEST, req, "10003", e,
                messageService.getMessage("type.resource.duplicate.key"));
    }

    @ExceptionHandler (ConstraintViolationException.class)
    @ResponseStatus (value = HttpStatus.NOT_ACCEPTABLE)
    @ResponseBody
    public ErrorResource handleConstraintViolationException(HttpServletRequest req, ConstraintViolationException e) {
        ErrorResource errorResource = new ErrorResource(HttpStatus.NOT_ACCEPTABLE, req, "10004", e,
                messageService.getMessage("type.resource.constraint.violation"),
                messageService.getMessage("message.resource.constraint.violation",
                                                     new Object[]{e.getConstraintViolations().size()}));
        for(ConstraintViolation<?> constraint : e.getConstraintViolations()) {
            errorResource.addError(constraint.getPropertyPath().toString(),
                               constraint.getInvalidValue(),
                               constraint.getMessage());
        }
        return errorResource;
    }

    @ExceptionHandler (IllegalArgumentException.class)
    @ResponseStatus (value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResource handleIllegalArgumentException(HttpServletRequest req, IllegalArgumentException e) {
        return new ErrorResource(HttpStatus.BAD_REQUEST, req, "10005", e,
                messageService.getMessage("type.illegal.argument"));
    }

    @ExceptionHandler (DataIntegrityViolationException.class)
     @ResponseStatus (value = HttpStatus.BAD_REQUEST)
     @ResponseBody
     public ErrorResource handleDataIntegrityViolationException(HttpServletRequest req, DataIntegrityViolationException e) {
        return new ErrorResource(HttpStatus.BAD_REQUEST, req, "10006", e,
                messageService.getMessage("type.resource.data.integrity.violation"));
    }

    @ExceptionHandler (HttpRequestMethodNotSupportedException.class)
    @ResponseStatus (value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResource handleHttpRequestMethodNotSupportedException(HttpServletRequest req, HttpRequestMethodNotSupportedException e) {
        return new ErrorResource(HttpStatus.BAD_REQUEST, req, "10007", e,
                messageService.getMessage("type.method.not.supported"));
    }

    @ExceptionHandler (TypeMismatchException.class)
    @ResponseStatus (value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResource handleTypeMismatchException(HttpServletRequest req, TypeMismatchException e) {
        return new ErrorResource(HttpStatus.BAD_REQUEST, req, "10008", e,
                messageService.getMessage("type.bad.path.parameter"));
    }
}
