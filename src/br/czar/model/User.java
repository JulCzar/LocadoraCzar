package br.czar.model;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {
	private Integer id;
	@NotNull
	@NotBlank(message = "O nome não pode ficar vazio.")
	private String name;
	private String lastname;
	@NotBlank(message = "O email não pode ficar vazio.")
	private String email;
	private Privilege privilege;
	private LocalDate birthdate;
	private String cpf;
	@Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres.")
	private String password;
	
	public User() {
		this.privilege = Privilege.USER;
	}
	
	public User(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Privilege getPrivilege() {
		return privilege;
	}
	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}
	public LocalDate getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User = {\n\tid: " + id + ",\n\tname: " + name + ",\n\tlastname: " + lastname + ",\n\temail: " + email
				+ ",\n\tprivilege: " + privilege + ",\n\tbirthdate: " + birthdate + ",\n\tcpf: " + cpf
				+ ",\n\tpassword: " + password + "\n}";
	}
}
