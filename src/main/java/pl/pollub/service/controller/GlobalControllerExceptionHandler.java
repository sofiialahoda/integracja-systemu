package pl.pollub.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.pollub.service.audit.RequestAuditService;
import pl.pollub.service.config.DDosConfig;
import pl.pollub.service.ex.AllowedRequestsExceeded;
import pl.pollub.service.ex.MovieNotFoundException;
import pl.pollub.service.model.Error;
import pl.pollub.service.model.ErrorType;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalControllerExceptionHandler {

    @Autowired
    private RequestAuditService service;

    @ExceptionHandler(MovieNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Error handleMovieNotFoundException(HttpServletRequest request, MovieNotFoundException ex) {
        service.insert(request, HttpStatus.BAD_REQUEST.value());
        return new Error(ErrorType.MOVIE_NOT_FOUND,
                String.format("Movie not exists with provided id: %s.", ex.getId()));
    }

    @ExceptionHandler(AllowedRequestsExceeded.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED)
    Error handleAvailableRequestsExceeded(HttpServletRequest request, AllowedRequestsExceeded ex) {
        service.insert(request, HttpStatus.BAD_REQUEST.value());
        return new Error(ErrorType.REQUEST_PER_SECOND_EXCEEDED, String.format("Your IP: %s has exceed allowed requests number per second. You have been added into black list for %s %s.",
                ex.getHost(), DDosConfig.BLACK_LIST_TIMEOUT, DDosConfig.BLACK_LIST_TIME_UNIT));
    }

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Error handleThrowable(HttpServletRequest request, Throwable ex) {
        service.insert(request, HttpStatus.BAD_REQUEST.value());
        return new Error(ErrorType.GENERAL, ex.getMessage());
    }
}
