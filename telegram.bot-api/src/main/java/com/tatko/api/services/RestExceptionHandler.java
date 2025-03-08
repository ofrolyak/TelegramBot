package com.tatko.api.services;

import com.tatko.api.apis.models.GeneralErrorResultApiResponse;
import com.tatko.api.apis.models.MsgApiObject;
import com.tatko.api.apis.models.MsgsApiObject;
import com.tatko.api.exceptions.TelegramBotApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Centralized exception handler for REST API that extends
 * {@code ResponseEntityExceptionHandler}.
 * This class is annotated with {@code @ControllerAdvice}
 * to allow global handling of exceptions
 * across all REST endpoints. It prioritizes handling of exceptions based
 * on its {@code @Order} annotation.
 *
 * Custom handling logic is implemented to construct and return detailed
 * error responses
 * in a consistent structure using {@code GeneralErrorResultApiResponse}.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Service
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles exceptions of type {@code TelegramBotApiException} and constructs
     * a standardized error response.
     *
     * @param ex the {@code TelegramBotApiException} that was thrown
     * @param request the web request during which the exception occurred
     * @return a {@code ResponseEntity} containing
     * a {@code GeneralErrorResultApiResponse}
     *         with error details and a status of {@code HttpStatus.BAD_REQUEST}
     */
    @ExceptionHandler({TelegramBotApiException.class})
    public ResponseEntity<GeneralErrorResultApiResponse> handleAll(
            final TelegramBotApiException ex, final WebRequest request) {
        log.debug("TelegramBotApiException exception", ex);
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        GeneralErrorResultApiResponse generalErrorResultApiResponse
                = new GeneralErrorResultApiResponse();
        MsgsApiObject msgsApiObject = new MsgsApiObject();
        MsgApiObject msgApiObject = new MsgApiObject();
        msgApiObject.setType("ERROR");
        msgApiObject.setCode("-1");
        msgApiObject.setDest(null);
        msgApiObject.setText(ex.getMessage());
        msgsApiObject.addMsgItem(msgApiObject);
        generalErrorResultApiResponse.setMsgs(msgsApiObject);
        return new ResponseEntity<>(generalErrorResultApiResponse,
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles all generic exceptions that are not explicitly handled
     * by other specific exception handlers.
     * Constructs a standardized error response based on the provided exception.
     *
     * @param ex the {@code Exception} that was thrown
     * @param request the web request during which the exception occurred
     * @return a {@code ResponseEntity} containing
     * a {@code GeneralErrorResultApiResponse}
     *         with error details and a status
     *         of {@code HttpStatus.INTERNAL_SERVER_ERROR}
     */
    @ExceptionHandler({Exception.class})
    public ResponseEntity<GeneralErrorResultApiResponse> handleAll(
            final Exception ex, final WebRequest request) {
        log.debug("Exception exception", ex);
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        GeneralErrorResultApiResponse generalErrorResultApiResponse
                = new GeneralErrorResultApiResponse();
        MsgsApiObject msgsApiObject = new MsgsApiObject();
        MsgApiObject msgApiObject = new MsgApiObject();
        msgApiObject.setType("ERROR");
        msgApiObject.setCode("-1");
        msgApiObject.setDest(null);
        msgApiObject.setText(ex.getMessage());
        msgsApiObject.addMsgItem(msgApiObject);
        generalErrorResultApiResponse.setMsgs(msgsApiObject);
        return new ResponseEntity<>(generalErrorResultApiResponse,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }


}


