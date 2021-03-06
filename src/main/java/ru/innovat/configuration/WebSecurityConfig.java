package ru.innovat.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.innovat.service.authorization.UserService;


@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
        httpSecurity.authorizeRequests()
                    .antMatchers("/person", "/login", "/organization", "/project", "/event")
                    .permitAll()
                    .and().authorizeRequests()
                    .antMatchers("/event/**", "/person/**", "/organization/**", "/project/**", "/myprofile/**")
                    .access("hasAnyRole('USER', 'ADMIN','SUPPORT')")
                    .and().authorizeRequests().antMatchers("/support/**").access("hasAnyRole('SUPPORT')")
                    .and().authorizeRequests().antMatchers("/help/**").access("hasAnyRole('USER')")
                    .and().authorizeRequests().antMatchers("/admin/**").access("hasRole('ADMIN')")
                    .and().exceptionHandling()
                    .accessDeniedPage("/403")
                    .and().formLogin()
                    .loginProcessingUrl("/j_spring_security_check")
                    .loginPage("/login")
                    .defaultSuccessUrl("/person")
                    .failureUrl("/login?error=true")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .and().logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/logoutSuccessful");
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userService);
        return daoAuthenticationProvider;
    }
}