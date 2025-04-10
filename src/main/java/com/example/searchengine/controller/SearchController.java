package com.example.searchengine.controller;

import com.example.searchengine.model.User;
import com.example.searchengine.model.SearchHistory;
import com.example.searchengine.repository.UserRepository;
import com.example.searchengine.repository.SearchHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SearchHistoryRepository searchHistoryRepository;

    @PostMapping("/query")
    public ResponseEntity<SearchHistory> saveQuery(@RequestParam Long userId, @RequestParam String query) {
        System.out.println("Received request with userId: " + userId + " and query: " + query); // Logging

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        SearchHistory history = new SearchHistory();
        history.setUser(user);
        history.setQuery(query);
        history.setTimestamp(LocalDateTime.now());

        SearchHistory savedHistory = searchHistoryRepository.save(history);
        return ResponseEntity.ok(savedHistory); // Return saved history
    }

    @GetMapping("/history/{userId}")
    public List<SearchHistory> getHistory(@PathVariable Long userId) {
        return searchHistoryRepository.findByUserId(userId);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("API is working!");
    }
    @GetMapping("/")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Welcome to the Search Engine API!");
    }
}