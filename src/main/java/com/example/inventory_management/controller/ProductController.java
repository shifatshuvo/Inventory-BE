package com.example.inventory_management.controller;

import com.example.inventory_management.dto.ProductResponseDto;
import com.example.inventory_management.model.Product;
import com.example.inventory_management.repository.ProductRepository;
import com.example.inventory_management.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/products")
//@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Create a new product
    @PostMapping
    public void createProduct(@RequestBody @Valid Product product) {
        productService.saveProduct(product);
    }

    // Get all products of a (specific category)

    // Get all products
    @GetMapping
    public Page<ProductResponseDto> getAllProducts(@RequestParam(required = false) String txt,
                                                   @RequestParam(required = false) Long catId,
                                                   Pageable pageable) {
        return productService.getAllProducts(txt, catId, pageable);
    }

    // Get a single product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update an existing product
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody Product product) {
        try {
            Product updatedProduct = productService.updateProduct(id, product);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProductById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    // Get total number of products
    @GetMapping("/count")
    public long getTotalProducts() {
        return productService.getTotalProducts();
    }



    // Increment Product Quantity
    @PutMapping("/{productId}/increment")
    public ResponseEntity<Product> incrementProductQuantity(
        @PathVariable Long productId,
        @RequestParam int amount) {

        Product updatedProduct = productService.incrementProductQuantity(productId, amount);
        return ResponseEntity.ok(updatedProduct);
    }

    // Decrement Product Quantity
    @PutMapping("/{productId}/decrement")
    public ResponseEntity<Product> decrementProductQuantity(
        @PathVariable Long productId,
        @RequestParam int amount) {

        Product updatedProduct = productService.decrementProductQuantity(productId, amount);
        return ResponseEntity.ok(updatedProduct);
    }


}










//@RestController
//@RequestMapping("/products")
//@CrossOrigin(origins = "*")
//public class ProductController {
//
//    @Autowired
//    private ProductService productService;
//
//    // Create a new product
//    @PostMapping
//    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
//        Product savedProduct = productService.saveProduct(product);
//        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
//    }
//
//    // Get all products
//    @GetMapping
//    public List<ProductResponseDto> getAllProducts() {
//        return productService.getAllProducts();
//    }
//
//    // Get a single product by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
//        Optional<Product> product = productService.getProductById(id);
//        return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
//            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }
//
//    // Update an existing product
//    @PutMapping("/{id}")
//    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody Product product) {
//        try {
//            Product updatedProduct = productService.updateProduct(id, product);
//            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
//        } catch (IllegalArgumentException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    // Delete a product
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
//        try {
//            productService.deleteProductById(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (IllegalArgumentException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//}









//@RestController
//@CrossOrigin(origins = "*")
//@RequestMapping("/products")
//public class ProductController {
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    @Autowired
//    private ProductService productService;
//
//    // save a pack
//    @PostMapping
//    private void saveProduct(@RequestBody @Valid Product prod) {
//        productService.storedProduct(prod);
//    }
//
//
//    // get all Product
//    @GetMapping
//    public List<Product> getAllProducts() {
//        return productService.getAllProducts();
//    }
//
//
//    // get a Product
//    @GetMapping("/{id}")
//    public Optional<Product> getOneProduct(@PathVariable Long id) {
//        return productService.getOneProduct(id);
//    }
//
//
//    // update a Product
//    @PutMapping("/{id}")
//    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Product prod) {
//        if (productService.updateProduct(prod, id)) {
//            return ResponseEntity.status(HttpStatus.OK).body("Product updated successfully");
//        } else {
//            return ResponseEntity.badRequest().body("Product not found or invalid data");
//        }
//    }
//
//
//    // delete a pack
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteProductById(@PathVariable Long id) {
//        try {
//            productService.deleteProductById(id);
//            return ResponseEntity.ok("Product deleted successfully");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete Product");
//        }
//    }
//}
