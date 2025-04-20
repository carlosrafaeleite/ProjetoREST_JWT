package projeto.rest.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import io.swagger.v3.oas.annotations.Hidden;
import projeto.rest.model.UsuarioLogin;

@Repository
@RepositoryRestResource(exported = false)
@Hidden
public interface UsuarioRepository extends CrudRepository<UsuarioLogin, Long> {
	
	@Query("select u from UsuarioLogin where u.loginUser = ?1" )
	UsuarioLogin findUserBylogin(String login);

}
