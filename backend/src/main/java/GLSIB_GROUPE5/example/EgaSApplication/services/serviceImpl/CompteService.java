package GLSIB_GROUPE5.example.EgaSApplication.services.serviceImpl;

import GLSIB_GROUPE5.example.EgaSApplication.constants.TypeCompte;
import GLSIB_GROUPE5.example.EgaSApplication.dto.CompteDto;
import GLSIB_GROUPE5.example.EgaSApplication.entities.Compte;
import GLSIB_GROUPE5.example.EgaSApplication.exceptions.EntityNotFoundException;
import GLSIB_GROUPE5.example.EgaSApplication.exceptions.InvalidEntityException;
import GLSIB_GROUPE5.example.EgaSApplication.mappers.ApplicationsMapper;
import GLSIB_GROUPE5.example.EgaSApplication.repositories.CompteRepository;
import GLSIB_GROUPE5.example.EgaSApplication.services.ICompteServices;
import lombok.RequiredArgsConstructor;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompteService implements ICompteServices {
    private final ApplicationsMapper applicationsMapper;
    private final CompteRepository compteRepository;
    private  final UserService userService;
    @Override
    public <T> T ajouterCompte(CompteDto compteDto) {
        Compte compte = getOneCompte(compteDto.getNumCompte());
        if(compte != null) throw new EntityNotFoundException("Ce compte existe deja");
        else {
            String nouveauNumCompte = generateFormattedIban();
            compteDto.setNumCompte(nouveauNumCompte);
            return (T) compteRepository.save(applicationsMapper.convertDtoToEntity(compteDto));
        }
    }

    @Override
    public <T> T getOneCompte(String numCpt) {
        Compte compte = compteRepository.findById(numCpt).orElse(null);
        if(compte == null) throw new EntityNotFoundException("Le numéro de compte n'est pas valide");
        return applicationsMapper.convertEntityToDto(compte);
    }

    @Override
    public List<CompteDto> getCompteByType(TypeCompte typeCompte, int id) {
        boolean userExist = userService.VerifIFUserExist(id);
        if(userExist) {
           List<Compte> lstCompte = compteRepository.findByProprietaireIdAndTypeCompte(id, typeCompte);
           return lstCompte.stream().map(cpt -> (CompteDto) applicationsMapper.convertEntityToDto(cpt)).collect(Collectors.toList());
        }
        else throw new InvalidEntityException("l'utilisateur n'existe pas");
    }

    @Override
    public List<CompteDto> getAllCptByUser(int id) {
        List<Compte> lstCpt = compteRepository.findByProprietaireId(id);
        return lstCpt.stream().map(cpt -> (CompteDto) applicationsMapper.convertEntityToDto(cpt)).collect(Collectors.toList());
    }

    private String generateFormattedIban() {
        // Générez un IBAN standard avec iban4j
        Iban iban = Iban.random(CountryCode.FR);

        // Récupérez la représentation du numéro de compte de l'IBAN
        String ibanAccountNumber = iban.getAccountNumber();

        // Ajoutez le préfixe "EGA" au numéro de compte de l'IBAN
        return "EGA" + ibanAccountNumber;
    }
}
