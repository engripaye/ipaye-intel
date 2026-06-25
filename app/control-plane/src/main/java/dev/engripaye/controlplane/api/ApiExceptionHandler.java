package dev.engripaye.controlplane.api;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    ProblemDetail status(ResponseStatusException exception, HttpServletRequest request){
        var problem = ProblemDetail.forStatusAndDetail(exception.getStatusCode(), exception.getReason());
        problem.setInstance(URI.create(request.getRequestURI()));
        return problem;

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ProblemDetail validation(MethodArgumentNotValidException exception){
    var problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Request validation failed");
    problem.setProperty("errors", exception.getBindingResult().getFieldErrors().stream()
            .map(error -> error.getField() + ": " + error.getDefaultMessage()).toList());
    return problem;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    ProblemDetail conflict(){
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, "Resource already exists or violates a constraint");
    }


    @ExceptionHandler(EmptyResultDataAccessException.class)
    ProblemDetail notFound(){
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, "Resource was not found in this organization");
    }
}

