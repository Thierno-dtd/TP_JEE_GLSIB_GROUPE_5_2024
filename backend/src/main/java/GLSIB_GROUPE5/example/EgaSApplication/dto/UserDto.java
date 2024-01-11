package GLSIB_GROUPE5.example.EgaSApplication.dto;

import GLSIB_GROUPE5.example.EgaSApplication.constants.TypeSexe;
import GLSIB_GROUPE5.example.EgaSApplication.entities.Compte;
import GLSIB_GROUPE5.example.EgaSApplication.entities.Operation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDto {
    private String nom;
    private String prenom;
    private String password;
    private LocalDate dateNaiss;
    private TypeSexe sexe;
    private String adresse;
    private String telephone;
    private String email;
    private String nationnalite;
}
