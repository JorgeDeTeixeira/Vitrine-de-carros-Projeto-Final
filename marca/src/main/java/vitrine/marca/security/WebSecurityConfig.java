package vitrine.marca.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.httpBasic().and().authorizeHttpRequests()
		.requestMatchers("/vitrine").permitAll()
		.requestMatchers("/vitrine/marcas").hasAnyRole("ADMIN", "EMPRESA")
		.requestMatchers("/vitrine/marca/form").hasAnyRole("ADMIN", "EMPRESA")
		.and().formLogin()
				.loginPage("/User/login-cadastro").permitAll().usernameParameter("username")
				.passwordParameter("password").defaultSuccessUrl("/vitrine").and().logout().permitAll().and()
				.exceptionHandling().accessDeniedPage("/User/login-cadastro").and().csrf().disable();

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
