package com.vivomoney.domain.offer;

import com.vivomoney.domain.customer.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "offers")
@Table(name = "tb_offers")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal tax;

    private Long amount;

    private Long installments;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private LocalDateTime expirationDate;
}
