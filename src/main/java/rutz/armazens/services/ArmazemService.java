package rutz.armazens.services;

import org.springframework.stereotype.Service;
import rutz.armazens.domain.Armazem;
import rutz.armazens.dtos.ArmazemResponseDTO;
import rutz.armazens.dtos.ArmazenDTO;
import rutz.armazens.repository.ArmazenRepository;

import java.util.List;

@Service
public class ArmazemService {

    private final ArmazenRepository repository;

    public ArmazemService(ArmazenRepository repository) {
        this.repository = repository;
    }

    public ArmazemResponseDTO criarArmazem(ArmazenDTO armazenDTO){
        Armazem armazem = new Armazem();
        armazem.setDesc(armazenDTO.getDesc());

        repository.save(armazem);
        return new ArmazemResponseDTO(armazem);
    }

    public List<Armazem> listarArmazem(){
        return repository.findAll();
    }
}
