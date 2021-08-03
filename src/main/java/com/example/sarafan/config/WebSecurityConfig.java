package com.example.sarafan.config;

import com.example.sarafan.entity.User;
import com.example.sarafan.repository.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.time.LocalDateTime;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig<PrincipalExtractor> extends WebSecurityConfigurerAdapter {

    private final UserRepository userRepository;

    public WebSecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .antMatcher("/**").authorizeRequests()
                .antMatchers("/", "/login**", "/js/**", "/error**").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout(l -> l.logoutSuccessUrl("/").permitAll())
                .csrf(AbstractHttpConfigurer::disable)
//                .csrf(c -> c.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .oauth2Login()
                .userInfoEndpoint().oidcUserService(this.oidcUserService());
    }

    private OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService() {
        return userRequest -> {
            OidcUser oidcUser = new OidcUserService().loadUser(userRequest);
            String googleId = (String) oidcUser.getAttributes().get("sub");
            User existingUser = userRepository.findById(googleId).orElseGet(() -> {
                User user = new User();
                user.setId(googleId);
                user.setName(oidcUser.getFullName());
                user.setUserpic(oidcUser.getPicture());
                user.setEmail(oidcUser.getEmail());
                user.setGender(oidcUser.getGender());
                user.setLocale(oidcUser.getLocale());
                return user;
            });
            existingUser.setLastVisit(LocalDateTime.now());
            userRepository.save(existingUser);
            return oidcUser;
        };
    }

}
