package testtotest.testPrac.exception;

public class BeforeSubmitException extends RuntimeException {
    public BeforeSubmitException() {
    }

    public BeforeSubmitException(String message) {
        super(message);
    }
}
