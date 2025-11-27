import { Component, OnInit } from "@angular/core";
import { CartService } from "../cart.service";
import { Router } from "@angular/router";
import { HttpClient } from "@angular/common/http";

@Component({
  selector: "app-checkout",
  templateUrl: "./checkout.component.html",
  styleUrls: ["./checkout.component.css"],
})
export class CheckoutComponent implements OnInit {
  cart: any[] = [];
  total: number = 0;

  customerName: string = "";
  phone: string = "";
  address: string = "";

  constructor(
    private cartService: CartService,
    private http: HttpClient,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.cartService.cart$.subscribe((items) => {
      this.cart = items;
      this.total = this.cartService.getTotal();
    });
  }

  placeOrder() {
    // Validation
    if (this.cart.length === 0) {
      alert("Your cart is empty!");
      return;
    }

    if (!this.customerName || !this.phone || !this.address) {
      alert("Please fill all required fields!");
      return;
    }

    // Construct backend payload exactly as expected
    const orderPayload = {
      customerName: this.customerName,
      phone: this.phone,
      addressLine1: this.address,
      addressLine2: "",
      city: "Unknown",
      state: "Unknown",
      pincode: "000000",
      paymentMethod: "COD",

      items: this.cart.map((item) => ({
        menuItemId: item.id, // backend expects this
        name: item.name,
        price: item.price,
        quantity: item.quantity,
      })),
    };

    console.log("Sending Order:", orderPayload);

    this.http
      .post("http://localhost:8080/api/orders/place", orderPayload)
      .subscribe({
        next: (res: any) => {
          console.log("ORDER SAVED:", res);

          this.cartService.clearCart();

          this.router.navigate(["/order-success", res.id]);
        },
        error: (err) => {
          console.error("Order Error:", err);
          alert("Failed to place order.");
        },
      });
  }
}
