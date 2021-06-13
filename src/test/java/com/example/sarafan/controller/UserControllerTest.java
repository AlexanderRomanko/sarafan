package com.example.sarafan.controller;

import com.example.sarafan.entity.User;
import com.example.sarafan.repository.UserRepository;
import org.hamcrest.core.StringContains;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/before.sql"})
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    void create() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("username", "test");
        map.put("password", "test");
        RequestBuilder request = MockMvcRequestBuilders
                .post("/users")
                .content(String.valueOf(new JSONObject(map)))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string(StringContains.containsString("User has been created")))
                .andReturn();
    }

    @Test
    void show() throws Exception {
        User user = new User();
        user.setUsername("Max");
        user.setPassword("copra");
        user = userRepository.save(user);
        Map<String, String> map = new HashMap<>();
        map.put("username", user.getUsername());
        map.put("id", String.valueOf(user.getId()));
        RequestBuilder request = MockMvcRequestBuilders
                .get("/users/" + userRepository.findByUsername("Max").getUsername())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(StringContains.containsString(String.valueOf(new JSONObject(map)))))
                .andReturn();
    }

}