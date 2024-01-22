package GLSIB_GROUPE5.example.EgaSApplication.services.serviceImpl;

import GLSIB_GROUPE5.example.EgaSApplication.constants.TypeOperation;
import GLSIB_GROUPE5.example.EgaSApplication.dto.*;
import GLSIB_GROUPE5.example.EgaSApplication.entities.Compte;
import GLSIB_GROUPE5.example.EgaSApplication.entities.Operation;
import GLSIB_GROUPE5.example.EgaSApplication.entities.User;
import GLSIB_GROUPE5.example.EgaSApplication.exceptions.InvalidEntityException;
import GLSIB_GROUPE5.example.EgaSApplication.exceptions.InvalidOperationException;
import GLSIB_GROUPE5.example.EgaSApplication.mappers.ApplicationsMapper;
import GLSIB_GROUPE5.example.EgaSApplication.repositories.OperationRepository;
import GLSIB_GROUPE5.example.EgaSApplication.services.IOperationServcie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OperationService implements IOperationServcie {
    private final OperationRepository operationRepository;
    private final ApplicationsMapper applicationsMapper;
    private final CompteService compteService;
    @Override
    public OperationDto debit(TransfertDto transfertDto, int id) {
        CompteDto compte = compteService.getOneCompte(transfertDto.getAccountId());
        log.info(compte.toString());
        /*if(compte == null) throw new InvalidOperationException("Ce numéros de compte n'est pas valid");
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
                                    //.numCpt(Compte.builder().numCompte(transfertDto.getAccountId()).build())
                                    .build()));
        }*/
        return null;
    }

    @Override
    public OperationDto credit(TransfertDto transfertDto, int id) {
        if(compteService.getOneCompte(transfertDto.getAccountId()) instanceof CompteCourantDto){
            CompteCourantDto cpt = compteService.getOneCompte(transfertDto.getAccountId());
            if(cpt.getProprietaireId() != id) throw new InvalidEntityException(" Vous ne possédez pas ce compte là");
            if((cpt.getSolde().add(cpt.getDecouvertAutorise())).compareTo(transfertDto.getAmount()) == -1) throw new InvalidOperationException("Votre solde ne vous permet pas cette opération");
        }
        if(compteService.getOneCompte(transfertDto.getAccountId()) instanceof CompteEpargneDto){
            CompteEpargneDto cpt = compteService.getOneCompte(transfertDto.getAccountId());
            if(cpt.getProprietaireId() != id) throw new InvalidEntityException(" Vous ne possédez pas ce compte là");
            if(cpt.getSolde().compareTo(transfertDto.getAmount()) == -1) throw new InvalidOperationException("Votre solde ne vous permet pas cette opération");
        }

        Compte compte = compteService.getOneCompte(transfertDto.getAccountId());
        if(compte == null) throw new InvalidOperationException("Ce numéros de compte n'est pas valide");
        /*else {
            compte.setSolde(compte.getSolde().subtract(transfertDto.getAmount()));
            compteService.ajouterCompte(compte);
            return applicationsMapper.convertEntityToDto(
                    operationRepository.save(
                            Operation.builder()
                                    .type(TypeOperation.CREDIT)
                                    .client(User.builder().id(id).build())
                                    .date(LocalDateTime.now())
                                    .montant(transfertDto.getAmount())
                                    //.numCpt(Compte.builder().numCompte(transfertDto.getAccountId()).build())
                                    .build()));
        }*/ return null;
    }

    @Override
    public List<OperationDto> virement(VirementDto virementDto, int id) {
        List<OperationDto> lstOp = new ArrayList<>();
        lstOp.add(credit(dtoToDto(virementDto), id));
        lstOp.add(debit(dtoToDto(virementDto), id));
        return lstOp;
    }

    @Override
    public List<OperationDto> listeOperation(String numCpt, LocalDate date) {
        Compte cpt = compteService.getOneCompte(numCpt);
        if(cpt==null) throw new InvalidEntityException("Ce numéro de compte n''existe pas");
        return operationRepository.findByNumCptAndDateAfter(cpt, date).stream().map(op -> applicationsMapper.convertEntityToDto(op)).collect(Collectors.toList());
    }

    public TransfertDto dtoToDto(VirementDto virementDto){
        return TransfertDto.builder()
                .accountId(virementDto.getAccountSource())
                .amount(virementDto.getAmount())
                .build();
    }

}
