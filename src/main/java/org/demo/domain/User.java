package org.demo.domain;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (uniqueConstraints = @UniqueConstraint (columnNames = { "email" }))
@SuppressWarnings("unused")
public class User extends Domain {

    private static Log logger = LogFactory.getLog(User.class);

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

	private String email;
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = (email == null)? null : email.toUpperCase();
    }

    private String password;
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

	private Integer fails = 0;
    public Integer getFails() {
        return this.fails;
    }
    public void setFails(Integer fails) {
        this.fails = fails;
    }

	private boolean enabled = true;
    public Boolean getEnabled() {
        return this.enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

	@Enumerated(EnumType.STRING)
	private Role role;
    public Role getRole() {
        return this.role;
    }
    public void setRol(Role role) {
        this.role = role;
    }
    public Set<Role> getRoles() {
        Set<Role> roles = new HashSet<>();
        roles.add(this.getRole());
        return roles;
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

	public User() {}

    public User(String email, String password, Role role) {
        logger.info("New object " + User.class);
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(User user) {
        super();
        logger.info("New object " + User.class);
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.role = user.getRole();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return !(email != null ? !email.equals(user.email) : user.email != null);

    }

    @Override
    public int hashCode() {
        return email != null ? email.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fails=" + fails +
                ", enabled=" + enabled +
                ", role=" + role +
                ", record=" + record +
                '}';
    }
}
