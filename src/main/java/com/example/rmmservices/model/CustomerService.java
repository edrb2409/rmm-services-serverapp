package com.example.rmmservices.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "service_customer",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"service_id", "customer_id"})
        })
public class CustomerService {

    @Id
    @SequenceGenerator(name = "service_customer_id_seq", sequenceName = "service_customer_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_customer_id_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

}
