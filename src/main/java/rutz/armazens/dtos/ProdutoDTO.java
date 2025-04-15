package rutz.armazens.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rutz.armazens.domain.UM;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProdutoDTO {

    private Long id;

    private String desc;

    private UM Um;
}
