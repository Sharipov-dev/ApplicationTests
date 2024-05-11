package proselyte.com.testingGradle.exception;

public class DeveloperWithDuplicateEmailException extends RuntimeException{

    public DeveloperWithDuplicateEmailException(String message){
        super(message);
    }
}
