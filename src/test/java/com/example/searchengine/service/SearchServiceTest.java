package com.example.searchengine.service;

import com.example.searchengine.model.SearchHistory;
import com.example.searchengine.model.User;
import com.example.searchengine.repository.SearchHistoryRepository;
import com.example.searchengine.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;


import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private SearchHistoryRepository searchHistoryRepository;

    @InjectMocks
    private SearchService searchService;

    @Test
    public void testInsertQuery() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");

        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));

        searchService.insertQuery(user.getId(), "example query");

        verify(searchHistoryRepository, times(1))
                .save(any(SearchHistory.class));
    }

    @Test
    public void testGetUserHistory() {
        // Implement a test for retrieving user history
    }
}
