package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.formLogin().loginProcessingUrl("/login").permitAll().and().exceptionHandling().accessDeniedPage("/denied").and()
        .authorizeRequests().mvcMatchers("/accounts/resources/**").permitAll().mvcMatchers("/accounts/edit*")
        .hasRole("EDITOR").mvcMatchers("/accounts/account*").hasAnyRole("VIEWER", "EDITOR")
        .mvcMatchers("/accounts/**").authenticated().and().logout().permitAll().logoutSuccessUrl("/");
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication().passwordEncoder(new StandardPasswordEncoder()).withUser("user")
        .password("pass123").roles("VIEWER").and().withUser("editorUser").password("abc123").roles("EDITOR");
  }

}
