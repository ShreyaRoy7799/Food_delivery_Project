//package com.fooddeliveryapp.foodappbackend.entity;
//
//import javax.persistence.*;
//import java.util.HashSet;
//import java.util.Set;
//
//@Entity
//@Table(name = "user")
//public class UserEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String firstName;
//    private String lastName;
//    @Column(unique = true)
//    private String email;
//    private String password;
//    private String token;
//    private boolean accountVerified;
//    private int failedLoginAttempts;
//    private boolean loginDisabled;
//    @OneToMany(mappedBy = "user")
//    private Set<SecureToken> tokens;
//
//    @ManyToMany(cascade = {
//            CascadeType.PERSIST,
//            CascadeType.MERGE
//    })
//    @JoinTable(name = "user_groups",
//            joinColumns =@JoinColumn(name = "customer_id"),
//            inverseJoinColumns = @JoinColumn(name = "group_id"
//            ))
//    private Set<User> userGroups= new HashSet<>();
//
//    public Set<User> getUserGroups() {
//        return userGroups;
//    }
//
//    public void setUserGroups(Set<User> userGroups) {
//        this.userGroups = userGroups;
//    }
//}