package org.javaboy.idempontent.exception;

/**
 * 自定义异常
 *
 * @author szh
 */
public class IdempotentException extends Exception {

    public IdempotentException(String message) {
        super(message);
    }
}
