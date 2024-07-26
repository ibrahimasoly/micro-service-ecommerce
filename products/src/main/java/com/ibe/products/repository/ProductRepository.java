package com.ibe.products.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibe.products.models.Products;

public interface ProductRepository extends JpaRepository<Products, Integer> {

    public List<Products> findAllByIdInOrderById(List<Integer> productId);

}
