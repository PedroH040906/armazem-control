package rutz.armazens.dtos;

import rutz.armazens.domain.Armazem;
import rutz.armazens.domain.Produto;

public record SaldoTranfeDTO (Produto produto, Armazem armazemOriginal, Armazem armazemDestino, int quantidade) {
}
