package com.ibe.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibe.products.models.Categorie;

public interface CategoryRepository extends JpaRepository<Categorie, Integer> {
    
}
