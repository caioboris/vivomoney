package com.vivomoney.domain.customer;


import com.vivomoney.domain.offer.Offer;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "customers")
@Table(name = "tb_customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String document;
    private String name;
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "customer")
    private List<Offer> offers;

}
