package com.epam.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "Product")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Client userId;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "productId", fetch = FetchType.EAGER)
    private Lot lot;

}
