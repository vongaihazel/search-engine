package com.example.searchengine.controller;

import com.example.searchengine.model.User;
import com.example.searchengine.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class SearchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository; // Inject UserRepository to access the database

    @BeforeEach
    public void setUp() {
        // Create and save a test user
        User user = new User();
        user.setUsername("testUser");
        userRepository.save(user);// Save user to the in-memory database
        
    }

    @Test
    public void testSaveQuery() throws Exception {
        mockMvc.perform(post("/search/query")
                        .param("userId", "1")
                        .param("query", "test query"))
                .andExpect(status().isOk());
    }
}