package GLSIB_GROUPE5.example.EgaSApplication.dto;

import GLSIB_GROUPE5.example.EgaSApplication.entities.Compte;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
//@Builder
@Data
public class CompteEpargneDto extends CompteDto {
    private BigDecimal taux2Interet;
}
