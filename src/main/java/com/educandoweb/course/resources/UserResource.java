package com.educandoweb.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/springboot/users", produces = {"application/json"})
@Tag(name = "springboot")
public class UserResource {

	@Autowired
	private UserService service;
	
	@Operation(summary = "Realiza a busca de todos os Usuários", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Usuários carregados com sucesso"),
			@ApiResponse(responseCode = "500", description = "Erro ao carregar usuários")
	})
	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@Operation(summary = "Realiza a busca de Usuário por ID", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Usuário carregado com sucesso"),
			@ApiResponse(responseCode = "400", description = "ID inválido"),
			@ApiResponse(responseCode = "500", description = "Erro ao carregar Usuário")
	})
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id){
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@Operation(summary = "Realiza o cadastro de um novo Usuário", method = "POST")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Usuário cadastrado com sucesso"),
		@ApiResponse(responseCode = "400", description = "Dados do Usuário inválidos"),
		@ApiResponse(responseCode = "500", description = "Erro ao cadastrar Usuário")
	})
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@Operation(summary = "Deleta um Usuário a partir de um ID", method = "DELETE")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso"),
			@ApiResponse(responseCode = "400", description = "ID inválido"),
			@ApiResponse(responseCode = "500", description = "Erro ao deletar Usuário")
	})
	//Preciso fazer alguma alteração pra diferenciar o método do outro put
	@PutMapping(value = "/{id}/inactive")
	public ResponseEntity<Void> deleteOrSetInactive(@PathVariable Long id){
		service.updateOrDelete(id);
		return ResponseEntity.noContent().build();
	}
	
	@Operation(summary = "Atualiza um Usuário a partir de um ID", method = "PUT")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Dados de Usuário atualizado com sucesso"),
			@ApiResponse(responseCode = "400", description = "ID inválido"),
			@ApiResponse(responseCode = "500", description = "Erro ao atualizar dados de Usuário")
	})
	@PutMapping(value = "/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
