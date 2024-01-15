package GLSIB_GROUPE5.example.EgaSApplication.dto;

import GLSIB_GROUPE5.example.EgaSApplication.constants.TypeCompte;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompteDto {
    private String numCompte;
    private TypeCompte typeCompte;
    private LocalDateTime dateCreated;
    private BigDecimal solde;

    private int proprietaireId;

}

