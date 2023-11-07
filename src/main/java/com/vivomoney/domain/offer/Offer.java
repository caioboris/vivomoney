package com.vivomoney.domain.offer;

import com.vivomoney.domain.customer.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

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

    private BigDecimal installments;

    @ManyToOne
    private Customer customer;
}
