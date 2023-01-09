package vitrine.marca.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_USER")
public class UserModel implements UserDetails, Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 40)
	private Long userId;
	@Column(nullable = false, unique = true)
	private String username;
	@Column(nullable = false, unique = true)
	private String cnpj;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false)
	private String password;

	@ManyToMany
	@JoinTable(name = "TB_USERS_ROLES", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<RoleModel> roles;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.roles;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public List<RoleModel> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleModel> roles) {
		this.roles = roles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserModel [userId=" + userId + ", username=" + username + ", cnpj=" + cnpj + ", email=" + email
				+ ", password=" + password + ", roles=" + roles + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cnpj, email, password, roles, userId, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserModel other = (UserModel) obj;
		return Objects.equals(cnpj, other.cnpj) && Objects.equals(email, other.email)
				&& Objects.equals(password, other.password) && Objects.equals(roles, other.roles)
				&& Objects.equals(userId, other.userId) && Objects.equals(username, other.username);
	}

}
