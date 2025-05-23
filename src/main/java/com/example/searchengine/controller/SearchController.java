package com.example.searchengine.controller;

import com.example.searchengine.model.SearchHistory;
import com.example.searchengine.model.SearchRequest;
import com.example.searchengine.service.SearchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchHistoryService searchHistoryService;

    @PostMapping("/query")
    public ResponseEntity<SearchHistory> saveQuery(@RequestBody SearchRequest searchRequest) {
        SearchHistory savedHistory = searchHistoryService.saveQuery(searchRequest.getUserId(), searchRequest.getQuery());
        return ResponseEntity.ok(savedHistory);
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<String>> getHistory(@PathVariable Long userId) {
        List<SearchHistory> userHistory = searchHistoryService.getUserHistory(userId);

        if (userHistory != null && !userHistory.isEmpty()) {
            // Extracting the queries from the SearchHistory objects
            List<String> simplifiedHistory = userHistory.stream()
                    .map(SearchHistory::getQuery) // Ensure this method exists in SearchHistory
                    .toList(); // Collecting into a List<String>

            return ResponseEntity.ok(simplifiedHistory);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
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