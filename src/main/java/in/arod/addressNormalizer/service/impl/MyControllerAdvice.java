package in.arod.addressNormalizer.service.impl;

import in.arod.addressNormalizer.controller.dto.ApiBaseErrorDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class MyControllerAdvice {
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiBaseErrorDto handleNoContentEntity(RuntimeException ex, HttpServletRequest request) {
        return ApiBaseErrorDto.builder()
                .patch(request.getRequestURI())
                .code(HttpStatus.BAD_REQUEST.value())
                .type(ex.getClass().getName())
                .detail(ex.getMessage())
                .build();
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiBaseErrorDto handleNoContentEntity(DataIntegrityViolationException ex, HttpServletRequest request) {
        return ApiBaseErrorDto.builder()
                .patch(request.getRequestURI())
                .code(HttpStatus.BAD_REQUEST.value())
                .type(ex.getClass().getName())
                .detail(ex.getCause().getCause().getMessage())
                .build();
    }
}
