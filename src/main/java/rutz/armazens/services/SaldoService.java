package rutz.armazens.services;

import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import rutz.armazens.domain.*;
import rutz.armazens.dtos.SaldoDTO;
import rutz.armazens.dtos.SaldoResponseDTO;
import rutz.armazens.dtos.SaldoTranfeDTO;
import rutz.armazens.dtos.SaldoUpdateDTO;
import rutz.armazens.repository.ArmazenRepository;
import rutz.armazens.repository.HistoricoRepository;
import rutz.armazens.repository.ProdutoRepository;
import rutz.armazens.repository.SaldoRepository;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SaldoService {

    private final SaldoRepository repository;
    private final ProdutoRepository produtoRepository;
    private final ArmazenRepository armazemRepository;
    private final HistoricoRepository historicoRepository;

    public SaldoService(SaldoRepository repository, ProdutoRepository produtoRepository, ArmazenRepository armazenRepository, HistoricoRepository historicoRepository) {
        this.repository = repository;
        this.produtoRepository = produtoRepository;
        this.armazemRepository = armazenRepository;
        this.historicoRepository = historicoRepository;
    }

    public SaldoResponseDTO criarSaldo(SaldoDTO saldoDTO) {
        if (saldoDTO.getSaldo() <= 0) {
            throw new IllegalArgumentException("O saldo deve ser maior que zero.");
        }




        Long produtoId = saldoDTO.getProduto().getId();
        Long armazemId = saldoDTO.getArmazem().getId();

        if (repository.existsByProdutoIdAndArmazemId(produtoId, armazemId)) {
            throw new IllegalArgumentException("Já existe um saldo para este produto neste armazém.");
        }

        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        Armazem armazem = armazemRepository.findById(armazemId)
                .orElseThrow(() -> new RuntimeException("Armazém não encontrado"));

        Saldo saldo = new Saldo();
        saldo.setProduto(produto);
        saldo.setArmazem(armazem);
        saldo.setSaldo(saldoDTO.getSaldo());
        saldo.setData(LocalDateTime.now());

        repository.save(saldo);

        Historico historico = new Historico();
        historico.setSaldo(saldo);
        historico.setTipo(Tipo.CRIACAO);
        historico.setProduto(produto);
        historico.setQuantidade(saldoDTO.getSaldo());
        historico.setArmazemOg(armazem);
        historico.setArmazemDt(null);

        historicoRepository.save(historico);
        return new SaldoResponseDTO(saldo);
    }

    public SaldoResponseDTO adicicionarSaldo(Long id, SaldoUpdateDTO saldoDTO) {
        Saldo saldo = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhum Saldo com esse id"));

        if (saldoDTO.saldo() <= 0) {
            throw new IllegalArgumentException("O saldo deve ser maior que zero.");
        }
        Historico historico = new Historico();
        historico.setProduto(saldo.getProduto());
        historico.setTipo(Tipo.ADICAO);
        historico.setArmazemOg(saldo.getArmazem());
        historico.setArmazemDt(null);
        historico.setQuantidade(saldoDTO.saldo());

        saldo.setSaldo(saldo.getSaldo() + saldoDTO.saldo());

        repository.save(saldo);
        historicoRepository.save(historico);

        return new SaldoResponseDTO(saldo);
    }

    public SaldoResponseDTO removerSaldo(Long id, SaldoUpdateDTO saldoDTO) {
        Saldo saldo = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhum saldo com esse id"));

        if (saldo.getSaldo() - saldoDTO.saldo() < 0) {
            throw new IllegalArgumentException("Quantidade a retirar maior que a quantidade existente");
        }


        Historico historico = new Historico();
        historico.setProduto(saldo.getProduto());
        historico.setTipo(Tipo.RETIRADA);
        historico.setArmazemOg(saldo.getArmazem());
        historico.setArmazemDt(null);
        historico.setQuantidade(saldoDTO.saldo());

        saldo.setSaldo(saldo.getSaldo() - saldoDTO.saldo());
        repository.save(saldo);
        historicoRepository.save(historico);

        return new SaldoResponseDTO(saldo);
    }
    public SaldoResponseDTO transferirSaldo(SaldoTranfeDTO saldoTranfeDTO) {
        Long produtoId = saldoTranfeDTO.produto().getId();
        Long armazemOr = saldoTranfeDTO.armazemOriginal().getId();
        Long armazemDs = saldoTranfeDTO.armazemDestino().getId();
        int quantidade = saldoTranfeDTO.quantidade();

        // Buscar saldo de origem
        Saldo saldoOrigem = repository.findByProdutoIdAndArmazemId(produtoId, armazemOr)
                .orElseThrow(() -> new RuntimeException("Saldo não encontrado para o armazém de origem."));

        // Verifica se tem saldo suficiente
        if (saldoOrigem.getSaldo() < quantidade) {
            throw new RuntimeException("Saldo insuficiente para a transferência.");
        }

        // Atualiza saldo do armazém de origem
        saldoOrigem.setSaldo(saldoOrigem.getSaldo() - quantidade);
        saldoOrigem.setData(LocalDateTime.now());
        repository.save(saldoOrigem);

        // Verifica se já existe saldo no armazém de destino
        Optional<Saldo> optionalSaldoDestino = repository.findByProdutoIdAndArmazemId(produtoId, armazemDs);
        Saldo saldoDestino;

        if (optionalSaldoDestino.isPresent()) {
            saldoDestino = optionalSaldoDestino.get();
            saldoDestino.setSaldo(saldoDestino.getSaldo() + quantidade);
            saldoDestino.setData(LocalDateTime.now());
        } else {
            saldoDestino = new Saldo();
            saldoDestino.setProduto(saldoTranfeDTO.produto());
            saldoDestino.setArmazem(saldoTranfeDTO.armazemDestino());
            saldoDestino.setSaldo(quantidade);
            saldoDestino.setData(LocalDateTime.now());
        }

        Historico historico = new Historico();
        historico.setProduto(saldoTranfeDTO.produto());
        historico.setTipo(Tipo.TRANSFERENCIA);
        historico.setArmazemOg(saldoTranfeDTO.armazemOriginal());
        historico.setArmazemDt(saldoTranfeDTO.armazemDestino());
        historico.setQuantidade(saldoTranfeDTO.quantidade());

        repository.save(saldoDestino);
        historicoRepository.save(historico);

        // Retorna a resposta com o saldo atualizado do destino
        return new SaldoResponseDTO(saldoDestino);
    }

}