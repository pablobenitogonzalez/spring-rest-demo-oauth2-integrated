package org.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (uniqueConstraints = @UniqueConstraint (columnNames = { "login" }))
@SuppressWarnings("unused")
public class User extends Domain {

	private static Log logger = LogFactory.getLog(User.class);

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@NotEmpty
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@NotEmpty
	@Column(unique = true, nullable = false)
	private String login;
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}

	@NotEmpty
	private String password;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@NotNull
	@Valid
	@Embedded
	private Record record = new Record();
	public Record getRecord() {
		return record;
	}
	public void setRecord(Record record) {
		this.record = record;
	}

	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "granted_authority", joinColumns = { @JoinColumn(name = "user") }, inverseJoinColumns = { @JoinColumn(name = "role") })
	private Set<Role> roles = new HashSet<>();
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public User() {}

	public User(String name, String login, String password, Set<Role> roles) {
		logger.info("New object " + User.class);
		this.name = name;
		this.login = login;
		this.password = password;
		this.roles = roles;
	}

	public User(User user) {
		super();
		logger.info("New object " + User.class);
		this.id = user.getId();
		this.name = user.getName();
		this.login = user.getLogin();
		this.password = user.getPassword();
		this.roles = user.getRoles();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		return !(login != null ? !login.equals(user.login) : user.login != null);

	}

	@Override
	public int hashCode() {
		return login != null ? login.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", login='" + login + '\'' +
				", record=" + record +
				", roles=" + roles +
				'}';
	}

}
