package rutz.armazens.controllers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rutz.armazens.domain.Produto;
import rutz.armazens.dtos.ProdutoDTO;
import rutz.armazens.services.ProdutoService;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping
    ResponseEntity criarProduto(@RequestBody ProdutoDTO produtoDTO){
        try{
            return new ResponseEntity<>(this.service.criarProduto(produtoDTO), HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(this.service.criarProduto(produtoDTO), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos(){
        List<Produto> lista = service.listarProdutos();
        return ResponseEntity.ok(lista);
    }
}
