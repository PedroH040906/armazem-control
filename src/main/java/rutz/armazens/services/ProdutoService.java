package rutz.armazens.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import rutz.armazens.domain.Produto;
import rutz.armazens.dtos.ProdutoDTO;
import rutz.armazens.dtos.ResponseProdutoDTO;
import rutz.armazens.repository.ProdutoRepository;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;


    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public ResponseProdutoDTO criarProduto(ProdutoDTO produtoDTO){
        Produto produto = new Produto();
        produto.setDesc(produtoDTO.getDesc());
        produto.setUm(produtoDTO.getUm());

        repository.save(produto);
        return new ResponseProdutoDTO(produto);
    }

    public List<Produto> listarProdutos(){
        return repository.findAll();
    }



}
