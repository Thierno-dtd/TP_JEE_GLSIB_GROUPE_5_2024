package GLSIB_GROUPE5.example.EgaSApplication.mappers;

import GLSIB_GROUPE5.example.EgaSApplication.constants.TypeCompte;
import GLSIB_GROUPE5.example.EgaSApplication.dto.*;
import GLSIB_GROUPE5.example.EgaSApplication.entities.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ApplicationsMapper {
    public UserDto convertEntityToDto(User user){
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

    public User convertDtoToEntity(UserDto userDto){
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

    public OperationDto convertEntityToDto(Operation operation){
        return OperationDto.builder()
                .type(operation.getType())
                .montant(operation.getMontant())
                .clientId(operation.getClient().getId())
                .numCpt(operation.getNumCpt().getNumCompte())
                .operationDate(operation.getDate())
                .build();
    }


    public CompteCourantDto convertEntityToDto(CompteCourant compteCourant){
        CompteCourantDto compteCourantDto = new CompteCourantDto();
        BeanUtils.copyProperties(compteCourant, compteCourantDto);
        compteCourantDto.setProprietaireId(compteCourant.getProprietaire().getId());
        compteCourantDto.setTypeCompte(compteCourant.getTypeCompte());
        return  compteCourantDto;
    }
    public CompteCourant convertDtoToEntity(CompteCourantDto compteCourantDto){
        CompteCourant compteCourant = new CompteCourant();
        BeanUtils.copyProperties(compteCourantDto,compteCourant);
        compteCourant.setProprietaire(User.builder().id(compteCourantDto.getProprietaireId()).build());
        //compteCourant.setTypeCompte(compteCourantDto.);
        return compteCourant;
    }

    public <T> T convertEntityToDto(Compte compte){
        if(compte.getTypeCompte() == TypeCompte.E){
            CompteEpargneDto compteEpargneDto = new CompteEpargneDto();
            BeanUtils.copyProperties(compte, compteEpargneDto);
            compteEpargneDto.setProprietaireId(compte.getProprietaire().getId());
            compteEpargneDto.setTypeCompte(compte.getTypeCompte());
            return (T) compteEpargneDto;
        }
        if(compte.getTypeCompte() == TypeCompte.C){
            CompteCourantDto compteCourantDto = new CompteCourantDto();
            BeanUtils.copyProperties(compte, compteCourantDto);
            compteCourantDto.setProprietaireId(compte.getProprietaire().getId());
            compteCourantDto.setTypeCompte(compte.getTypeCompte());
            return (T) compteCourantDto;
        }
        return null;
    }
    public <T> T convertDtoToEntity(CompteDto compteDto){
        if(compteDto.getTypeCompte() == TypeCompte.C){
            CompteCourant compteCourant = new CompteCourant();
            BeanUtils.copyProperties(compteDto, compteCourant);
            compteCourant.setProprietaire(User.builder().id(compteDto.getProprietaireId()).build());
            return (T) compteCourant;
        }
        if(compteDto.getTypeCompte() == TypeCompte.E) {
            CompteEpargne compteEpargne = new CompteEpargne();
            BeanUtils.copyProperties(compteDto, compteEpargne);
            compteEpargne.setProprietaire(User.builder().id(compteDto.getProprietaireId()).build());
            //compteCourant.setTypeCompte(compteCourantDto.);
            return (T) compteEpargne;
        }
        return null;
    }
}
