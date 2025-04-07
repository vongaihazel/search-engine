package com.example.searchengine.service;

import com.example.searchengine.model.SearchHistory;
import com.example.searchengine.model.User;
import com.example.searchengine.repository.SearchHistoryRepository;
import com.example.searchengine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SearchHistoryRepository searchHistoryRepository;

    public void insertQuery(Long userId, String query) {
        User user = userRepository.findById(userId).orElseThrow();
        SearchHistory history = new SearchHistory();
        history.setUser(user);
        history.setQuery(query);
        searchHistoryRepository.save(history);
    }

    public List<SearchHistory> getUserHistory(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return searchHistoryRepository.findByUser(user);
    }
}
