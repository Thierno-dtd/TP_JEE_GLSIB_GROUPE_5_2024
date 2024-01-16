package GLSIB_GROUPE5.example.EgaSApplication.controllers;

import GLSIB_GROUPE5.example.EgaSApplication.constants.TypeCompte;
import GLSIB_GROUPE5.example.EgaSApplication.dto.CompteDto;
import GLSIB_GROUPE5.example.EgaSApplication.services.serviceImpl.CompteService;
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
    public ResponseEntity<CompteDto> register(@RequestBody CompteDto compteDto){
        return ResponseEntity.ok(compteService.ajouterCompte(compteDto));
    }

    @GetMapping("/byUserId/{id}")
    public ResponseEntity<List<CompteDto>> getAllCompteByUserId(@PathVariable int id){
        return  ResponseEntity.ok(compteService.getAllCptByUser(id));
    }

    @GetMapping("/byNumCpt/{numCpt}")
    public ResponseEntity<CompteDto> getAllCompteNumCompte(@PathVariable String numCpt){
        return  ResponseEntity.ok(compteService.getOneCompte(numCpt));
    }

    @GetMapping("/byTypeAndId/{id}")
    public ResponseEntity<List<CompteDto>> getAllCompteTypeAndUserId(@PathVariable int id, @PathVariable TypeCompte typeCompte){
        return  ResponseEntity.ok(compteService.getCompteByType(typeCompte, id));
    }
}
