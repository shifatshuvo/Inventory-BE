package com.example.inventory_management.dto;

import com.example.inventory_management.model.Category;
import com.example.inventory_management.model.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ProductResponseDto {

    private Long id;
    private String name;
    private Category category;
    private int price;
    private int quantity;
    private String imgUrl;
    private String description;

    public ProductResponseDto(Product product) {
        setId(product.getId());
        setName(product.getName());
        setDescription(product.getDescription());
        setQuantity(product.getQuantity());
        setPrice(product.getPrice());
        setImgUrl(product.getImgUrl());
        setCategory(product.getCategory());
    }
}
