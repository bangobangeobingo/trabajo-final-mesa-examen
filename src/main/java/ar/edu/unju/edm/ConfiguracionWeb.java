package ar.edu.unju.edm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ar.edu.unju.edm.service.imp.LoginService;

@Configuration 
@EnableWebSecurity
public class ConfiguracionWeb extends WebSecurityConfigurerAdapter{
	@Autowired
	private Autenticacion autenticacion;
	

	String[] resources = new String[] { "/include/**", "/css/**", "/icons/**", "/img/**", "/js/**", "/layer/**",
			"/webjars/**" };

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
				.antMatchers(resources).permitAll()
				.antMatchers("/", "/index","/otroUsuario","/guardarusuario").permitAll()
				.antMatchers("/nuevoPeli","/guardarPeli","/listadoPeliculas","/editarUsuario/{id}","/editarUsuario","/eliminarUsuario/{id}").hasAuthority("ADMIN")
				.antMatchers("/formulario/pelicula","/guardarTicket","/listadoTickets").hasAuthority("CLIENTE")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.successHandler(autenticacion)
				.failureUrl("/login?error=true")
				.usernameParameter("correo")
				.passwordParameter("password")				
				.and()
			.logout()
				.permitAll()
				.logoutSuccessUrl("/login?logout");
	}

	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(4);
	}

	@Autowired
	LoginService userDetailsService;

	@Autowired
	public void configuracionGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		System.out.println("***Inicio del Usuario***");
		auth.userDetailsService(userDetailsService);
	}
}
