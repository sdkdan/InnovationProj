package ru.innovat.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import ru.innovat.service.UserService;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    final
    UserService userService;
    final DataSource dataSource;

    public WebSecurityConfig(UserService userService, DataSource dataSource) {
        this.userService = userService;
        this.dataSource = dataSource;
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
////                .csrf()
////                .disable()
////                .authorizeRequests()
////                //Доступ только для не зарегистрированных пользователей
////                .antMatchers("/registration").not().fullyAuthenticated()
////                //Все остальные страницы требуют аутентификации
////                .anyRequest().authenticated()
////                .and()
////                //Настройка для входа в систему
////                .formLogin()
////                .loginPage("/login")
////                //Перенарпавление на главную страницу после успешного входа
////                .defaultSuccessUrl("/person")
////                .permitAll()
////                .and()
////                .logout()
////                .permitAll()
////                .logoutSuccessUrl("/");
        httpSecurity.csrf().disable();
        // The pages does not require login
        httpSecurity.authorizeRequests().antMatchers("/person", "/login", "/event", "/organization", "/project").permitAll();

        // /userInfo page requires login as ROLE_USER or ROLE_ADMIN.
        // If no login, it will redirect to /login page.
        httpSecurity.authorizeRequests().antMatchers("/userInfo").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");

        // For ADMIN only.
        httpSecurity.authorizeRequests().antMatchers("/admin").access("hasRole('ROLE_ADMIN')");

        // When the user has logged in as XX.
        // But access a page that requires role YY,
        // AccessDeniedException will be thrown.
        httpSecurity.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

        // Config for Login Form
        httpSecurity.authorizeRequests().and().formLogin()//
                // Submit URL of login page.
                .loginProcessingUrl("/j_spring_security_check") // Submit URL
                .loginPage("/login")//
                .defaultSuccessUrl("/person")//
                .failureUrl("/login?error=true")//
                .usernameParameter("username")//
                .passwordParameter("password")
                // Config for Logout Page
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");

        // Config Remember Me.
        httpSecurity.authorizeRequests().and() //
                .rememberMe().tokenRepository(this.persistentTokenRepository()) //
                .tokenValiditySeconds(1 * 24 * 60 * 60); // 24h
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource);
        return db;
    }
}