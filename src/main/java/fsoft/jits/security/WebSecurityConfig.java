package fsoft.jits.security;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserDetailsService userDetailsService;
    
    @Autowired
    private AuthenticationSuccessHandlerImpl authSuccessHandlerImpl;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	// Entry points
    	http.authorizeRequests()
    	.antMatchers("/").permitAll()
    	.antMatchers("/welcome").permitAll()
    	.antMatchers("/register").permitAll()
    	.antMatchers("/process_register").permitAll()
    	.antMatchers("/verify").permitAll()
    	.antMatchers("/process_changePass").permitAll()
    	.antMatchers("/login").permitAll()
    	.antMatchers("/change_pass").permitAll()
    	
    	.anyRequest()
	  		.authenticated()
	  		.and()
	  		
  		.formLogin()
	  		.loginProcessingUrl("/process_login") // submit URL
	  		.loginPage("/login")
		  	//.failureUrl("/login?error=true")
		  	.permitAll()
		  	//.successHandler(authSuccessHandlerImpl)
		  	.defaultSuccessUrl("/")
			.usernameParameter("username")//
			.passwordParameter("password")
		  	.and()
		  	
	  	.logout()
		    .logoutUrl("/perform_logout")
		    .logoutSuccessUrl("/login")
		//		.invalidateHttpSession(true)
		//		.deleteCookies("JSESSIONID")
		    .permitAll();
    	
	  // If a user try to access a resource without having enough permissions
	  	http.exceptionHandling().accessDeniedPage("/not_permission");
    }
    
    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    
    @Bean
	public PasswordEncoder passwordEncoder() { // userserviceimple autowired from here
		return new BCryptPasswordEncoder(10);
	}

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }
}
