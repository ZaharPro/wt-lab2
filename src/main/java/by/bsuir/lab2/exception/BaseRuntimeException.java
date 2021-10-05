package by.bsuir.lab2.exception;

public class BaseRuntimeException extends RuntimeException {
    public BaseRuntimeException() {
    }

    public BaseRuntimeException(String message) {
        super(message);
    }

    public BaseRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseRuntimeException(Throwable cause) {
        super(cause.getMessage(), cause);
    }
}
