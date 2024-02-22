package com.nayuki.handlers;

import com.nayuki.exceptions.ApplicationNotSetUpException;
import com.nayuki.components.UtilitiesComponent;
import com.nayuki.dto.ErrorDto;
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
 * @author Rudolf Barbu
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

    // 404 error
    @ExceptionHandler(value = Exception.class)
    public String notFoundExceptionHandler(Model model) throws IOException {
        model.addAttribute("theme", utilitiesComponent.getThemeName());

        return "error/404";
    }

    // 500 error
    @ExceptionHandler(value = IOException.class)
    public String internalServerErrorExceptionHandler(Model model) throws IOException {
        model.addAttribute("theme", utilitiesComponent.getThemeName());

        return "error/500";
    }
}