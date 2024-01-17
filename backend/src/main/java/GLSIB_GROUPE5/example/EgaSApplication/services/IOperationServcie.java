package GLSIB_GROUPE5.example.EgaSApplication.services;

import GLSIB_GROUPE5.example.EgaSApplication.dto.OperationDto;
import GLSIB_GROUPE5.example.EgaSApplication.dto.TransfertDto;
import GLSIB_GROUPE5.example.EgaSApplication.dto.VirementDto;

import java.util.List;

public interface IOperationServcie {
    public OperationDto debit(TransfertDto transfertDto, int id);
    public OperationDto credit(TransfertDto transfertDto, int id);
    public List<OperationDto> virement(VirementDto virementDto, int id);
}
