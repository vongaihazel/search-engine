import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-search',
  standalone: true,
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss'],
  imports: [CommonModule, FormsModule],
})
export class SearchComponent implements OnInit {
  query: string = '';
  userId: number = 1; // Set this to the actual user ID
  searchHistory: string[] = [];
  selectedHistory: string = '';

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.getSearchHistory();
  }

  search(): void {
    this.http.post(`http://localhost:8080/search/query`, { userId: this.userId, query: this.query })
      .subscribe(response => {
        console.log('Search successful', response);
        this.getSearchHistory(); // Refresh search history
      });
  }

  getSearchHistory(): void {
    this.http.get<string[]>(`http://localhost:8080/search/history/${this.userId}`)
      .subscribe(history => {
        this.searchHistory = history;
      });
  }

  useHistory(): void {
    this.query = this.selectedHistory; // Populate the query with the selected history item
  }
}
