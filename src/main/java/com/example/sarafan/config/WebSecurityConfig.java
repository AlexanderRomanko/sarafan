package com.example.sarafan.config;

import com.example.sarafan.entity.User;
import com.example.sarafan.repository.UserRepository;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserRepository userRepository;

    public WebSecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .antMatcher("/**").authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .userInfoEndpoint().oidcUserService(this.oidcUserService());
//                .authorizeRequests()
//                .mvcMatchers("/").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .csrf().disable();
    }


    private OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService() {
        return userRequest -> {
            OidcUser oidcUser = new OidcUserService().loadUser(userRequest);
            String googleId = (String) oidcUser.getAttributes().get("sub");
            User user = new User();
            user.setId(googleId);
            user.setName(oidcUser.getFullName());
            user.setUserpic(oidcUser.getPicture());
            user.setEmail(oidcUser.getEmail());
            user.setGender(oidcUser.getGender());
            user.setLocale(oidcUser.getLocale());
            user.setLastVisit(LocalDateTime.now());
            userRepository.save(user);
            return oidcUser;
        };
    }

}
