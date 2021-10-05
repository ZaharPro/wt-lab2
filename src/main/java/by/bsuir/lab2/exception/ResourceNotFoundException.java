package by.bsuir.lab2.exception;

public class ResourceNotFoundException extends BaseRuntimeException {
    private static String buildMsg(Object id) {
        return "Resource with id: " + id + " not found";
    }

    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(Object id) {
        super(buildMsg(id));
    }

    public ResourceNotFoundException(Object id, Throwable cause) {
        super(buildMsg(id), cause);
    }

    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }
}
