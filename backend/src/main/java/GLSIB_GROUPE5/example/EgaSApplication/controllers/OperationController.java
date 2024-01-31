package GLSIB_GROUPE5.example.EgaSApplication.controllers;

import GLSIB_GROUPE5.example.EgaSApplication.dto.*;
import GLSIB_GROUPE5.example.EgaSApplication.services.serviceImpl.OperationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/operations")
public class OperationController {
    private final OperationService operationService;
    @PostMapping("debit/{id}")
    @ApiOperation("debit")
    public OperationDto debit(
            @RequestBody
            @ApiParam(name = "transfertDto", required = true)
             TransfertDto transfertDto,
            @PathVariable
            @ApiParam(name = "id", required = true) int id){
        return  operationService.debit(transfertDto, id);
    }

    @PostMapping("credit/{id}")
    public OperationDto credi(@RequestBody TransfertDto transfertDto, @PathVariable int id){
        return  operationService.credit(transfertDto, id);
    }

    @PostMapping("virement/{id}")
    @ApiOperation("virement")
    public List<OperationDto> virement(
            @RequestBody
            @ApiParam(name = "virementDto", required = true) VirementDto virementDto,
            @PathVariable
            @ApiParam(name = "id", required = true)int id){
        return  operationService.virement(virementDto, id);
    }

    @PostMapping("ListeOperations/")
    @ApiOperation("getOperation")
    public List<OperationDto> getOperation(
            @RequestBody
            @ApiParam(name = "requestOperationDto", required = true) RequestOperationDto requestOperationDto){
        return  operationService.listeOperationByNumCpt(requestOperationDto);
    }

    @PostMapping("ListeOperationsByClient/")
    @ApiOperation("getOperation")
    public List<OperationDto> getOperationByClient(
            @RequestBody
            @ApiParam(name = "requestOperationDto", required = true) RequestOperationIDto requestOperationDto){
        return  operationService.listeOperationByClientId(requestOperationDto);
    }
}
