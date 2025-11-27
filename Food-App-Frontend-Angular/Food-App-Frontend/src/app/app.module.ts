import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { UserRegisterComponent } from "./user-register/user-register.component";
import { UserLoginComponent } from "./user-login/user-login.component";
import { FormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";
import { RouterModule } from "@angular/router";

import { ResturantComponent } from "./resturant/resturant.component";
import { MenuComponent } from "./menu/menu.component";
import { HomeComponent } from "./home/home.component";
import { ResturantDetailComponent } from "./resturant-detail/resturant-detail.component";
import { CartComponent } from "./cart/cart.component";
import { CheckoutComponent } from "./checkout/checkout.component";
import { OrderSuccessComponent } from "./order-success/order-success.component";
import { AdminOrdersComponent } from './admin-orders/admin-orders.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { AdminLoginComponent } from './admin-login/admin-login.component';
import { AdminAuthGuard } from "./admin-auth.guard";

@NgModule({
  declarations: [
    AppComponent,
    UserLoginComponent,
    UserRegisterComponent,
    ResturantComponent,
    MenuComponent,
    HomeComponent,
    ResturantDetailComponent,
    CartComponent,
    CheckoutComponent,
    OrderSuccessComponent,
    AdminOrdersComponent,
    AdminDashboardComponent,
    AdminLoginComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,

    RouterModule.forRoot([
      { path: "user-login", component: UserLoginComponent },
      { path: "user-signup", component: UserRegisterComponent },
      { path: "restaurants", component: ResturantComponent },
{ path: 'admin/orders', component: AdminOrdersComponent },
{ path: 'admin/dashboard', component: AdminDashboardComponent },
{ path: 'admin-login', component: AdminLoginComponent },

{ path: 'admin/dashboard', component: AdminDashboardComponent, canActivate: [AdminAuthGuard] },
{ path: 'admin/orders', component: AdminOrdersComponent, canActivate: [AdminAuthGuard] },

      // ✅ STATIC ROUTES FIRST (IMPORTANT)
      { path: "cart", component: CartComponent },
      { path: "checkout", component: CheckoutComponent },

      // ✅ DYNAMIC ROUTE LAST (VERY IMPORTANT)
      { path: "restaurant/:id", component: ResturantDetailComponent },
      { path: "order-success/:id", component: OrderSuccessComponent },

      // Optional default
      { path: "", redirectTo: "/restaurants", pathMatch: "full" },
    ]),
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
