package GLSIB_GROUPE5.example.EgaSApplication.entities;

import GLSIB_GROUPE5.example.EgaSApplication.constants.TypeSexe;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Le champs nom n'est pas valide")
    private String nom;
    @NotBlank(message = " Le champs prenom c'est pas valide")
    private String prenom;
    private String password;
    private LocalDate dateNaiss;
    @Enumerated(EnumType.STRING)
    private TypeSexe sexe;
    @NotBlank(message = "Veuillez renseignez votre adresse")
    private String adresse;
    @NotBlank(message = "veuillez renseignez votre telephone")
    private String telephone;
    @Email
    private String email;
    @NotBlank(message = "veuillez renseignez votre nationnalité")
    private String nationnalite;
    @OneToMany(mappedBy = "proprietaire")
    private List<Compte> comptes;
    @OneToMany(mappedBy = "client")
    private List<Operation> operations;

}
