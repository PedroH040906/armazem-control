package rutz.armazens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rutz.armazens.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
