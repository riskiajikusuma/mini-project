package com.ajik.cardlesswithdrawal.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "merchants")
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String merchantCode;

    @Column
    private String merchantName;

    @Column
    private Double merchantBalance;

    @OneToMany(mappedBy = "merchant")
    private List<CardlessWithdrawal> cardlessWithdrawals;

    @OneToOne(mappedBy = "merchant")
    private MerchantFee merchantFee;
}