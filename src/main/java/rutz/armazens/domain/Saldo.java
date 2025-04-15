package rutz.armazens.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "SALDO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Saldo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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