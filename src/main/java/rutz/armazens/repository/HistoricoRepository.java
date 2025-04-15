package rutz.armazens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rutz.armazens.domain.Historico;

@Repository
public interface HistoricoRepository extends JpaRepository<Historico, Long> {

}