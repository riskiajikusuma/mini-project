package com.ajik.cardlesswithdrawal.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "merchant_fee")
public class MerchantFee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double fee;

    @OneToOne()
    @JoinColumn(name = "merchant_id", referencedColumnName = "id")
    private Merchant merchant;
}
