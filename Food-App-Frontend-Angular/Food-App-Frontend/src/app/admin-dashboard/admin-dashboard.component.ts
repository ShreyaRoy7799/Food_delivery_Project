import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent implements OnInit {

  stats: any = null;

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.loadStats();
  }

  loadStats() {
    this.http.get("http://localhost:8080/api/admin/dashboard")
      .subscribe(res => {
        this.stats = res;
      });
  }
}
