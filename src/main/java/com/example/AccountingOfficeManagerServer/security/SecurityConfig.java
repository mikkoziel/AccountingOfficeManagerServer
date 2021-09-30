package com.example.AccountingOfficeManagerServer.security;

import com.example.AccountingOfficeManagerServer.entity.configuration.RoleEnum;
import com.example.AccountingOfficeManagerServer.repository.UserRepository;
import com.example.AccountingOfficeManagerServer.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.http.HttpServletResponse;

import static java.lang.String.format;

@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    private final UserRepository userRepo;
    private final JwtTokenFilter jwtTokenFilter;
    private final UserService userService;

    public SecurityConfig(UserRepository userRepo,
                          JwtTokenFilter jwtTokenFilter,
                          UserService userService) {
        super();
//        this.userRepo = userRepo;
        this.jwtTokenFilter = jwtTokenFilter;
        this.userService = userService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username -> {
            try {
                return userService.loadUserByUsername(username);
            } catch(UsernameNotFoundException e) {
                throw new UsernameNotFoundException(format("User: %s, not found", username));
            }
        });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Enable CORS and disable CSRF
        http = http.cors().and().csrf().disable();

        // Set session management to stateless
        http = http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        // Set unauthorized requests exception handler
        http = http
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, ex) -> {
                            response.sendError(
                                    HttpServletResponse.SC_UNAUTHORIZED,
                                    ex.getMessage()
                            );
                        }
                )
                .and();

        // Permissions on endpoints
        http.authorizeRequests()
                // Public endpoints
                .antMatchers("/auth/**").permitAll()
                // Private endpoints
//                .antMatchers("/ao/").hasAnyRole(
//                        RoleEnum.AO_ADMIN.toString())
//                .antMatchers("/calendar/").hasAnyRole(
//                        RoleEnum.ADMIN.toString(),
//                        RoleEnum.AO_ADMIN.toString(),
//                        RoleEnum.USER.toString(),
//                        RoleEnum.CLIENT.toString())
//                .antMatchers("/cc/register").hasAnyRole(
//                        RoleEnum.ADMIN.toString(),
//                        RoleEnum.AO_ADMIN.toString())
//                .antMatchers("/cc/").hasAnyRole(
//                        RoleEnum.ADMIN.toString(),
//                        RoleEnum.AO_ADMIN.toString(),
//                        RoleEnum.USER.toString(),
//                        RoleEnum.CLIENT.toString())
//                .antMatchers("/client/").hasAnyRole(
//                        RoleEnum.ADMIN.toString(),
//                        RoleEnum.AO_ADMIN.toString(),
//                        RoleEnum.USER.toString())
//                .antMatchers("/company/").hasAnyRole(
//                        RoleEnum.ADMIN.toString(),
//                        RoleEnum.AO_ADMIN.toString(),
//                        RoleEnum.USER.toString(),
//                        RoleEnum.CLIENT.toString())
//                .antMatchers("/documents/").hasAnyRole(
//                        RoleEnum.ADMIN.toString(),
//                        RoleEnum.AO_ADMIN.toString(),
//                        RoleEnum.USER.toString(),
//                        RoleEnum.CLIENT.toString())
//                .antMatchers("/employee/").hasAnyRole(
//                        RoleEnum.ADMIN.toString(),
//                        RoleEnum.AO_ADMIN.toString(),
//                        RoleEnum.USER.toString(),
//                        RoleEnum.CLIENT.toString())
//                .antMatchers("/user/").hasAnyRole(
//                        RoleEnum.ADMIN.toString(),
//                        RoleEnum.AO_ADMIN.toString(),
//                        RoleEnum.USER.toString(),
//                        RoleEnum.CLIENT.toString())
//                .antMatchers("/work-log/").hasAnyRole(
//                        RoleEnum.ADMIN.toString(),
//                        RoleEnum.AO_ADMIN.toString(),
//                        RoleEnum.USER.toString())
                .anyRequest().authenticated();

        // Add JWT token filter
        http.addFilterBefore(
                jwtTokenFilter,
                UsernamePasswordAuthenticationFilter.class
        );
    }

    // Used by spring security if CORS is enabled.
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


}