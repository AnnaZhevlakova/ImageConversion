package com.example.ImageConversion.exceptionHandler;

import com.example.ImageConversion.StackTraceUtil;
import com.example.ImageConversion.controllers.ImageConversionController;
import com.example.ImageConversion.dto.ErrorDto;
import com.example.ImageConversion.exceptions.UserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tools.jackson.databind.ObjectMapper;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LogManager.getLogger(ImageConversionController.class);

    @ExceptionHandler(UserException.class)
    public ResponseEntity<String> handlerAppException(UserException ex) throws Exception {
        logger.error(String.format("message: %s | stackTrace: %s", ex.getMessage(), StackTraceUtil.getStackTraceAsString(ex)));

        ObjectMapper mapper = new ObjectMapper();

        String responseJson = mapper.writeValueAsString(new ErrorDto(ex.getMessage(), 400));
        return ResponseEntity.badRequest().body(responseJson);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handlerGlobalException(Exception ex) throws Exception {
        logger.error(String.format("message: %s | stackTrace: %s", ex.getMessage(), StackTraceUtil.getStackTraceAsString(ex)));

        ObjectMapper mapper = new ObjectMapper();

        String responseJson = mapper.writeValueAsString(new ErrorDto("Что то пошло не так.", 500));
        return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(responseJson);

    }

}
