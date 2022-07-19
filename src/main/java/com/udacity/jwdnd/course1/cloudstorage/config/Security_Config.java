package com.udacity.jwdnd.course1.cloudstorage.config;

import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//to override the default login


@Configuration
@EnableWebSecurity
public class Security_Config extends WebSecurityConfigurerAdapter {

    //we will take authentication service as a service
    private  final AuthenticationService authService;


    //need to add contructer parameter
    public Security_Config(AuthenticationService authService) {
        this.authService = authService;
    }


    //used to tell our spring we will using authService this as our login verification
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(this.authService);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //open for all
        http.authorizeRequests()
                .antMatchers("/signup", "/css/**", "/js/**").permitAll()
                .anyRequest().authenticated();


        //open only the one we signed up
        http.formLogin()
                .loginPage("/login")
                .permitAll();

        //verification suffcul peoople can see home page
        http.formLogin()
                .defaultSuccessUrl("/home", true);

        http.logout();
    }

}
