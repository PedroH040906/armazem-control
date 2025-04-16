package rutz.armazens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rutz.armazens.domain.Armazem;

public interface ArmazemRepository extends JpaRepository<Armazem, Long> {
}
