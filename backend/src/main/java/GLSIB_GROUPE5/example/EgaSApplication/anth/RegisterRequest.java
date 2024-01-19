package GLSIB_GROUPE5.example.EgaSApplication.anth;


import GLSIB_GROUPE5.example.EgaSApplication.constants.TypeSexe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String nom;
    private String prenom;
    private String email;
    private String passwd;
    private TypeSexe sexe;
    private LocalDate dateNaiss;
    private String nationnalite;
    private String telephone;
    private String adresse;

}