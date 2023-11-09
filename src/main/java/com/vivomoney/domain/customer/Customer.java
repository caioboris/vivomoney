package com.vivomoney.domain.customer;


import com.vivomoney.domain.offer.Offer;
import com.vivomoney.dtos.CustomerDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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

    private LocalDate birthDate;

    @OneToMany(mappedBy = "customer")
    private List<Offer> offers;

    public Customer(CustomerDTO customerDTO){
        this.document = customerDTO.cpf();
        this.name = customerDTO.nome();
        this.birthDate = customerDTO.data_de_nascimento();
    }

}
