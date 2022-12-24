package vitrine.marca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import vitrine.marca.models.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Long> {

}
