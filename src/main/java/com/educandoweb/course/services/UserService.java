package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.OrderRepository;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

/*
 * A anotação @Component, faz com que a minha classe seja REGISTRADA para que possa
 * haver a Dependency Injection
 * 
 * Já a @Service, é mais específico pra o que eu preciso
 */
@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private OrderRepository orderRepository;

	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public User insert(User obj) {
		return repository.save(obj);
	}

	private void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public User update(Long id, User obj) {
		/*
		 * O método getReferenceById() não chega no banco de dados. Ele só prepara o
		 * objeto monitorado e depois eu faço alguma alteração no Banco de Dados
		 */
		try {
			User entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}


	public void updateOrDelete(Long id) {
		List<Order> orders = orderRepository.findAll();

		boolean existe = false;
		User user = null;

		for (Order o : orders) {
			if (o.getClient().getId() == id) {
				existe = true;
				user = o.getClient();
				break;
			}
		}

		if (existe) {
			user.setAtivo(false);
			update(id, user);
		} else {
			delete(id);
		}

	}
}
