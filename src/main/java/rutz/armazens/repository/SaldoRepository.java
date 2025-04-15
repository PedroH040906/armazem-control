package rutz.armazens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rutz.armazens.domain.Saldo;

import java.util.Optional;

public interface SaldoRepository extends JpaRepository<Saldo, Long> {
    boolean existsByProdutoIdAndArmazemId(Long produtoId, Long armazemId);

    Optional<Saldo> findByProdutoIdAndArmazemId(Long produtoId, Long armazemId);
}
