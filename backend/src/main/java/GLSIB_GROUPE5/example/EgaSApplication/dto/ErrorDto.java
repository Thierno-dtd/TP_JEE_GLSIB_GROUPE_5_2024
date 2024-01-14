package GLSIB_GROUPE5.example.EgaSApplication.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDto {
    private Integer httpcode;

    //private ErrorCodes codes;

    private String message;

    //private List<String> error=new ArrayList<>();
}
