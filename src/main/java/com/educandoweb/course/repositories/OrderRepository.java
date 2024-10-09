package com.educandoweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.entities.Order;


public interface OrderRepository extends JpaRepository<Order, Long>{

	/*
	 * Nesse caso, não precisarei implementar esta interface, porque o SPRING DATA JPA
	 * já tem uma implementação padrão pra ela quando eu especifico a minha entidade e o tipo do ID
	 * da entidade
	 */
}
