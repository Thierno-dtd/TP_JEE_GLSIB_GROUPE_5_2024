package GLSIB_GROUPE5.example.EgaSApplication.services;

import GLSIB_GROUPE5.example.EgaSApplication.constants.TypeCompte;
import GLSIB_GROUPE5.example.EgaSApplication.dto.CompteCourantDto;
import GLSIB_GROUPE5.example.EgaSApplication.dto.CompteDto;
import GLSIB_GROUPE5.example.EgaSApplication.dto.CompteEpargneDto;
import GLSIB_GROUPE5.example.EgaSApplication.dto.CompteRequestDto;

import java.util.List;

public interface ICompteServices {
    <T> T ajouterCompte(CompteRequestDto compteDto);
    <T> T getOneCompte(String numCpt);
    <T> T getCompteByType(TypeCompte typeCompte, int id);
    List<CompteDto> getAllCptByUser(int id);
}
