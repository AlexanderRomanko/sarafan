package com.example.sarafan.controller;

import com.example.sarafan.entity.User;
import com.example.sarafan.repository.MessageRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class MainController {

    private final MessageRepository messageRepository;
    private final ClientRegistrationRepository clientRegistrationRepository;


    public MainController(MessageRepository messageRepository, ClientRegistrationRepository clientRegistrationRepository) {
        this.messageRepository = messageRepository;
        this.clientRegistrationRepository = clientRegistrationRepository;
    }

    @GetMapping
    public String main(@AuthenticationPrincipal OAuth2User oAuth2User, Model model) {
        HashMap<Object, Object> data = new HashMap<>();
//        ClientRegistration oktaRegistration =
//                this.clientRegistrationRepository.findByRegistrationId("okta");
        data.put("profile", oAuth2User);
        data.put("messages", messageRepository.findAll());
        model.addAttribute("frontendData", data);
        return "index";
    }
}
