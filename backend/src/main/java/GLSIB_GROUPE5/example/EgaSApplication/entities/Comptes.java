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
@DiscriminatorColumn(name = "compteType")
public abstract class Comptes {

    @Id
    private String numCompte;
    private TypeCompte typeCompte;
    private LocalDateTime dateCreated;
    private BigDecimal solde;
    @ManyToOne
    @JoinColumn(name = "proprietaireId")
    private Users proprietaire;
    @OneToMany(mappedBy = "numCpt")
    private List<Operations> numCpt;
}

