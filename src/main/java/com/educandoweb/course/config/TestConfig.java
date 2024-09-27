package com.educandoweb.course.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.course.entities.User;
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

	@Override
	public void run(String... args) throws Exception {
		//Tudo o que houver dentro deste método será executado na inicalização da aplicação
		
		User u1 = new User(null,"João Pé de Feijão", "joao@feijao","12312312","1234");
		User u2 = new User(null,"Maria Mão de Latrina", "maria@latrina","3480234","123445");
		
		/*
		 * Como a minha interface UserRepository extends JpaRepository, eu tenho, por meio dela,
		 * acesso ao método "saveAll". Como parâmetro, eu crio uma lista que contém os dois objetos criados
		 */
		userRepository.saveAll(Arrays.asList(u1,u2));
	}
}