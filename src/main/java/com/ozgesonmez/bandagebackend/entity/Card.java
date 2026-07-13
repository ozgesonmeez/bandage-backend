package com.ozgesonmez.bandagebackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cards")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "card_no", nullable = false, length = 19)
    private String cardNo;

    @Column(name = "expire_month", nullable = false)
    private Integer expireMonth;

    @Column(name = "expire_year", nullable = false)
    private Integer expireYear;

    @Column(name = "name_on_card", nullable = false)
    private String nameOnCard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;
}