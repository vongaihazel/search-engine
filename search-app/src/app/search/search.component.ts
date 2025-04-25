import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

export interface User {
  id: number;
  username: string;
}

@Component({
  selector: 'app-search',
  standalone: true,
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss'],
  imports: [CommonModule, FormsModule], // Removed HttpClientModule
})
export class SearchComponent implements OnInit {
  query: string = '';
  userId: number = 1; // Set this to the actual user ID
  searchHistory: string[] = [];
  results: User[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    console.log('Component initialized');
    this.getSearchHistory();
  }

  search(): void {
    this.http.post(`http://localhost:8080/search/query`, { userId: this.userId, query: this.query })
      .subscribe({
        next: (response) => {
          console.log('Search successful', response);
          this.getSearchHistory(); // Refresh search history
        },
        error: (error) => {
          console.error('Error during search', error);
        }
      });
  }

  getSearchHistory(): void {
    this.http.get<string[]>(`http://localhost:8080/search/history/${this.userId}`)
      .subscribe({
        next: (history) => {
          this.searchHistory = history;
        },
        error: (error) => {
          console.error('Error fetching search history', error);
        }
      });
  }

  useHistory(historyItem: string): void {
    this.query = historyItem; // Populate the query with the selected history item
  }
}
