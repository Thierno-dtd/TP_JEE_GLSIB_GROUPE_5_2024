package GLSIB_GROUPE5.example.EgaSApplication.services;

import GLSIB_GROUPE5.example.EgaSApplication.dto.*;
import GLSIB_GROUPE5.example.EgaSApplication.entities.Operation;

import java.time.LocalDate;
import java.util.List;

public interface IOperationServcie {
    public OperationDto debit(TransfertDto transfertDto, int id);
    public OperationDto credit(TransfertDto transfertDto, int id);
    public List<OperationDto> virement(VirementDto virementDto, int id);
    public List<OperationDto> listeOperationByNumCpt(RequestOperationDto requestOperationDto);
    public List<OperationDto> listeOperationByClientId(RequestOperationIDto requestOperationDto);
}
