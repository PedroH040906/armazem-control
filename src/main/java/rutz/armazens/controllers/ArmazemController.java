package rutz.armazens.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rutz.armazens.domain.Armazem;
import rutz.armazens.dtos.ArmazemResponseDTO;
import rutz.armazens.dtos.ArmazenDTO;
import rutz.armazens.services.ArmazemService;

import java.util.List;

@RestController
@RequestMapping("/armazem")
public class ArmazemController {

    private final ArmazemService service;

    public ArmazemController(ArmazemService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ArmazemResponseDTO> criarArmazem(@RequestBody ArmazenDTO armazenDTO){
        try {
            ArmazemResponseDTO response = service.criarArmazem(armazenDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
       }
   }

    @GetMapping
    public ResponseEntity<List<Armazem>> listarTodos(){
        List<Armazem> lista = service.listarArmazem();
        return ResponseEntity.ok(lista);
    }
}
