package com.educandoweb.course.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/*
 * Quando eu quero que o meu objeto possa ser transformado em cadeias de bytes
 * e então poder trafegar na rede, ser gravado em arquivos, etc. preciso implementar Serializable
 */
@Entity
@Table(name = "tb_user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	/*
	 * Indica que o Persistence Provider precisa atribuir chave primária para a entidade usando uma
	 * coluna de identidade do Banco de Dados
	 */
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String password;
	
	
	//O JsonIgnore aqui não funciona. Só na classe ORDER
	
	@OneToMany(mappedBy = "client")
	private List<Order> orders = new ArrayList<>();
	
	//Por eu estar usando um framework, sou OBRIGADO a colocar um construtor vazio
	public User() {
	}
	
	public User(Long id, String name, String email, String phone, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@JsonIgnore
	public List<Order> getOrder() {
		return orders;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
