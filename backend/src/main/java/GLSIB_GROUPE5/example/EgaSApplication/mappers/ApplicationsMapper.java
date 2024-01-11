package GLSIB_GROUPE5.example.EgaSApplication.mappers;

import GLSIB_GROUPE5.example.EgaSApplication.constants.TypeCompte;
import GLSIB_GROUPE5.example.EgaSApplication.dto.CompteCourantDto;
import GLSIB_GROUPE5.example.EgaSApplication.dto.CompteDto;
import GLSIB_GROUPE5.example.EgaSApplication.dto.OperationDto;
import GLSIB_GROUPE5.example.EgaSApplication.dto.UserDto;
import GLSIB_GROUPE5.example.EgaSApplication.entities.Compte;
import GLSIB_GROUPE5.example.EgaSApplication.entities.CompteCourant;
import GLSIB_GROUPE5.example.EgaSApplication.entities.Operation;
import GLSIB_GROUPE5.example.EgaSApplication.entities.User;

public class ApplicationsMapper {
    private UserDto convertEntityToDto(User user){
        return UserDto.builder()
                .nom(user.getNom())
                .prenom(user.getPrenom())
                .email(user.getEmail())
                .sexe(user.getSexe())
                .nationnalite(user.getNationnalite())
                .telephone(user.getTelephone())
                .adresse(user.getAdresse())
                .dateNaiss(user.getDateNaiss())
                .password(user.getPassword())
                .build();
    }

    private User convertDtoToEntity(UserDto userDto){
        return User.builder()
                .nom(userDto.getNom())
                .prenom(userDto.getPrenom())
                .adresse(userDto.getAdresse())
                .dateNaiss(userDto.getDateNaiss())
                .email(userDto.getEmail())
                .email(userDto.getEmail())
                .sexe(userDto.getSexe())
                .password(userDto.getPassword())
                .nationnalite(userDto.getNationnalite())
                .telephone(userDto.getTelephone())
                .build();
    }

    private OperationDto convertEntityToDto(Operation operation){
        return OperationDto.builder()
                .type(operation.getType())
                .montant(operation.getMontant())
                .clientId(operation.getClient().getId())
                .numCpt(operation.getNumCpt().getNumCompte())
                .operationDate(operation.getDate())
                .build();
    }


    /*private CompteCourantDto convertEntityToDto(CompteCourant compteCourant){
        return CompteCourantDto.builder()
                .decouvertAutorise(compteCourant.getD)

                .build();
    }*/
}
