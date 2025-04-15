package rutz.armazens.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rutz.armazens.domain.Saldo;
import rutz.armazens.domain.UM;

import java.util.List;

@Entity
@Table(name = "PRODUTO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao")  // ← SOLUÇÃO AQUI
    private String desc;

    @Enumerated(EnumType.STRING)
    private UM um;

    @OneToMany(mappedBy = "produto")
    @JsonIgnore
    private List<Saldo> saldos;
}