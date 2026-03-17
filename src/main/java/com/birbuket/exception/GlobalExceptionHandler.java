package com.birbuket.exception;


import com.birbuket.dto.ApiResponse;
import com.birbuket.enums.ErrorCode;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<@NonNull ApiResponse<Void>> handlerUserAlreadyExists (UserAlreadyExistsException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ApiResponse.error(ErrorCode.USER_ALREADY_EXISTS, ex.getMessage()));
    }


    @ExceptionHandler(UserIsNotActive.class)
    public ResponseEntity<@NonNull ApiResponse<Void>> handlerUserIsNotActive (UserIsNotActive ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(ErrorCode.USER_IS_NOT_ACTIVE, ex.getMessage()));
    }


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<@NonNull ApiResponse<Void>> handlerUserNotFoundException (UserNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(ErrorCode.USER_NOT_FOUND, ex.getMessage()));
    }


    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<@NonNull ApiResponse<Void>> handlePasswordMismatchException(PasswordMismatchException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(ErrorCode.PASSWORD_MISMATCH, ex.getMessage()));
    }
}
