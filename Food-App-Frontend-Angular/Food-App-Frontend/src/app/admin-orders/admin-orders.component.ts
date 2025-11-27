import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ExportService } from '../export.service';

@Component({
  selector: 'app-admin-orders',
  templateUrl: './admin-orders.component.html',
  styleUrls: ['./admin-orders.component.css']
})
export class AdminOrdersComponent implements OnInit {

  orders: any[] = [];
  statuses = ["PLACED", "CONFIRMED", "OUT_FOR_DELIVERY", "DELIVERED"];

  constructor(
    private http: HttpClient,
    private exportService: ExportService
  ) {}

  ngOnInit(): void {
    this.loadOrders();
  }

  // Load all orders for admin
  loadOrders() {
    this.http.get<any[]>("http://localhost:8080/api/orders")
      .subscribe(res => {
        this.orders = res;
      });
  }

  // Safe event value reader
  getSelectValue(event: Event): string {
    return (event.target as HTMLSelectElement).value;
  }

  // Update order status
  updateStatus(orderId: number, newStatus: string) {
    const payload = { status: newStatus };

    this.http.patch(`http://localhost:8080/api/orders/${orderId}/status`, payload)
      .subscribe({
        next: () => {
          alert("Status updated!");
          this.loadOrders();
        },
        error: () => {
          alert("Failed to update status!");
        }
      });
  }

  // Export delivered orders as CSV
  exportDeliveredReport() {
    this.http.get<any[]>("http://localhost:8080/api/orders/delivered")
      .subscribe(data => {
        if (data.length === 0) {
          alert("No delivered orders found!");
          return;
        }

        this.exportService.exportToCSV("Delivered_Orders_Report", data);
        alert("Report downloaded successfully!");
      });
  }
}
