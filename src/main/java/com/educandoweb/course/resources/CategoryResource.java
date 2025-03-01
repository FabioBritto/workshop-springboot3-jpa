package com.educandoweb.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.services.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/springboot/categories", produces = {"application/json"})
@Tag(name = "springboot")
public class CategoryResource {

	@Autowired
	private CategoryService service;
	
	@Operation(summary = "Realiza a busca de todas as Categorias", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Categorias carregadas com sucesso"),
			@ApiResponse(responseCode = "500", description = "Erro ao carregar Categorias")
	})
	@GetMapping
	public ResponseEntity<List<Category>> findAll(){
		List<Category> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@Operation(summary = "Realiza a busca de categoria por ID", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Categoria carregada com sucesso"),
			@ApiResponse(responseCode = "400", description = "ID inválido"),
			@ApiResponse(responseCode = "500", description = "Erro ao carregar Categoria")
	})
	@GetMapping(value = "/{id}")
	public ResponseEntity<Category> findById(@PathVariable Long id){
		Category obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

}
