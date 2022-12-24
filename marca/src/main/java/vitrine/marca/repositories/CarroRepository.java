package vitrine.marca.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import vitrine.marca.models.Carro;
import vitrine.marca.models.Marca;

public interface CarroRepository extends JpaRepository<Carro, Long> {
	List<Carro> findByMarca(Marca marca);
}
