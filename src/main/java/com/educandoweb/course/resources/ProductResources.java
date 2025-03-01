package com.educandoweb.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.entities.Product;
import com.educandoweb.course.services.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/springboot/products", produces = {"application/json"})
@Tag(name = "springboot")
public class ProductResources {

	@Autowired
	private ProductService service;
	
	@Operation(summary = "Realiza a busca de todos os Produtos", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Produtos carregados com sucesso"),
			@ApiResponse(responseCode = "500", description = "Erro ao carregar Produtos")
	})
	@GetMapping
	public ResponseEntity<List<Product>> findAll(){
		List<Product> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@Operation(summary = "Realiza a busca de Produto por ID", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Produto carregado com sucesso"),
			@ApiResponse(responseCode = "400", description = "ID inválido"),
			@ApiResponse(responseCode = "500", description = "Erro ao carregar Produto")
	})
	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id) {
		Product obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
