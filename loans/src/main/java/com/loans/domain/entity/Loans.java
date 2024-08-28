package com.loans.domain.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Loans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private Long loanId;

    @Column(name = "mobile_number", length = 15, nullable = false)
    private String mobileNumber;

    @Column(name = "loan_number", length = 100, nullable = false)
    private String loanNumber;

    @Column(name = "loan_type", length = 100, nullable = false)
    private String loanType;

    @Column(name = "total_loan", nullable = false)
    private int totalLoan;

    @Column(name = "amount_paid", nullable = false)
    private int amountPaid;

    @Column(name = "outstanding_amount", nullable = false)
    private int outstandingAmount;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.createdBy = "SYSTEM";
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
        this.updatedBy = "SYSTEM";
    }
}
