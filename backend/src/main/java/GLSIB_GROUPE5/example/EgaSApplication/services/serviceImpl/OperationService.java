package GLSIB_GROUPE5.example.EgaSApplication.services.serviceImpl;

import GLSIB_GROUPE5.example.EgaSApplication.constants.TypeCompte;
import GLSIB_GROUPE5.example.EgaSApplication.constants.TypeOperation;
import GLSIB_GROUPE5.example.EgaSApplication.dto.CompteDto;
import GLSIB_GROUPE5.example.EgaSApplication.dto.OperationDto;
import GLSIB_GROUPE5.example.EgaSApplication.dto.TransfertDto;
import GLSIB_GROUPE5.example.EgaSApplication.dto.VirementDto;
import GLSIB_GROUPE5.example.EgaSApplication.entities.Compte;
import GLSIB_GROUPE5.example.EgaSApplication.entities.Operation;
import GLSIB_GROUPE5.example.EgaSApplication.entities.User;
import GLSIB_GROUPE5.example.EgaSApplication.exceptions.InvalidEntityException;
import GLSIB_GROUPE5.example.EgaSApplication.exceptions.InvalidOperationException;
import GLSIB_GROUPE5.example.EgaSApplication.mappers.ApplicationsMapper;
import GLSIB_GROUPE5.example.EgaSApplication.repositories.OperationRepository;
import GLSIB_GROUPE5.example.EgaSApplication.services.IOperationServcie;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OperationService implements IOperationServcie {
    private final OperationRepository operationRepository;
    private final ApplicationsMapper applicationsMapper;
    private final CompteService compteService;
    @Override
    public OperationDto debit(TransfertDto transfertDto, int id) {
        Compte compte = compteService.getOneCompte(transfertDto.getAccountId());
        if(compte == null) throw new InvalidOperationException("Ce numéros de compte n'est pas valid");
        else {
            compte.setSolde(compte.getSolde().add(transfertDto.getAmount()));
            compteService.ajouterCompte(compte);
            return applicationsMapper.convertEntityToDto(
                    operationRepository.save(
                            Operation.builder()
                                    .type(TypeOperation.DEBIT)
                                    .client(User.builder().id(id).build())
                                    .date(LocalDateTime.now())
                                    .montant(transfertDto.getAmount())
                                    .numCpt(Compte.builder().numCompte(transfertDto.getAccountId()).build())
                                    .build()));
        }
    }

    @Override
    public OperationDto credit(TransfertDto transfertDto, int id) {
        /* cpt = compteService.getOneCompte(transfertDto.getAccountId());
        if(cpt.getProprietaireId() != id) throw new InvalidEntityException(" Vous ne possédez pas ce compte là");
        if(cpt.getSolde().compareTo(transfertDto.getAmount()) == -1){
            if(cpt.getTypeCompte() == TypeCompte.C){
                if(cpt.getSolde().plus(cpt.get))
            }
        }*/
        Compte compte = compteService.getOneCompte(transfertDto.getAccountId());
        if(compte == null) throw new InvalidOperationException("Ce numéros de compte n'est pas valide");
        else {
            compte.setSolde(compte.getSolde().subtract(transfertDto.getAmount()));
            compteService.ajouterCompte(compte);
            return applicationsMapper.convertEntityToDto(
                    operationRepository.save(
                            Operation.builder()
                                    .type(TypeOperation.CREDIT)
                                    .client(User.builder().id(id).build())
                                    .date(LocalDateTime.now())
                                    .montant(transfertDto.getAmount())
                                    .numCpt(Compte.builder().numCompte(transfertDto.getAccountId()).build())
                                    .build()));
        }
    }

    @Override
    public List<OperationDto> virement(VirementDto virementDto, int id) {
        List<OperationDto> lstOp = new ArrayList<>();
        lstOp.add(credit(dtoToDto(virementDto), id));
        lstOp.add(debit(dtoToDto(virementDto), id));
        return lstOp;
    }

    public TransfertDto dtoToDto(VirementDto virementDto){
        return TransfertDto.builder()
                .accountId(virementDto.getAccountSource())
                .amount(virementDto.getAmount())
                .build();
    }
}
