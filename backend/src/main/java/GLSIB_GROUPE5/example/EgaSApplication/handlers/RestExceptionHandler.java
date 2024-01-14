package GLSIB_GROUPE5.example.EgaSApplication.handlers;

import GLSIB_GROUPE5.example.EgaSApplication.dto.ErrorDto;
import GLSIB_GROUPE5.example.EgaSApplication.exceptions.EntityNotFoundException;
import GLSIB_GROUPE5.example.EgaSApplication.exceptions.InvalidEntityException;
import GLSIB_GROUPE5.example.EgaSApplication.exceptions.InvalidOperationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> handleException(EntityNotFoundException exception, WebRequest webRequest){
        final HttpStatus notfound=HttpStatus.NOT_FOUND;
        final ErrorDto errorDto = ErrorDto.builder()
                .httpcode(notfound.value())
                .message(exception.getMessage())
                .build();

        return new ResponseEntity<>(errorDto,notfound);

    }
    @ExceptionHandler(InvalidEntityException.class)
    public ResponseEntity<ErrorDto> handleException(InvalidEntityException exception,WebRequest webRequest){
        final HttpStatus badRequest=HttpStatus.BAD_REQUEST;

        final ErrorDto errorDto = ErrorDto.builder()
                .httpcode(badRequest.value())
                .message(exception.getMessage())
                .build();

        return new ResponseEntity<>(errorDto,badRequest);
    }

    @ExceptionHandler(InvalidOperationException.class)
    public ResponseEntity<ErrorDto> handlerException(InvalidOperationException exception, WebRequest webRequest){
        final HttpStatus nofound =HttpStatus.UNPROCESSABLE_ENTITY;

        final ErrorDto errorDto = ErrorDto.builder()
                .httpcode(nofound.value())
                .message(exception.getMessage())
                .build();

        return new ResponseEntity<>(errorDto,nofound);
    }
}
