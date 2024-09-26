package com.estoutic.conflict_backend.configs;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;

import java.io.IOException;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
class SecurityConfiguration {

    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();
        LogoutSuccessHandler logoutSuccessHandler = new LogoutSuccessHandler() {
            @Override
            public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                request.getSession().invalidate();

                if (authentication != null && authentication.getName() != null) {
                    System.out.println("User " + authentication.getName() + " has logged out.");
                }
            }
        };
        //csrf & cors
        http.csrf(AbstractHttpConfigurer::disable).cors(Customizer.withDefaults());


        //http request
        http.authorizeHttpRequests(
                (request) -> {
                    request.requestMatchers("/auth/login", "/auth/register").permitAll();
                    request.requestMatchers("/**").fullyAuthenticated();
                    request.requestMatchers("/public/**").permitAll();
                    request.anyRequest().permitAll();
                }
        );

        //storing the session
        http.securityContext((context) -> context.securityContextRepository(securityContextRepository));

        //session management
        http.sessionManagement((session) -> {
            session.maximumSessions(1).maxSessionsPreventsLogin(true);
            session.sessionFixation(SessionManagementConfigurer.SessionFixationConfigurer::newSession);
            session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
        });

        //clear cookie when logout
        http.logout((logout) -> {
            logout.logoutUrl("/api/auth/logout");
            logout.addLogoutHandler(
                    new HeaderWriterLogoutHandler(
                            new ClearSiteDataHeaderWriter(ClearSiteDataHeaderWriter.Directive.COOKIES)
                    )
            );
            logout.deleteCookies("JSESSIONID");
            logout.logoutSuccessHandler(logoutSuccessHandler);
        });

        //auth provider for connect DAO
        http.authenticationProvider(authenticationProvider);

        return http.build();
    }

}