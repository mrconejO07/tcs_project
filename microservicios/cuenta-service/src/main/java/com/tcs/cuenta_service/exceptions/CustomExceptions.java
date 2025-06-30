package com.tcs.cuenta_service.exceptions;

public class CustomExceptions {
    public static class NotFoundException extends RuntimeException {
        public NotFoundException(String message) {
            super(message);
        }
    }

    public static class BadRequestException extends RuntimeException {
        public BadRequestException(String message) {
            super(message);
        }
    }

    public static class InvalidFieldException extends RuntimeException {
        public InvalidFieldException(String message) {
            super(message);
        }
    }

    public static class ValorYaAsignadoException extends RuntimeException {
        public ValorYaAsignadoException(String message) {
            super(message);
        }
    }
}