package GLSIB_GROUPE5.example.EgaSApplication.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@DiscriminatorValue("E")
public class CompteEpargne extends Compte {
    @Min(0)
    private BigDecimal taux2Interet;
}
