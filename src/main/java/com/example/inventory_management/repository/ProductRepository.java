package com.example.inventory_management.repository;

import com.example.inventory_management.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // query to delete all products associated with the category
    void deleteByCategoryId(Long id);

    @EntityGraph(attributePaths = {"category"})
    @Query("FROM Product ORDER BY id DESC")
    Page<Product> findAllWithCategory(Pageable pageable);

    @EntityGraph(attributePaths = {"category"})
    @Query("FROM Product WHERE category.id=:catId ORDER BY id DESC")
    Page<Product> findAllByCategoryIdWithCategory(Long catId, Pageable pageable);

    @EntityGraph(attributePaths = {"category"})
    @Query("FROM Product WHERE name LIKE :txt%")
    Page<Product> findAllByNameStartsWithTxt(String txt, Pageable pageable);

    @EntityGraph(attributePaths = {"category"})
    @Query("FROM Product WHERE category.id=:catId AND name LIKE :txt% ORDER BY id DESC")
    Page<Product> findAllByCategoryIdAndNameStartsWithCategory(Long catId, String txt, Pageable pageable);

}
