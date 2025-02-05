package com.example.shop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 255)
    private String name;

    @NotNull
    private Integer price;

    @NotNull
    private Integer pieces;

    public Product() {}

    public Product(String name, Integer price, Integer pieces) {
        this.name= name;
        this.price = price;
        this.pieces = pieces;

    }


}



