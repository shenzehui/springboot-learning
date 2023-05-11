package org.javaboy.idempontent.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 *
 * @author szh
 */
@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(IdempotentException.class)
    public String idempotentException(IdempotentException e) {
        return e.getMessage();
    }

}
