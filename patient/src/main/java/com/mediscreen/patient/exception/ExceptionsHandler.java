package com.mediscreen.patient.exception;

import com.mediscreen.patient.exception.customexceptions.MissingIdException;
import com.mediscreen.patient.exception.customexceptions.PatientAlreadyExistException;
import com.mediscreen.patient.exception.customexceptions.PatientNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<Object>handePatientNotFoundException(PatientNotFoundException e){
        CustomErrorResponse errorResponse = new CustomErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND, ZonedDateTime.now());
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PatientAlreadyExistException.class)
    public ResponseEntity<Object>handePatientAlreadyExistException(PatientAlreadyExistException e){
        CustomErrorResponse errorResponse = new CustomErrorResponse(e.getMessage(), HttpStatus.CONFLICT, ZonedDateTime.now());
        return new ResponseEntity<>(errorResponse,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MissingIdException.class)
    public ResponseEntity<Object>handleMissingIdExceptionExistException(MissingIdException e){
        CustomErrorResponse errorResponse = new CustomErrorResponse(e.getMessage(), HttpStatus.NO_CONTENT, ZonedDateTime.now());
        return new ResponseEntity<>(errorResponse,HttpStatus.NO_CONTENT);
    }
}
