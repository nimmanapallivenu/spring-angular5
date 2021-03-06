package com.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private static String REALM = "REST_REALM";

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("bill").password("abc123").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("tom").password("abc123").roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		super.configure(http);
		
		http.cors().and().csrf().disable().authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").denyAll().
		antMatchers("/","/index.html","/resources/**","/static/**").permitAll()
		.antMatchers("/**").hasRole("ADMIN").
		and().httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint()).and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
	}

	@Bean
	public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint() {
		return new CustomBasicAuthenticationEntryPoint();
	}

	@Override
    public void configure(WebSecurity web) throws Exception {
        // Filters will not get executed for the resources
        web.ignoring().antMatchers("/","/index.html", "/resources/**", "/static/**", "/public/**", "/webui/**", "/h2-console/**"
            , "/configuration/**", "/swagger-ui/**", "/swagger-resources/**", "/api-docs", "/api-docs/**", "/v2/api-docs/**"
            , "/*.html", "/**/*.html" ,"/**/*.css","/**/*.js","/**/*.png","/**/*.jpg", "/**/*.gif", "/**/*.svg", "/**/*.ico", "/**/*.ttf","/**/*.woff","/**/*.otf");
    }

	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowCredentials(true);
            }
        };
    }
	
	

}
