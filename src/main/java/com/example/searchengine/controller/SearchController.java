package com.example.searchengine.controller;

import com.example.searchengine.model.SearchHistory;
import com.example.searchengine.service.SearchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchHistoryService searchHistoryService;

    @PostMapping("/query")
    public ResponseEntity<SearchHistory> saveQuery(@RequestParam Long userId, @RequestParam String query) {
        SearchHistory savedHistory = searchHistoryService.saveQuery(userId, query);
        return ResponseEntity.ok(savedHistory);
    }

    @GetMapping("/history/{userId}")
    public List<SearchHistory> getHistory(@PathVariable Long userId) {
        return searchHistoryService.getUserHistory(userId);
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