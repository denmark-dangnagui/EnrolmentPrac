package testtotest.testPrac.exception;

public class AlreadySubmitException extends RuntimeException {
    public AlreadySubmitException() {
    }

    public AlreadySubmitException(String message) {
        super(message);
    }
}
