package rutz.armazens.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "HISTORICO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Historico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @ManyToOne
    @JoinColumn(name = "saldo_id")
    private Saldo saldo;

    private int quantidade;

    @ManyToOne
    private Armazem armazemOg;

    @ManyToOne
    private Armazem armazemDt;

}