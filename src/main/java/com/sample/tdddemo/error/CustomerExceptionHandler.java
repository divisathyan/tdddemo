package com.sample.tdddemo.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;

@ControllerAdvice
public class CustomerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Object> handleCustomerNotFoundError(CustomerNotFoundException ex, WebRequest request) throws IOException {
        CustomerErrorResponse errorResponse = new CustomerErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getLocalizedMessage());
        return  new ResponseEntity(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralError(Exception ex, WebRequest request) throws IOException {
        CustomerErrorResponse errorResponse = new CustomerErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),ex.getLocalizedMessage());
        return  new ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) throws IOException {
        CustomerErrorResponse errorResponse = new CustomerErrorResponse(HttpStatus.BAD_REQUEST.value(),"Validation errors: " + ex.getLocalizedMessage());
        return  new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
