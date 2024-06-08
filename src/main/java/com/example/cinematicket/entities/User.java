package com.example.cinematicket.entities;

import jakarta.persistence.*;
import lombok.*;

@Table(name="users")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    private String image;

    @Column(name = "full_name")
    private String fullName;

    private String gender;

    @Column(length = 10)
    private String phone;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(nullable = false)
    private String email;

    private String password;

    private String status;
}
