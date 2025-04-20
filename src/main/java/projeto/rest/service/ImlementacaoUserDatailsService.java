package projeto.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import projeto.rest.model.UsuarioLogin;
import projeto.rest.repository.UsuarioRepository;

@Service
public class ImlementacaoUserDatailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository repository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsuarioLogin usuario = repository.findUserBylogin(username);
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuario não encontrado");
		}
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getAuthorities());
	}
}
