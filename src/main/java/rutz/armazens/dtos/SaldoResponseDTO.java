package rutz.armazens.dtos;

import rutz.armazens.domain.Saldo;

import java.time.LocalDateTime;

public record SaldoResponseDTO(
        LocalDateTime data,
        ArmazemResponseDTO armazem,
        ResponseProdutoDTO produto,
        int saldo
) {
    public SaldoResponseDTO(Saldo saldo){
        this(
                saldo.getData(),
                new ArmazemResponseDTO(saldo.getArmazem().getDesc(), saldo.getArmazem().getId()),
                new ResponseProdutoDTO(saldo.getProduto().getDesc(), saldo.getProduto().getUm(), saldo.getProduto().getId()),
                saldo.getSaldo()
        );
    }
}