package rutz.armazens.dtos;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import rutz.armazens.domain.Armazem;
import rutz.armazens.domain.Produto;

import java.time.LocalDateTime;
@Data
public class SaldoDTO {

    private Long id;

    private LocalDateTime data;

    @ManyToOne
    @JoinColumn(name = "armazem_id")
    private Armazem armazem;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    private int saldo;

}
