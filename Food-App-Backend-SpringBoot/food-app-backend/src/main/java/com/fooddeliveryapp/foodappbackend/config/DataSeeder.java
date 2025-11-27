package com.fooddeliveryapp.foodappbackend.config;

import com.fooddeliveryapp.foodappbackend.entity.Restaurant;
import com.fooddeliveryapp.foodappbackend.entity.Role;
import com.fooddeliveryapp.foodappbackend.entity.User;
import com.fooddeliveryapp.foodappbackend.repository.RestaurantRepository;
import com.fooddeliveryapp.foodappbackend.repository.RoleRepository;
import com.fooddeliveryapp.foodappbackend.repository.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initData(RestaurantRepository restaurantRepo,
                               RoleRepository roleRepo,
                               UserRepository userRepo) {

        return args -> {

            /* -------------------------
                1️⃣ SEED ROLES
               ------------------------- */
            if (roleRepo.count() == 0) {
                Role admin = new Role("ADMIN");
                Role user = new Role("USER");
                roleRepo.save(admin);
                roleRepo.save(user);
                System.out.println("✔ Roles seeded");
            }

            /* -------------------------
                2️⃣ SEED ADMIN USER
               ------------------------- */
            if (userRepo.findByEmail("admin@gmail.com") == null) {

                User admin = new User();
                admin.setFirstName("Admin");
                admin.setLastName("User");
                admin.setEmail("admin@gmail.com");
                admin.setPassword("admin123"); // no encoder

                // assign ADMIN role
                Role adminRole = roleRepo.findByName("ADMIN");
                admin.addRole(adminRole);

                userRepo.save(admin);

                System.out.println("✔ Admin user created (admin@gmail.com / admin123)");
            }

            /* -------------------------
                3️⃣ SEED RESTAURANTS
               ------------------------- */
            if (restaurantRepo.count() == 0) {

                Restaurant r1 = new Restaurant();
                r1.setName("Dominos");

                Restaurant r2 = new Restaurant();
                r2.setName("Pizza Hut");

                Restaurant r3 = new Restaurant();
                r3.setName("KFC");

                restaurantRepo.save(r1);
                restaurantRepo.save(r2);
                restaurantRepo.save(r3);

                System.out.println("✔ Default restaurants seeded");
            }
        };
    }
}
