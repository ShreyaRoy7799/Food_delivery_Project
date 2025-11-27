import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private CART_KEY = 'APP_CART';

  private cart: any[] = [];
  private cartSubject = new BehaviorSubject<any[]>([]);

  cart$ = this.cartSubject.asObservable();

  constructor() {
    this.loadCartFromStorage();
  }

  // Load cart from localStorage on startup
  private loadCartFromStorage() {
    const data = localStorage.getItem(this.CART_KEY);
    if (data) {
      this.cart = JSON.parse(data);
      this.cartSubject.next([...this.cart]);
    }
  }

  // Save cart in localStorage whenever it changes
  private saveCart() {
    localStorage.setItem(this.CART_KEY, JSON.stringify(this.cart));
  }

  private updateCart() {
    this.cartSubject.next([...this.cart]);
    this.saveCart();
  }

  addItem(item: any) {
    const existing = this.cart.find(i => i.id === item.id);

    if (existing) {
      existing.quantity += 1;
    } else {
      this.cart.push({ ...item, quantity: 1 });
    }

    this.updateCart();
  }

  increaseQuantity(item: any) {
    item.quantity += 1;
    this.updateCart();
  }

  decreaseQuantity(item: any) {
    if (item.quantity > 1) {
      item.quantity -= 1;
    }
    this.updateCart();
  }

  removeItem(item: any) {
    this.cart = this.cart.filter(i => i.id !== item.id);
    this.updateCart();
  }

  clearCart() {
    this.cart = [];
    this.updateCart();
  }

  getCartItems() {
    return [...this.cart];
  }

  getTotal() {
    return this.cart.reduce((sum, item) => sum + item.price * item.quantity, 0);
  }
}
