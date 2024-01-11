package GLSIB_GROUPE5.example.EgaSApplication.dto;

import GLSIB_GROUPE5.example.EgaSApplication.constants.TypeOperation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OperationDto {
    private LocalDateTime operationDate;
    private BigDecimal montant;
    private TypeOperation type;
    private int clientId;
    private String numCpt;
}

