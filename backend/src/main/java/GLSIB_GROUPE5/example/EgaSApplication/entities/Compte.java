package GLSIB_GROUPE5.example.EgaSApplication.entities;

import GLSIB_GROUPE5.example.EgaSApplication.constants.TypeCompte;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
//@Builder
@DiscriminatorColumn(name = "compteType")
public abstract class Compte {
    @Id
    private String numCompte;
    private TypeCompte typeCompte;
    private LocalDateTime dateCreated;
    private BigDecimal solde;
    @ManyToOne
    @JoinColumn(name = "proprietaireId")
    private User proprietaire;
    @OneToMany(mappedBy = "numCpt")
    private List<Operation> numCpt;
}

