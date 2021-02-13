package com.bookshelf.app.security;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Arrays;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.bookshelf.app.jwt.JwtConfigurations;
import com.bookshelf.app.jwt.JwtTokenVerifier;
import com.bookshelf.app.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import com.bookshelf.app.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BookShelfSecurityConfigurations extends WebSecurityConfigurerAdapter{
	
	private final PasswordEncoder passwordEncoder;
	private final UserService userService;
	private final SecretKey secretKey;
	private final JwtConfigurations jwtConfigurations;
	private final LogoutHandler logoutHandler;
	
	@Autowired
	public BookShelfSecurityConfigurations(PasswordEncoder passwordEncoder, UserService userService,
			SecretKey secretKey, JwtConfigurations jwtConfigurations, LogoutHandler logoutHandler) {
		super();
		this.passwordEncoder = passwordEncoder;
		this.userService = userService;
		this.secretKey = secretKey;
		this.jwtConfigurations = jwtConfigurations;
		this.logoutHandler = logoutHandler;
	}
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors(withDefaults())
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .logout()
                	.logoutUrl("/logout")
                	.addLogoutHandler(logoutHandler)
                	.logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus	.OK))
                	.permitAll()
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfigurations, secretKey))
                .addFilterAfter(new JwtTokenVerifier(secretKey, jwtConfigurations),JwtUsernameAndPasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .anyRequest()
                .authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }
    
    @Bean 
	CorsConfigurationSource corsConfigurationSource() { 
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowCredentials(true);
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		configuration.setAllowedMethods(Arrays.asList(CorsConfiguration.ALL));
		configuration.setAllowedHeaders(Arrays.asList(CorsConfiguration.ALL));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(); 
		source.registerCorsConfiguration("/**",configuration); 
		return source; 
	}
	
}
