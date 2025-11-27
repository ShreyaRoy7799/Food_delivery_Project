package com.fooddeliveryapp.foodappbackend.Test;

import static org.assertj.core.api.Assertions.assertThat;

import com.fooddeliveryapp.foodappbackend.entity.Role;
import com.fooddeliveryapp.foodappbackend.entity.User;
import com.fooddeliveryapp.foodappbackend.repository.RoleRepository;
import com.fooddeliveryapp.foodappbackend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setEmail("ravikumadecr@gmail.com");
        user.setPassword("ravi2020");
        user.setFirstName("varma");
        user.setLastName("kmk");

        User savedUser = userRepo.save(user);

        User existUser = entityManager.find(User.class, savedUser.getId());

        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());

    }
    @Test
    public void testAddRoleToNewUser() {
        Role roleAdmin = roleRepo.findByName("Admin");

        User user = new User();
        user.setEmail("mikes.sss@gmawxwxil.com");
        user.setPassword("mikssewx2020");
        user.setFirstName("jhoqssnson");
        user.setLastName("Gassxwxxwtes");
        user.addRole(roleAdmin);

//        User savedUser = userRepo.save(user);

//        assertThat(savedUser.getRoles().size()).isEqualTo(1);
    }
}