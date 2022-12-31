package testtotest.testPrac.exception;

public class OverApplyException extends RuntimeException{

    public OverApplyException() {
    }

    public OverApplyException(String message){
        super(message);
    }
}
