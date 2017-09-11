package org.big.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by WangTianshan on 2017/9/6.
 */
@EnableWebSecurity
@Configuration
public class Security extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().and()
                .headers()
                .frameOptions().sameOrigin()
                .xssProtection()
                .block(true)
                .and();

        //http.headers().xssProtection();

//        http
//                .authorizeRequests()
//                .antMatchers("/img/**", "/index").permitAll()
//                .antMatchers("/images/**", "/index").permitAll()
//                .antMatchers("/plugins/**", "/index").permitAll()
//                .antMatchers("/css/**", "/index").permitAll()
//                .antMatchers("/js/**", "/index").permitAll();
//                .antMatchers("/user/**").hasRole("USER")
//                .and()
//                .formLogin()
//                .loginPage("/login").failureUrl("/login-error");
    }

    @Autowired
    public void configureGlobal() throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER");
    }
}
