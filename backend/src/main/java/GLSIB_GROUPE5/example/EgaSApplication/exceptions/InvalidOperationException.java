package GLSIB_GROUPE5.example.EgaSApplication.exceptions;

import lombok.Getter;

import java.util.List;

public class InvalidOperationException extends RuntimeException{
    public InvalidOperationException(String message){
        super(message);
    }

    public InvalidOperationException(Throwable cause) {
        super(cause);
    }

}
