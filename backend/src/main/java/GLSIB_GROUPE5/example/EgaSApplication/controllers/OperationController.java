package GLSIB_GROUPE5.example.EgaSApplication.controllers;

import GLSIB_GROUPE5.example.EgaSApplication.dto.OperationDto;
import GLSIB_GROUPE5.example.EgaSApplication.dto.TransfertDto;
import GLSIB_GROUPE5.example.EgaSApplication.dto.VirementDto;
import GLSIB_GROUPE5.example.EgaSApplication.services.serviceImpl.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/operations")
public class OperationController {
    private final OperationService operationService;
    @PostMapping("debit/{id}")
    public ResponseEntity<OperationDto> debit(@RequestBody TransfertDto transfertDto, @PathVariable int id){
        return  ResponseEntity.ok(operationService.debit(transfertDto, id));
    }

    @PostMapping("credit/{id}")
    public ResponseEntity<OperationDto> credi(@RequestBody TransfertDto transfertDto, @PathVariable int id){
        return  ResponseEntity.ok(operationService.credit(transfertDto, id));
    }

    @PostMapping("virement/{id}")
    public ResponseEntity<List<OperationDto>> virement(@RequestBody VirementDto virementDto, @PathVariable int id){
        return  ResponseEntity.ok(operationService.virement(virementDto, id));
    }

    @GetMapping("ListeOperations/{numCpt}/{date}")
    public ResponseEntity<List<OperationDto>> getOperation(@PathVariable String numCpt, @PathVariable LocalDate date){
        return  ResponseEntity.ok(operationService.listeOperation(numCpt, date));
    }
}