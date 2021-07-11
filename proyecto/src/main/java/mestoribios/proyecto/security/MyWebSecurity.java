package mestoribios.proyecto.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.CorsConfigurationSource;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import mestoribios.proyecto.security.jwt.JwtEntryPoint;
import mestoribios.proyecto.security.jwt.JwtTokenFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MyWebSecurity  extends WebSecurityConfigurerAdapter{
    
    private final static Logger logger = LoggerFactory.getLogger(MyWebSecurity.class);
    
    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    JwtEntryPoint jwtEntryPoint;

    @Bean
    JwtTokenFilter jwtTokenFilter(){
        return new JwtTokenFilter();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/oauth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }


//  @Bean
//  CorsConfigurationSource corsConfigurationSource() {
//      //CorsConfiguration configuration = new CorsConfiguration();
//     //  configuration.setAllowedOrigins(List.of("*"));
//     //  configuration.setAllowedMethods(List.of("GET","POST","PUT","DELETE"));
//     //  configuration.setAllowCredentials(true);
//     //  //the below three lines will add the relevant CORS response headers
//     //  configuration.addAllowedOrigin("*");
//     //  configuration.addAllowedHeader("*");
//     //  configuration.addAllowedMethod("*");
//     //  UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//     //  source.registerCorsConfiguration("/**", configuration);

//      CorsConfiguration configuration = new CorsConfiguration();
//      configuration.setAllowedOrigins(List.of("http://localhost:4200"));
//      configuration.setAllowedMethods(List.of("GET","POST","PUT","DELETE"));
//      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//      source.registerCorsConfiguration("/**", configuration);
//      //return source;
//      return source;
//  }

// @Bean
// CorsConfigurationSource corsConfigurationSource() {
//     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//     source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
//     return source;
// }



}
