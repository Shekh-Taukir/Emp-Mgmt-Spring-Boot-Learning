package com.learnSpringBootCode.Hospital_Mgmt_SB.advices;

import com.learnSpringBootCode.Hospital_Mgmt_SB.exceptions.ResourceNotFoundException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.List;

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
        /*ApiError apiError = ApiError.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(exception.getMessage())
                .build();

        return buidErrorResponseEntity(apiError);
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
         */
        return buidErrorResponseEntity(exception.getMessage(),HttpStatus.NOT_FOUND);
    }

    //example for usage of sub errors
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
        /*
        ApiError apiError = ApiError.builder()
                .message(exception.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();

        return buidErrorResponseEntity(apiError);
        */
        return buidErrorResponseEntity(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Authentication exceptions
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleUsernameNotFoundException(Exception exception){
        /*
            ApiError apiError = ApiError.builder()
                .message("UserName not found as per credentials | "+exception.getMessage())
                .status(HttpStatus.FORBIDDEN)
                .build();
        return buidErrorResponseEntity(apiError);
        */
        return buidErrorResponseEntity("UserName not found as per credentials | "+exception.getMessage(),HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(AuthenticationException.class)
        public ResponseEntity<ApiResponse<?>> handleAuthenticationException(Exception exception){
            /*
            ApiError apiError = ApiError.builder()
                    .message("Authentication failed | msg : "+exception.getMessage())
                    .status(HttpStatus.UNAUTHORIZED)
                    .build();
            return buidErrorResponseEntity(apiError);
            */
        return buidErrorResponseEntity("Authentication failed | msg : "+exception.getMessage(),HttpStatus.UNAUTHORIZED);
        }
    @ExceptionHandler(JwtException.class)
        public ResponseEntity<ApiResponse<?>> handleJwtException(Exception exception){
            /*
            ApiError apiError = ApiError.builder()
                    .message("Error occurs while processing JWT token | "+exception.getMessage())
                    .status(HttpStatus.UNAUTHORIZED)
                    .build();
            return buidErrorResponseEntity(apiError);
             */
        return buidErrorResponseEntity("Error occurs while processing JWT token | "+exception.getMessage(),HttpStatus.UNAUTHORIZED);
        }

    @ExceptionHandler(AccessDeniedException.class)
        public ResponseEntity<ApiResponse<?>> handleAccessDeniedException(Exception exception){
        /*
            ApiError apiError = ApiError.builder()
                    .message("Access Denied: Insufficient persmission | "+exception.getMessage())
                    .status(HttpStatus.FORBIDDEN)
                    .build();
            return buidErrorResponseEntity(apiError);
            */
        return buidErrorResponseEntity("Access Denied: Insufficient permission | "+exception.getMessage(),HttpStatus.FORBIDDEN);
        }


    //------------------------Internal Methods

    private ResponseEntity<ApiResponse<?>> buidErrorResponseEntity(ApiError apiError) {
        return ResponseEntity.status(apiError.getStatus()).body(new ApiResponse<>(apiError));
    }

    private ResponseEntity<ApiResponse<?>> buidErrorResponseEntity(String errorMessage, HttpStatus httpStatusCode) {

        return ResponseEntity
                .status(httpStatusCode)
                .body(new ApiResponse<>(
                        ApiError.builder()
                                .message(errorMessage)
                                .status(httpStatusCode)
                                .build()
                ));
    }
}
