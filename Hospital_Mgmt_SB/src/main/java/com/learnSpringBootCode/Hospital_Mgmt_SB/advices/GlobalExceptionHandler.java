package com.learnSpringBootCode.Hospital_Mgmt_SB.advices;

import com.learnSpringBootCode.Hospital_Mgmt_SB.exceptions.ResourceNotFoundException;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /*
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiError> handleResourceNotFound(NoSuchElementException exception){
        ApiError apiError = ApiError.builder().status(HttpStatus.NOT_FOUND).message("Resource Not Found on requested Id").build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleResourceNotFound(ResourceNotFoundException exception){
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(exception.getMessage())
                .build();

        return buidErrorResponseEntity(apiError);
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        List<String> errors =  exception.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error-> error.getDefaultMessage())
                .toList();

        ApiError apiError = ApiError.builder()
                .message("Input validation fails due to wrong user input data")
                .subErrors(errors)
                .status(HttpStatus.BAD_REQUEST)
                .build();

        return buidErrorResponseEntity(apiError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleInternalException(Exception exception){
        ApiError apiError = ApiError.builder()
                .message(exception.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();

        return buidErrorResponseEntity(apiError);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleUsernameNotFoundException(Exception exception){
        ApiError apiError = ApiError.builder()
                .message(exception.getMessage())
                .status(HttpStatus.FORBIDDEN)
                .build();
        return buidErrorResponseEntity(apiError);
    }


    //------------------------Internal Methods

    private ResponseEntity<ApiResponse<?>> buidErrorResponseEntity(ApiError apiError) {
        return ResponseEntity.status(apiError.getStatus()).body(new ApiResponse<>(apiError));
    }
}
