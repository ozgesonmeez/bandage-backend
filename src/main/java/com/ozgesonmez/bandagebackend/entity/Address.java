package com.ozgesonmez.bandagebackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String name;

    private String surname;

    private String phone;

    private String city;

    private String district;

    @Column(length = 1000)
    private String neighborhood;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;
}