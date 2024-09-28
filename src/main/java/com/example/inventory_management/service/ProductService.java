package com.example.inventory_management.service;


import com.example.inventory_management.dto.ProductResponseDto;
import com.example.inventory_management.model.Product;
import com.example.inventory_management.repository.CategoryRepository;
import com.example.inventory_management.repository.ProductRepository;
import com.example.inventory_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.springframework.util.StringUtils.hasText;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    // Save Product
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    // Update Product
    public Product updateProduct(Long id, Product updatedProduct) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            product.setName(updatedProduct.getName());
            product.setCategory(updatedProduct.getCategory());
            product.setUser(updatedProduct.getUser());
            product.setPrice(updatedProduct.getPrice());
            product.setQuantity(updatedProduct.getQuantity());
            product.setImgUrl(updatedProduct.getImgUrl());
            product.setDescription(updatedProduct.getDescription());

            return productRepository.save(product);
        } else {
            throw new IllegalArgumentException("Product not found");
        }
    }

    // Get all products
    @Transactional(readOnly = true)
    public Page<ProductResponseDto> getAllProducts(String txt, Long catId, Pageable pageable) {
        Page<Product> result;
        if (hasText(txt) && catId != null) {
            result = productRepository.findAllByCategoryIdAndNameStartsWithCategory(catId, txt, pageable);
        } else if (hasText(txt)) {
            result = productRepository.findAllByNameStartsWithTxt(txt, pageable);
        } else if (catId != null) {
            result = productRepository.findAllByCategoryIdWithCategory(catId, pageable);
        } else {
            result = productRepository.findAllWithCategory(pageable);
        }
        return result.map(p -> new ProductResponseDto(p));
    }

    // Get a single product by ID
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // Total products
    public long getTotalProducts() {
        return productRepository.count();
    }



    // Increment product quantity
    @Transactional
    public Product incrementProductQuantity(Long productId, int amount) {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        product.setQuantity(product.getQuantity() + amount);
        return productRepository.save(product);  // Save the updated product
    }

    // Decrement product quantity
    @Transactional
    public Product decrementProductQuantity(Long productId, int amount) {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new IllegalArgumentException ("Product not found"));

        if (product.getQuantity() >= amount) {
            product.setQuantity(product.getQuantity() - amount);
        } else {
            throw new IllegalArgumentException("Not enough quantity to decrement");
        }

        return productRepository.save(product);  // Save the updated product
    }


    // Delete a product
    @Transactional
    public void deleteProductById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Product not found");
        }
        productRepository.deleteById(id);
    }
}
















//@Service
//public class ProductService {
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    @Autowired
//    private CategoryRepository categoryRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    // Save Product
//    public Product saveProduct(Product product) {
//        if (!categoryRepository.existsById(product.getCategory().getId())) {
//            throw new IllegalArgumentException("Invalid category ID");
//        }
//
//        if (!userRepository.existsById(product.getUser().getId())) {
//            throw new IllegalArgumentException("Invalid user ID");
//        }
//
//        return productRepository.save(product);
//    }
//
//    // Update Product
//    public Product updateProduct(Long id, Product updatedProduct) {
//        Optional<Product> existingProduct = productRepository.findById(id);
//        if (existingProduct.isPresent()) {
//            Product product = existingProduct.get();
//            product.setName(updatedProduct.getName());
//            product.setCategory(updatedProduct.getCategory());
//            product.setUser(updatedProduct.getUser());
//            product.setPrice(updatedProduct.getPrice());
//            product.setQuantity(updatedProduct.getQuantity());
//            product.setImgUrl(updatedProduct.getImgUrl());
//            product.setDescription(updatedProduct.getDescription());
//
//            return productRepository.save(product);
//        } else {
//            throw new IllegalArgumentException("Product not found");
//        }
//    }
//
//    // Get all products
//    @Transactional(readOnly = true)
//    public List<ProductResponseDto> getAllProducts() {
//        List<Product> all = productRepository.findAllWithCategory();
//        return all.stream().map(p -> new ProductResponseDto(p)).toList();
//    }
//
//    // Get a single product by ID
//    public Optional<Product> getProductById(Long id) {
//        return productRepository.findById(id);
//    }
//
//    // Delete a product
//    @Transactional
//    public void deleteProductById(Long id) {
//        if (!productRepository.existsById(id)) {
//            throw new IllegalArgumentException("Product not found");
//        }
//        productRepository.deleteById(id);
//    }
//}











//@Service
//public class ProductService {
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    @Autowired
//    private CategoryRepository categoryRepository;
//
//    // save Product
//    public void storedProduct(Product prod) {
//        productRepository.save(prod);
//    }
//
//
//    // update Product
//    public boolean updateProduct(Product prod, Long id) {
//
//        Optional<Product> optionalProduct = productRepository.findById(id);
//        if (optionalProduct.isEmpty()) {
//            return false; //Product not found!!
//        }
//
//        // set fields
//        Product product = new Product();
//        product.setId(id);
//        product.setName(prod.getName());
//        product.setCategory(prod.getCategory());
//        product.setPrice(prod.getPrice());
//        product.setImgUrl(prod.getImgUrl());
//        product.setDescription(prod.getDescription());
//
//        productRepository.save(product);
//        return true; // Product updated successfully
//    }
//
//
//    // get all Product
//    public List<Product> getAllProducts() {
//        return productRepository.findAll();
//
////        List<Product> products = productRepository.findAll();
////        return products;
//    }
//
//
//    // get one Product
//    public Optional<Product> getOneProduct(@PathVariable Long id) {
//        return productRepository.findById(id);
//    }
//
//
//    // delete a Product
//    @Transactional
//    public void deleteProductById(Long productId) {
//        // Delete orders associated with the package
////        categoryRepository.deleteByPkgId(packageId);
//
//        // Delete the Product itself
//        productRepository.deleteById(productId);
//    }
//
//}
