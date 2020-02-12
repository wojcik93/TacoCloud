package tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
/*				.antMatchers()
					.hasRole("ROLE_USER")
				.antMatchers("/", "/**").permitAll();	*/
				.antMatchers("/design", "/orders")
					.access("hasRole('ROLE_USER')")
				.antMatchers("/", "/**").access("permitAll")
			.and()
				.formLogin()
					.loginPage("/login")
			.and()
				.logout()
					.logoutSuccessUrl("/")
			.and()
				.csrf();
		
		/* Mozliwosc wejscia w design i orders tylko we wtorki
		 * .antMatchers("/design", "/orders") .access("hasRole('ROLE_USER') && " +
		 * "T(java.util.Calendar).getInstance().get(" +
		 * "T(java.util.Calendar).DAY_OF_WEEK) == " + "T(java.util.Calendar).TUESDAY")
		 * .antMatchers("/", "/**").access("permitAll");
		 */
	}
}
