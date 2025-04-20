package projeto.rest.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import projeto.rest.model.UsuarioLogin;
import projeto.rest.repository.UsuarioRepository;

@RestController
@RequestMapping(value = "/api/inicio")
@Tag(name = "Cliente", description = "Consulta Cliente")
public class IndexController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping(value = "/{id}", produces = "application/json")
	@Operation(
	summary = "Consultando Cliente", 
	description = "Consulta Cliente", 
	tags = {"Cliente"},
	responses = {
			@ApiResponse(description= "Sucesso", responseCode = "200", 
					content = @Content(schema = @Schema(implementation = UsuarioLogin.class))
			),
			@ApiResponse(description= "Sem Content", responseCode = "204", content = @Content), 
			@ApiResponse(description= "Falhou", responseCode = "400", content = @Content), 
			@ApiResponse(description= "Não autorizado", responseCode = "401", content = @Content), 
			@ApiResponse(description= "Página nao encontrada", responseCode = "404", content = @Content), 
			@ApiResponse(description= "Servidor não encontrado", responseCode = "500", content = @Content) 

})
	public ResponseEntity<UsuarioLogin> inicio(@PathVariable(value = "id") Long id) {

		Optional<UsuarioLogin> usuarioLogin = usuarioRepository.findById(id);
		return new ResponseEntity<UsuarioLogin>(usuarioLogin.get(), HttpStatus.OK);

	}

	@GetMapping(value = "/{id}/relatorio", produces = "application/pdf")
	public ResponseEntity<UsuarioLogin> relatorio(@PathVariable(value = "id") Long id) {

		Optional<UsuarioLogin> usuarioLogin = usuarioRepository.findById(id);

		return new ResponseEntity<UsuarioLogin>(usuarioLogin.get(), HttpStatus.OK);

	}	

	@GetMapping(value = "/lista", produces = "application/json")
	@Operation(
	summary = "Listando Clientes", 
	description = "Lista Clientes", 
	tags = {"Cliente"},
	responses = {
			@ApiResponse(description= "Sucesso", responseCode = "200", 
					content = @Content(schema = @Schema(implementation = UsuarioLogin.class))
			),
			@ApiResponse(description= "Sem Content", responseCode = "204", content = @Content), 
			@ApiResponse(description= "Falhou", responseCode = "400", content = @Content), 
			@ApiResponse(description= "Não autorizado", responseCode = "401", content = @Content), 
			@ApiResponse(description= "Página nao encontrada", responseCode = "404", content = @Content), 
			@ApiResponse(description= "Servidor não encontrado", responseCode = "500", content = @Content) 

})
	public ResponseEntity<List<UsuarioLogin>> usuarioLogin() {

		List<UsuarioLogin> usuarioList = (List<UsuarioLogin>) usuarioRepository.findAll();
		return new ResponseEntity<List<UsuarioLogin>>(usuarioList, HttpStatus.OK);
	}
	
	@PostMapping(value = "/cadusuario", produces = "application/json")
	@Operation(
			summary = "cadastrando Clientes", 
			description = "cadastro Clientes", 
			tags = {"Cliente"},
			responses = {
					@ApiResponse(description= "Sucesso", responseCode = "200", 
							content = @Content(schema = @Schema(implementation = UsuarioLogin.class))
					),
					@ApiResponse(description= "Falhou", responseCode = "400", content = @Content), 
					@ApiResponse(description= "Não autorizado", responseCode = "401", content = @Content), 
					@ApiResponse(description= "Servidor não encontrado", responseCode = "500", content = @Content) 

		})
	public ResponseEntity<UsuarioLogin> cadusuario(@RequestBody UsuarioLogin usuarioLogin) {

		UsuarioLogin usuariosalvo = usuarioRepository.save(usuarioLogin);
		return new ResponseEntity<UsuarioLogin> (usuariosalvo, HttpStatus.OK);

	}
	
	@PutMapping(value = "/atualizarUser", produces = "application/json")
	@Operation(
			summary = "Atualizando Clientes", 
			description = "Atualizar Clientes", 
			tags = {"Cliente"},
			responses = {
					@ApiResponse(description= "Sucesso", responseCode = "200", 
							content = @Content(schema = @Schema(implementation = UsuarioLogin.class))
					),
					@ApiResponse(description= "Falhou", responseCode = "400", content = @Content), 
					@ApiResponse(description= "Não autorizado", responseCode = "401", content = @Content), 
					@ApiResponse(description= "Página nao encontrada", responseCode = "404", content = @Content), 
					@ApiResponse(description= "Servidor não encontrado", responseCode = "500", content = @Content) 

		})
	public ResponseEntity<UsuarioLogin> atualizarUser(@RequestBody UsuarioLogin usuarioLogin) {
		
		for (int i = 0; i < usuarioLogin.getTelefone().size(); i++) {
			usuarioLogin.getTelefone().get(i).setUsuarioLogin(usuarioLogin);
		}
		
		UsuarioLogin usuarioatualizado = usuarioRepository.save(usuarioLogin);

		return new ResponseEntity<UsuarioLogin>(usuarioatualizado, HttpStatus.OK);

	}	
	
	@DeleteMapping(value = "/{id}", produces = "application/text")
	@Operation(
			summary = "Excluindo Clientes", 
			description = "Excluir Clientes", 
			tags = {"Cliente"},
			responses = {
					@ApiResponse(description= "Sucesso", responseCode = "200", 
							content = @Content(schema = @Schema(implementation = UsuarioLogin.class))
					),
					@ApiResponse(description= "Sem Content", responseCode = "204", content = @Content), 
					@ApiResponse(description= "Falhou", responseCode = "400", content = @Content), 
					@ApiResponse(description= "Não autorizado", responseCode = "401", content = @Content), 
					@ApiResponse(description= "Página nao encontrada", responseCode = "404", content = @Content), 
					@ApiResponse(description= "Servidor não encontrado", responseCode = "500", content = @Content) 

		})
	public String deletarUser(@PathVariable(value = "id") Long id) {

		usuarioRepository.deleteById(id);
		return  "ok";

	}

}
