package kz.epam.intlab.security;


//@Configuration
//@EnableWebSecurity
public class AppSecurityConfig {

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }
//
//    @Autowired
//    @Qualifier("userDetailsServiceImpl")
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService);
//    }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/main").permitAll()
//                .antMatchers("/addUpNews", "/deleteNews", "/deleteSelectedNews", "/deleteComment")
//                .access("hasRole('ROLE_ADMIN')")
//                .antMatchers("/addComment").access("hasRole('ROLE_READER')")
//                .and()
//                .formLogin()
//                .loginPage("/openLoginPage").permitAll()
//                .usernameParameter("email")
//                .loginProcessingUrl("/openLogin")
//                .defaultSuccessUrl("/main")
//                .failureUrl("/openLoginPage?error")
//                .and()
//                .logout().logoutSuccessUrl("/main");
//    }
}
