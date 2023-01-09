package vitrine.marca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import vitrine.marca.models.RoleModel;

public interface RoleRepository extends JpaRepository<RoleModel, Long> {

	RoleModel findByRoleName(String roleName);

	Iterable<RoleModel> findAllByOrderByRoleNameAsc();

}
