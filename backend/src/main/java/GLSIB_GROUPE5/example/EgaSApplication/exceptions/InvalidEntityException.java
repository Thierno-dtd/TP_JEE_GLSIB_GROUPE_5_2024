package GLSIB_GROUPE5.example.EgaSApplication.exceptions;

import lombok.Getter;

import java.util.List;

public class InvalidEntityException extends RuntimeException{
    public InvalidEntityException(String message) {
        super(message);
    }

    public InvalidEntityException(String message, Throwable cause){
        super(message,cause);
    }

}
