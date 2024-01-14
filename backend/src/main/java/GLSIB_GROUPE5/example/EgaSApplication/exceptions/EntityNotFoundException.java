package GLSIB_GROUPE5.example.EgaSApplication.exceptions;

import lombok.Getter;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}

