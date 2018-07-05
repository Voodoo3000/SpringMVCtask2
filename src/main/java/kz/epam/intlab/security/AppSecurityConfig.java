package kz.epam.intlab.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/main").permitAll()
                .antMatchers("/addUpNews", "/deleteNews", "/deleteSelectedNews", "/deleteComment")
                .access("hasRole('ROLE_ADMIN')")
                .antMatchers("/addComment").access("hasRole('ROLE_READER')")
                .and()
                .formLogin()
                .loginPage("/openLoginPage").permitAll()
                .usernameParameter("email")
                .loginProcessingUrl("/openLogin")
                .defaultSuccessUrl("/main")
                .failureUrl("/openLoginPage?error")
                .and()
                .logout().logoutSuccessUrl("/main");
    }
}
