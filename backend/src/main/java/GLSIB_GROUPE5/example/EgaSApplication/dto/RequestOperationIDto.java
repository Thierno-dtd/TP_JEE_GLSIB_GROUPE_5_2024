package GLSIB_GROUPE5.example.EgaSApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestOperationIDto {
    private int id;
    private LocalDate dtDetut;
    private LocalDate dtFin;
}
