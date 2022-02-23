package com.mediscreen.notes.exception;

import com.mediscreen.notes.exception.customexceptions.MissingIdException;
import com.mediscreen.notes.exception.customexceptions.NoteNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(NoteNotFoundException.class)
    public ResponseEntity<Object>handePatientNotFoundException(NoteNotFoundException e){
        CustomErrorResponse errorResponse = new CustomErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND, ZonedDateTime.now());
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingIdException.class)
    public ResponseEntity<Object>handleMissingIdExceptionExistException(MissingIdException e){
        CustomErrorResponse errorResponse = new CustomErrorResponse(e.getMessage(), HttpStatus.NO_CONTENT, ZonedDateTime.now());
        return new ResponseEntity<>(errorResponse,HttpStatus.NO_CONTENT);
    }
}
