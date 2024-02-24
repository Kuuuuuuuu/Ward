package com.nayuki.handlers;

import com.nayuki.components.UtilitiesComponent;
import com.nayuki.dto.ErrorDto;
import com.nayuki.exceptions.ApplicationNotSetUpException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * ControllerExceptionHandler is a standard exception handler for rest api, and white labels
 *
 * @author Rudolf Barbu, Nayuki
 * @version 1.0.0
 */
@ControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class ControllerExceptionHandler {
    /**
     * Autowired UtilitiesComponent object
     * Used for various utility functions
     */
    private final UtilitiesComponent utilitiesComponent;

    public ControllerExceptionHandler(UtilitiesComponent utilitiesComponent) {
        this.utilitiesComponent = utilitiesComponent;
    }

    /**
     * Handles ApplicationNotSetUpException, then it threw
     */
    @ResponseBody
    @ExceptionHandler(value = ApplicationNotSetUpException.class)
    public ResponseEntity<?> applicationNotSetUpExceptionHandler(ApplicationNotSetUpException applicationNotSetUpException) {
        return new ResponseEntity<>(new ErrorDto(applicationNotSetUpException), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles MethodArgumentNotValidException, then it threw
     */
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException methodArgumentNotValidException) {
        return new ResponseEntity<>(new ErrorDto(methodArgumentNotValidException), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // IDK how this works, but it does lol
    @ExceptionHandler(value = {Exception.class, IOException.class})
    public String handleException(Model model, Exception exception) throws IOException {
        model.addAttribute("theme", utilitiesComponent.getThemeName());

        if (exception instanceof IOException) {
            return "error/500";
        } else {
            return "error/404";
        }
    }
}