package ru.innovat.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import ru.innovat.service.authorization.UserService;

import javax.sql.DataSource;


@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    final UserService userService;
    final DataSource dataSource;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
        httpSecurity.authorizeRequests()
                .antMatchers("/person", "/login", "/organization", "/project",
                "/event")
                .permitAll()
                .and()
                .authorizeRequests().antMatchers("/event/**", "/person/**", "/organization/**",
                "/project/**", "/myprofile/**").access("hasAnyRole('USER', 'ADMIN','SUPPORT')")
                .and().authorizeRequests().antMatchers("/support/**").access("hasAnyRole('SUPPORT')")
                .and().authorizeRequests().antMatchers("/help/**").access("hasAnyRole('USER')")
                .and().authorizeRequests().antMatchers("/admin/**").access("hasRole('ADMIN')")
                .and().authorizeRequests()
                .and().exceptionHandling()
                .accessDeniedPage("/403")
                .and().authorizeRequests()
                .and().formLogin()
                .loginProcessingUrl("/j_spring_security_check")
                .loginPage("/login")
                .defaultSuccessUrl("/person")
                .failureUrl("/login?error=true")
                .usernameParameter("username")
                .passwordParameter("password")
                .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/logoutSuccessful")
                .and().authorizeRequests();
    }

//    @Autowired
//    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
//    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {//второй вариант настройки
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userService);
        return daoAuthenticationProvider;
    }
}
