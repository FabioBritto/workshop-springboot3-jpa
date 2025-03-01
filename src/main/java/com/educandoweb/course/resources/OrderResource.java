package com.educandoweb.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.services.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/springboot/orders", produces = {"application/json"})
@Tag(name = "springboot")
public class OrderResource {

	@Autowired
	private OrderService service;
	
	@Operation(summary = "Realiza a busca de todos os Pedidos", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Pedidos carregados com sucesso"),
			@ApiResponse(responseCode = "500", description = "Erro ao carregar Pedidos")
	})
	@GetMapping
	public ResponseEntity<List<Order>> findAll(){
		List<Order> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@Operation(summary = "Realiza a busca de pedido por ID", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Pedido carregado com sucesso"),
			@ApiResponse(responseCode = "400", description = "ID inválido"),
			@ApiResponse(responseCode = "500", description = "Erro ao carregar Pedido")
	})
	@GetMapping(value = "/{id}")
	public ResponseEntity<Order> findById(@PathVariable Long id){
		Order obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}

