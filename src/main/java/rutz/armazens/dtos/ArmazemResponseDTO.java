package rutz.armazens.dtos;

import rutz.armazens.domain.Armazem;

public record ArmazemResponseDTO(String desc, Long id) {

    public ArmazemResponseDTO(Armazem armazem) {
        this(armazem.getDesc(), armazem.getId());
    }
}