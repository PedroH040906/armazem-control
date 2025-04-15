package rutz.armazens.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import rutz.armazens.dtos.SaldoDTO;
import rutz.armazens.dtos.SaldoResponseDTO;
import rutz.armazens.dtos.SaldoTranfeDTO;
import rutz.armazens.dtos.SaldoUpdateDTO;
import rutz.armazens.services.SaldoService;

@RestController
@RequestMapping("/saldo")
public class SaldoController {

    private final SaldoService service;

    public SaldoController(SaldoService serivce) {
        this.service = serivce;
    }

    @PostMapping
    public ResponseEntity criarSaldo(@RequestBody SaldoDTO saldoDTO) {
        try {
            SaldoResponseDTO response = service.criarSaldo(saldoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }
    @PutMapping("/adicionar/{id}")
    public ResponseEntity<SaldoResponseDTO> adicicionarSaldo(@PathVariable Long id, @RequestBody SaldoUpdateDTO saldoDTO) {
        return ResponseEntity.ok(service.adicicionarSaldo(id, saldoDTO));
    }

    @PutMapping("/remover/{id}")
    public ResponseEntity<SaldoResponseDTO> removerSaldo(@PathVariable Long id,@RequestBody SaldoUpdateDTO saldoDTO ){
        return ResponseEntity.ok(service.removerSaldo(id,saldoDTO));
    }

    @PostMapping("/transferir")
    public ResponseEntity<SaldoResponseDTO> transferirSaldo(@RequestBody @Validated SaldoTranfeDTO dto) {
        SaldoResponseDTO response = service.transferirSaldo(dto);
        return ResponseEntity.ok(response);
    }

}