package com.example.cinematicket.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Table(name="invoices")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_invoice")
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_user")
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="promotion_id")
    Promotion promotion;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "id_schedule")
    Schedule schedule;

    @OneToMany(mappedBy = "invoice")
    List<Ticket> tickets;

    @OneToMany(mappedBy = "invoice")
    List<InvoiceItem> invoiceItems;

    Double totalAmount;

    @Column(name = "reservation_time")
    LocalDateTime reservationTime;

    @Column(name = "payment_expiration_time")
    LocalDateTime paymentExpirationTime;

    @Column(name = "payment_time")
    LocalDateTime paymentTime;

    @Column(name = "amount_paid")
    Double amountPaid;

    int status;

    Integer star;

    @PrePersist
    protected void onCreate() {
        this.reservationTime = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        // Nếu cần, cập nhật paymentExpirationTime khi entity được cập nhật
        // Ví dụ: this.paymentExpirationTime = LocalDateTime.now().plusMinutes(10);
    }
}
