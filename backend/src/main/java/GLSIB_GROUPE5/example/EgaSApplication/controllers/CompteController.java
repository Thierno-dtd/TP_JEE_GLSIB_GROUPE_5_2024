package GLSIB_GROUPE5.example.EgaSApplication.controllers;

import GLSIB_GROUPE5.example.EgaSApplication.constants.TypeCompte;
import GLSIB_GROUPE5.example.EgaSApplication.dto.CompteDto;
import GLSIB_GROUPE5.example.EgaSApplication.dto.CompteRequestDto;
import GLSIB_GROUPE5.example.EgaSApplication.services.serviceImpl.CompteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comptes")
@RequiredArgsConstructor
public class CompteController {
    private final CompteService compteService;

    @PostMapping("/")
    @ApiOperation("Ajouter une nouveau compte")
    public ResponseEntity<CompteDto> register(
            @RequestBody
            @ApiParam(name = "compteDto", value = "fournir le compte", required = true)
            CompteRequestDto compteDto){

        return ResponseEntity.ok(compteService.ajouterCompte(compteDto));
    }

    @GetMapping("/byUserId/{id}")
    @ApiOperation("Get listCompte with userId")
    public List<CompteDto> getAllCompteByUserId(
            @PathVariable
            @ApiParam(name = "id", value = " userId", required = true) int id){
        return compteService.getAllCptByUser(id);
    }

    @GetMapping("/byNumCpt/{numCpt}")
    @ApiOperation("Get compte by numcpt")
    public CompteDto getAllCompteNumCompte(
            @PathVariable
            @ApiParam(name = "numCpt", value = "numero de cpt", required = true) String numCpt){
        return  compteService.getOneCompte(numCpt);
    }

    @GetMapping("/byTypeAndId/{id}/{typeCompte}")
    @ApiOperation("get y typeCompte and UserId")
    public List<CompteDto> getAllCompteTypeAndUserId(
            @PathVariable
            @ApiParam(name = "id", value = "UserId", required = true)
            int id,
            @PathVariable
            @ApiParam(name = "typeCompte", value = " le type de compte", required = true) TypeCompte typeCompte){
        return  compteService.getCompteByType(typeCompte, id);
    }
}
