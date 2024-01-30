package GLSIB_GROUPE5.example.EgaSApplication.dto;

import GLSIB_GROUPE5.example.EgaSApplication.constants.TypeCompte;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompteRequestDto {
    private TypeCompte typeCompte;
    //private BigDecimal sm;
    private String password;
    private int proprietaireId;

}

