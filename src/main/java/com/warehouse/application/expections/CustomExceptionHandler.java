package com.warehouse.application.expections;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> productNotFoundException(Exception ex, WebRequest request) {
        CustomErrorResponse cer = new CustomErrorResponse();
        cer.setTimestamp(LocalDateTime.now());
        cer.setError(ex.getMessage());
        cer.setStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(cer, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotInStockException.class)
    public ResponseEntity<CustomErrorResponse> productNotInStockException(Exception ex, WebRequest request) {
        CustomErrorResponse cer = new CustomErrorResponse();
        cer.setTimestamp(LocalDateTime.now());
        cer.setError(ex.getMessage());
        cer.setStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(cer, HttpStatus.NOT_FOUND);
    }

    /**
     * Custom response message in of failures
     */

    public static class CustomErrorResponse {
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
        private LocalDateTime timestamp;
        private int status;
        private String error;

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }
    }
}
