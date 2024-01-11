package GLSIB_GROUPE5.example.EgaSApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class VirementDto {
    private String accountSource;
    private String accountDestination;
    private double amount;
    private String description;
}
