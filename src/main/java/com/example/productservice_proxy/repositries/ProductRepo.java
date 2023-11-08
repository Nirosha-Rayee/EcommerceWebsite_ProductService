package com.example.productservice_proxy.repositries;

import com.example.productservice_proxy.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    Product save(Product product); // save is a method of JpaRepository (interface) which is extended by ProductRepo (interface)

}
