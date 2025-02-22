package com.example.demo.entity;

import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users") // set table name
@NoArgsConstructor
@AllArgsConstructor
@Data // generate Getters and Setters using Lombok
public class User {
   // Define columns in table

   @Id // set primary key
   @GeneratedValue(strategy = GenerationType.IDENTITY) // generate the primary key value by the database itself using
                                                       // the auto-increment column option
   private Long id; // primary key

   @Column(unique = true, nullable = false)
   private String username;

   @Column(unique = true, nullable = false)
   private String email;

   @Column(nullable = false)
   private String password;

   @Column(nullable = false)
   private String address;

   @Column(length = 15)
   private String telephoneNumber;

   @Column(name = "first_login", nullable = false)
   private boolean firstLogin = true;
   
   // User (Many) --- (Many) Role
   // User side relationship
   @ManyToMany(fetch = FetchType.LAZY) // LAZY: This means that the related entities (in the Many-to-Many relationship)
                                       // will not be fetched immediately when the parent entity is loaded.
   @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
   @JsonIgnore //prevent this property from being included in the JSON output.
   private Set<Role> roles;
   // EAGER : When You Always Need the Related Data
   // LAZY : When You Avoid Unnecessary Data Loading










   
   @Override
   public int hashCode() {
      return Objects.hash(id);
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null || getClass() != obj.getClass())
         return false;
      User user = (User) obj;
      return Objects.equals(id, user.id);
   }
}
