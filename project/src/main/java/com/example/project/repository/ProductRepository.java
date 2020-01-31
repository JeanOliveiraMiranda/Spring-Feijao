package com.example.project.repository;

import com.example.project.domain.entities.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "UPDATE Product SET Imagem = :pathImage WHERE id = :id", nativeQuery = true)
    public void updateImage(String pathImage, Integer id);

}