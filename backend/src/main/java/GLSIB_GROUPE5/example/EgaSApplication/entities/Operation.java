package GLSIB_GROUPE5.example.EgaSApplication.entities;

import GLSIB_GROUPE5.example.EgaSApplication.constants.TypeOperation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime date;
    private TypeOperation type;
    private BigDecimal montant;
    @ManyToOne
    @JoinColumn(name = "compte_id")
    private User client;

    private String numCpt;

}
