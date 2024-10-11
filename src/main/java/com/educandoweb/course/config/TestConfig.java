package com.educandoweb.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.Product;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.entities.enums.OrderStatus;
import com.educandoweb.course.repositories.CategoryRepository;
import com.educandoweb.course.repositories.OrderRepository;
import com.educandoweb.course.repositories.ProductRepository;
import com.educandoweb.course.repositories.UserRepository;

/*
 * Pra eu definir para o SPRING que está é uma classe de CONFIGURAÇÃO, preciso
 * usar uma ANNOTATION
 * Pra falar que esta é uma classe específica pra configuração de teste, preciso
 * colocar a segunda ANNOTATION. No parâmetro, eu coloco EXATAMENTE o que está em
 * <application.properties>
 */

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	//Usarei a classe pra popular o banco de dados
	
	/*
	 * O framework, tem um mecanismo de injeção de dependência
	 * O @Autowired faz com que o SPRING lide com esta dependência
	 */
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;

	//Tudo o que houver dentro deste método será executado na inicalização da aplicação
	@Override
	public void run(String... args) throws Exception {
		
		//No caso de Category, não tem problema ficar antes, pois não tem dependências com User e Order
		Category cat1 = new Category(null, "Eletronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");
		
		categoryRepository.saveAll(Arrays.asList(cat1,cat2,cat3));
		
		User u1 = new User(null,"João Pé de Feijão", "joao@feijao","12312312","1234");
		User u2 = new User(null,"Maria Mão de Latrina", "maria@latrina","3480234","123445");
		
		Order o1 = new Order(null, Instant.parse("2024-09-30T21:01:23Z"), OrderStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2024-10-30T21:01:23Z"), OrderStatus.WAITING_PAYMENT, u2);
		Order o3 = new Order(null, Instant.parse("2024-11-30T21:01:23Z"), OrderStatus.WAITING_PAYMENT, u1);
		
		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
		
		productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
		/*
		 * Como a minha interface UserRepository extends JpaRepository, eu tenho, por meio dela,
		 * acesso ao método "saveAll". Como parâmetro, eu crio uma lista que contém os dois objetos criados
		 */
		userRepository.saveAll(Arrays.asList(u1,u2));
		orderRepository.saveAll(Arrays.asList(o1,o2,o3));
		
	}
}
