package com.patito.cdod.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Autowired
	private CorsConfigurationSource corsConfigurationSource;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
				.cors().configurationSource(corsConfigurationSource).and()  // Habilitar CORS en Spring Security y usar la configuración definida en WebConfig
				.csrf().disable()
				.authorizeRequests()
				.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()  // Permitir todas las solicitudes OPTIONS sin autenticación
				.antMatchers("/",
						"/favicon.ico",
						"/**/*.png",
						"/**/*.gif",
						"/**/*.svg",
						"/**/*.jpg",
						"/**/*.html",
						"/**/*.css",
						"/**/*.js",
						"/swagger-ui.html").permitAll()
				.antMatchers(HttpMethod.POST, "/api/auth/registrarse").permitAll()
				.antMatchers(HttpMethod.POST, "/api/auth/registrarMod").permitAll()
				.antMatchers(HttpMethod.GET, "/api/auth/confirm").permitAll()
				.antMatchers(HttpMethod.GET, "/api/auth/prueba").permitAll()
				.anyRequest().authenticated()
				.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.httpBasic();
	}


}
