package GLSIB_GROUPE5.example.EgaSApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class VirementDto {
    private String accountSource;
    private String accountDestination;
    private BigDecimal amount;
}
