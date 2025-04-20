package projeto.rest.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.UniqueConstraint;

@Entity
@Hidden
public class UsuarioLogin implements UserDetails{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idLogin;
	private String loginUser;
	private String loginsenha;
	
	@OneToMany(mappedBy = "usuarioLogin", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<TelefoneUsuario> telefone = new ArrayList<TelefoneUsuario>();
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(
		    name = "usuario_role",
		    uniqueConstraints = @UniqueConstraint(
		        columnNames = {"user_id", "role_id"},
		        name = "unique_user_role"
		    ),
		    joinColumns = @JoinColumn(
		        name = "user_id",
		        referencedColumnName = "id",
		        table = "usuario_login",
		        foreignKey = @ForeignKey(name = "usuario_fk")
		    ),
		    inverseJoinColumns = @JoinColumn(
		        name = "role_id",
		        referencedColumnName = "id",
		        updatable = false,
		        table = "role",
		        foreignKey = @ForeignKey(name = "role_fk", value = ConstraintMode.CONSTRAINT)
		    )
		)
	private List<Role> roleList;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		return loginsenha;
	}

	@Override
	public String getUsername() {
		return loginUser;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	//gets and setters
	public Long getIdLogin() {
		return idLogin;
	}
	public void setIdLogin(Long idLogin) {
		this.idLogin = idLogin;
	}
	public String getLoginUser() {
		return loginUser;
	}
	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}
	public String getLoginsenha() {
		return loginsenha;
	}
	public void setLoginsenha(String loginsenha) {
		this.loginsenha = loginsenha;
	}
		
	public List<TelefoneUsuario> getTelefone() {
		return telefone;
	}
	public void setTelefone(List<TelefoneUsuario> telefone) {
		this.telefone = telefone;
	}
	@Override
	public int hashCode() {
		return Objects.hash(idLogin, loginUser, loginsenha, telefone);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioLogin other = (UsuarioLogin) obj;
		return Objects.equals(idLogin, other.idLogin) && Objects.equals(loginUser, other.loginUser)
				&& Objects.equals(loginsenha, other.loginsenha) && Objects.equals(telefone, other.telefone);
	}

	

}
