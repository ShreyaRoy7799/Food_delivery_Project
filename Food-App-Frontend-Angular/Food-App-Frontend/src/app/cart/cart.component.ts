import { Component, OnInit } from '@angular/core';
import { CartService } from '../cart.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  cartItems: any[] = [];
  total: number = 0;

  constructor(private cartService: CartService) {}

  ngOnInit(): void {
    this.cartService.cart$.subscribe(items => {
      this.cartItems = items;
      this.total = this.cartService.getTotal();
    });
  }

  increase(item: any) {
    this.cartService.increaseQuantity(item);
  }

  decrease(item: any) {
    this.cartService.decreaseQuantity(item);
  }

  remove(item: any) {
    this.cartService.removeItem(item);
  }
}
