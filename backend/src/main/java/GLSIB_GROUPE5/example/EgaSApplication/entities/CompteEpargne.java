package GLSIB_GROUPE5.example.EgaSApplication.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DiscriminatorValue("E")
public class CompteEpargne extends Comptes{
    private BigDecimal taux2Interet;
}
