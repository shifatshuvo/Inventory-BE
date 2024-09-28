package com.example.inventory_management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Entity
@Table(name = "products")
@Getter @Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product name is mandatory")
    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    private Category category;

    @ManyToOne(optional = false)
    private User user;

    @NotNull
    @Min(value = 0, message = "Price must be a positive number")
    @Column(nullable = false)
    private int price;

    @Min(value = 0, message = "Quantity cannot be negative")
    private int quantity;

    private String imgUrl;
    private String description;
}










//@Entity
//@Table(name = "products")
//@Getter @Setter
//public class Product {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @NotBlank(message = "Product name is mandatory")
//    @Column(nullable = false)
//    private String name;
//
////    @ManyToOne(fetch = FetchType.LAZY)
////    @JoinColumn(name = "category_id", nullable = false)
////    @JsonIgnore
////    private Category category;
//
//    @ManyToOne
//    @JoinColumn(name = "category_id", nullable = false)
//    @JsonIgnore
//    private Category category;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    @JsonIgnore
//    private User user;
//
////    @ManyToOne(fetch = FetchType.LAZY)
////    @JoinColumn(name = "user_id", nullable = false)
////    @JsonIgnore
////    private User user;
//
//    @NotNull
//    @Min(value = 0, message = "Price must be a positive number")
//    @Column(nullable = false)
//    private int price;
//
//    @Min(value = 0, message = "Quantity cannot be negative")
//    private int quantity;
//
//    private String imgUrl;
//    private String description;
//
//
//}
