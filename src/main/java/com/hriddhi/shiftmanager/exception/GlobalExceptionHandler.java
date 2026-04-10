package com.hriddhi.shiftmanager.exception;

import com.hriddhi.shiftmanager.dto.ApiResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse handleValidation(MethodArgumentNotValidException ex) {
        String error = ex.getBindingResult()
                .getFieldErrors()
                .get(0)
                .getDefaultMessage();

        return new ApiResponse("ERROR", error, null);
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse handleGeneric(Exception ex) {
        return new ApiResponse("ERROR", "Something went wrong", null);
    }
    @ExceptionHandler(BadRequestException.class)
    public ApiResponse handleBadRequest(BadRequestException ex) {
        return new ApiResponse("ERROR", ex.getMessage(), null);
    }
}
