package rutz.armazens.dtos;

import rutz.armazens.domain.Produto;
import rutz.armazens.domain.UM;

public record ResponseProdutoDTO(String descricao, UM um, Long id){

    public ResponseProdutoDTO(Produto produto) {
        this(produto.getDesc(), produto.getUm(), produto.getId());
    }
}
