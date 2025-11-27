import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { CartService } from '../cart.service';

@Component({
  selector: 'app-resturant-detail',
  templateUrl: './resturant-detail.component.html',
  styleUrls: ['./resturant-detail.component.css']
})
export class ResturantDetailComponent implements OnInit {

  // YOU CONTROL ADMIN HERE:
  isAdmin: boolean = false; 
  // 👉 Set true = show admin tools
  // 👉 Set false = hide admin tools

  restaurant: any;
  id!: number;

  // Form fields
  menuName = '';
  menuType = '';

  itemName = '';
  itemPrice: any = '';
  itemRating: any = '';
  selectedMenuId: number | null = null;

  menu: any;

  constructor(
    private route: ActivatedRoute,
    private http: HttpClient,
    private cartService: CartService
  ) {}

  ngOnInit(): void {
    // ❗ IMPORTANT: DO NOT OVERRIDE isAdmin HERE
    // Leave admin mode exactly as you set it above.

    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.loadRestaurant();
  }

  loadRestaurant() {
    this.http.get(`http://localhost:8080/api/restaurants/${this.id}`).subscribe({
      next: (data: any) => {

        // Remove duplicate menus
        const uniqueMenus = new Map();
        data.menus?.forEach((m: any) => {
          uniqueMenus.set(m.id, m);
        });
        data.menus = Array.from(uniqueMenus.values());

        // Remove duplicate items
        data.menus?.forEach((menu: any) => {
          const uniqueItems = new Map();
          menu.items?.forEach((item: any) => {
            uniqueItems.set(item.id, item);
          });
          menu.items = Array.from(uniqueItems.values());
        });

        this.restaurant = data;
      }
    });
  }

  addMenu() {
    const menu = {
      name: this.menuName,
      type: this.menuType
    };

    this.http.post(`http://localhost:8080/api/menu/add/${this.id}`, menu)
      .subscribe(() => {
        this.menuName = '';
        this.menuType = '';
        this.loadRestaurant();
      });
  }

  addMenuItem() {
    if (!this.selectedMenuId) return;

    const item = {
      name: this.itemName,
      price: this.itemPrice,
      rating: this.itemRating
    };

    this.http.post(`http://localhost:8080/api/menu/item/add/${this.selectedMenuId}`, item)
      .subscribe(() => {
        this.itemName = '';
        this.itemPrice = '';
        this.itemRating = '';
        this.loadRestaurant();
      });
  }

  addToCart(item: any) {
    this.cartService.addItem(item);
    alert(item.name + ' added to cart!');
  }
}
