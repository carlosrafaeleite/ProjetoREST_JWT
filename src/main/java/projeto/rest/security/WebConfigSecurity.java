package projeto.rest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import projeto.rest.service.ImplementacaoUserDatailsService;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private ImplementacaoUserDatailsService datailsService;
	
	//configuras as solicitaçoes http
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		//serviço que ira consultar no banco
		auth.userDetailsService(datailsService)
		.passwordEncoder(new BCryptPasswordEncoder());//padrao de codificacao de senha
		
		super.configure(auth);
	}
}
