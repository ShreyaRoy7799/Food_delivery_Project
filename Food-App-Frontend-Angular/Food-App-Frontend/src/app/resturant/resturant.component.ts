import { Component, OnInit } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Router } from "@angular/router";

@Component({
  selector: "app-resturant",
  templateUrl: "./resturant.component.html",
  styleUrls: ["./resturant.component.css"],
})
export class ResturantComponent implements OnInit {
  imageError($event: ErrorEvent) {
    console.log("Image failed to load.");
  }

  restaurants: any[] = [];
  loading = true;
  error = "";

  restaurantImages = [
    "https://images.unsplash.com/photo-1504674900247-0877df9cc836",
    "https://images.unsplash.com/photo-1540189549336-e6e99c3679fe",
    "https://images.unsplash.com/photo-1546069901-ba9599a7e63c",
    "https://images.unsplash.com/photo-1552566626-52f8b828add9",
    "https://images.unsplash.com/photo-1545243421-042ec4742741",
    "https://images.unsplash.com/photo-1586190848861-99aa4a171e90",
  ];

  constructor(private http: HttpClient, private router: Router) {}

  ngOnInit(): void {
    this.loadRestaurants();
  }

  loadRestaurants() {
    this.http.get("http://localhost:8080/api/restaurants").subscribe({
      next: (data: any) => {
        this.restaurants = data.map((r: any, index: number) => ({
          ...r,
          image: this.restaurantImages[index % this.restaurantImages.length],
          rating: r.rating || (4 + Math.random()).toFixed(1),

          // ⭐ Correct menu item calculation
          menuCount: r.menus
            ? r.menus.reduce(
                (sum: number, m: any) => sum + (m.items?.length || 0),
                0
              )
            : 0,
        }));

        // fetch menu for each restaurant
        this.restaurants.forEach((restaurant, i) => {
          this.http
            .get(`http://localhost:8080/api/restaurants/${restaurant.id}`)
            .subscribe((full: any) => {
              this.restaurants[i].menuItems = full.menu || [];
            });
        });

        this.loading = false;
      },

      error: () => {
        this.error = "Failed to load restaurants";
        this.loading = false;
      },
    });
  }

  openRestaurant(id: number) {
    this.router.navigate(["/restaurant", id]);
  }
}
