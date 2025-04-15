package rutz.armazens.dtos;

import lombok.Data;
import rutz.armazens.domain.Produto;
import rutz.armazens.domain.Saldo;
import rutz.armazens.domain.Tipo;

@Data
public class HistoricoDTO {

    private Long id;

    private Produto produto;

    private Tipo tipo;

    private Saldo saldo;

    private int quantidade;
}
