package testtotest.testPrac.exception;

public class OverDateException extends RuntimeException{
    public OverDateException() {
    }

    public OverDateException(String message){
        super(message);
    }
}
