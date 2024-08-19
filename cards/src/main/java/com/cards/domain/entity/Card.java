package com.cards.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cards_id_gen")
    @SequenceGenerator(name = "cards_id_gen", sequenceName = "cards_card_id_seq", allocationSize = 1)
    @Column(name = "card_id", nullable = false)
    private Long id;

    @Column(name = "mobile_number", nullable = false, length = 15)
    private String mobileNumber;

    @Column(name = "card_number", nullable = false, length = 100)
    private String cardNumber;

    @Column(name = "card_type", nullable = false, length = 100)
    private String cardType;

    @Column(name = "total_limit", nullable = false)
    private Integer totalLimit;

    @Column(name = "amount_used", nullable = false)
    private Integer amountUsed;

    @Column(name = "available_amount", nullable = false)
    private Integer availableAmount;

    @CreatedDate
    private String createdAt;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private String updatedAt;

    @LastModifiedBy
    private String updatedBy;
}
