import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-order-success',
  templateUrl: './order-success.component.html',
  styleUrls: ['./order-success.component.css']
})
export class OrderSuccessComponent implements OnInit {

  order: any = null;
  orderId: any;

  constructor(
    private route: ActivatedRoute,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    this.orderId = this.route.snapshot.paramMap.get('id');

    this.http.get(`http://localhost:8080/api/orders/${this.orderId}`)
      .subscribe({
        next: (res) => {
          this.order = res;
        },
        error: () => {
          alert("Could not load order details");
        }
      });
  }
}
