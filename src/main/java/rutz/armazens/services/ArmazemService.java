package rutz.armazens.services;

import org.springframework.stereotype.Service;
import rutz.armazens.domain.Armazem;
import rutz.armazens.dtos.ArmazemDTO;
import rutz.armazens.dtos.ArmazemResponseDTO;
import rutz.armazens.repository.ArmazemRepository;

import java.util.List;

@Service
public class ArmazemService {

    private final ArmazemRepository repository;

    public ArmazemService(ArmazemRepository repository) {
        this.repository = repository;
    }

    public ArmazemResponseDTO criarArmazem(ArmazemDTO armazemDTO){
        Armazem armazem = new Armazem();
        armazem.setDesc(armazemDTO.getDesc());

        repository.save(armazem);
        return new ArmazemResponseDTO(armazem);
    }

    public List<Armazem> listarArmazem(){
        return repository.findAll();
    }
}
