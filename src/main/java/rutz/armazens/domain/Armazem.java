package rutz.armazens.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "ARMAZENS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Armazem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao")
    private String desc;

    @OneToMany(mappedBy = "armazem")
    @JsonIgnore
    private List<Saldo> saldos;
}