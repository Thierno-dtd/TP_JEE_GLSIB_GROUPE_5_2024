package GLSIB_GROUPE5.example.EgaSApplication.services.serviceImpl;

import GLSIB_GROUPE5.example.EgaSApplication.constants.TypeOperation;
import GLSIB_GROUPE5.example.EgaSApplication.dto.*;
import GLSIB_GROUPE5.example.EgaSApplication.entities.*;
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
import java.time.format.DateTimeFormatter;
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
    private final UserService userService;
    @Override
    public OperationDto debit(TransfertDto transfertDto, int id) {
        //Compte compte =
        if(compteService.getOneCompte(transfertDto.getAccountId()) instanceof CompteCourantDto){
            CompteCourant compte = applicationsMapper.convertDtoToEntity((CompteCourantDto) compteService.getOneCompte(transfertDto.getAccountId()));
            log.info("1");
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
                                        .numCpt(transfertDto.getAccountId())
                                        .build()));
            }

        }else
        if(compteService.getOneCompte(transfertDto.getAccountId()) instanceof CompteEpargneDto)
            {
            CompteEpargne compte = applicationsMapper.convertDtoToEntity((CompteEpargneDto) compteService.getOneCompte(transfertDto.getAccountId()));
            log.info("2");
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
                                        .numCpt(transfertDto.getAccountId())
                                        .build()));
            }

        }

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

        if(compteService.getOneCompte(transfertDto.getAccountId()) instanceof CompteCourantDto){

            CompteCourant compte = applicationsMapper.convertDtoToEntity((CompteCourantDto) compteService.getOneCompte(transfertDto.getAccountId()));
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
                                        .numCpt(transfertDto.getAccountId())
                                        .build()));
            }
        }

        if(compteService.getOneCompte(transfertDto.getAccountId()) instanceof CompteEpargneDto){

            CompteEpargne compte = applicationsMapper.convertDtoToEntity((CompteEpargneDto) compteService.getOneCompte(transfertDto.getAccountId()));
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
                                        .numCpt(transfertDto.getAccountId())
                                        .build()));
            }
        }
       return null;
    }

    @Override
    public List<OperationDto> virement(VirementDto virementDto, int id) {
        List<OperationDto> lstOp = new ArrayList<>();
        lstOp.add(credit(dtoToDto(virementDto), id));
        lstOp.add(debit(dtoToDto1(virementDto), id));
        return lstOp;
    }

    @Override
    public List<OperationDto> listeOperationByNumCpt(RequestOperationDto requestOperationDto) {
        if(compteService.getOneCompte(requestOperationDto.getNumCpt()) instanceof CompteEpargneDto){
            CompteEpargne cpt = applicationsMapper.convertDtoToEntity((CompteEpargneDto) compteService.getOneCompte(requestOperationDto.getNumCpt()));
            if(cpt==null) throw new InvalidEntityException("Ce numéro de compte n''existe pas");
            if(requestOperationDto.getDtDetut().isAfter(requestOperationDto.getDtFin())) throw new InvalidOperationException(" la date de depart est supérieur a la date final");
            return operationRepository.findByNumCptAndDateAfterAndDateBefore(requestOperationDto.getNumCpt(),convertirLocalDateEnLocalDateTime(requestOperationDto.getDtDetut()) , convertirLocalDateEnLocalDateTime(requestOperationDto.getDtFin())).stream().map(op -> applicationsMapper.convertEntityToDto(op)).collect(Collectors.toList());

        }if(compteService.getOneCompte(requestOperationDto.getNumCpt()) instanceof CompteCourantDto){
            CompteCourant cpt = applicationsMapper.convertDtoToEntity((CompteCourantDto) compteService.getOneCompte(requestOperationDto.getNumCpt()));
            if(cpt==null) throw new InvalidEntityException("Ce numéro de compte n''existe pas");
            if(requestOperationDto.getDtDetut().isAfter(requestOperationDto.getDtFin())) throw new InvalidOperationException(" la date de depart est supérieur a la date final");
            return operationRepository.findByNumCptAndDateAfterAndDateBefore(requestOperationDto.getNumCpt(),convertirLocalDateEnLocalDateTime(requestOperationDto.getDtDetut()),convertirLocalDateEnLocalDateTime(requestOperationDto.getDtDetut())).stream().map(op -> applicationsMapper.convertEntityToDto(op)).collect(Collectors.toList());

        }
        return null;
       }

    @Override
    public List<OperationDto> listeOperationByClientId(RequestOperationIDto requestOperationDto) {
        if(requestOperationDto.getDtDetut().isAfter(requestOperationDto.getDtFin())) throw new InvalidOperationException(" la date de depart est supérieur a la date final");
        return operationRepository.findByClientAndDateAfterAndDateBefore(User.builder().id(requestOperationDto.getId()).build(), convertirLocalDateEnLocalDateTime(requestOperationDto.getDtDetut()),convertirLocalDateEnLocalDateTime(requestOperationDto.getDtFin())).stream().map(op -> applicationsMapper.convertEntityToDto(op)).collect(Collectors.toList());

    }

    public TransfertDto dtoToDto(VirementDto virementDto){
        return TransfertDto.builder()
                .accountId(virementDto.getAccountSource())
                .amount(virementDto.getAmount())
                .build();
    }

    public TransfertDto dtoToDto1(VirementDto virementDto){
        return TransfertDto.builder()
                .accountId(virementDto.getAccountDestination())
                .amount(virementDto.getAmount())
                .build();
    }

    public  LocalDateTime convertirLocalDateEnLocalDateTime(LocalDate localDate) {
        // Utilisez la méthode atStartOfDay pour convertir LocalDate en LocalDateTime
        return localDate.atStartOfDay();
    }

}
