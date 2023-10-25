package com.example.productservice_proxy.controllers;

import com.example.productservice_proxy.dtos.ProductDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

  @GetMapping(" ")
    public String getAllProducts() {
        return "Getting all the Products";
    }
    @GetMapping("/{productId}")
    public String getSingleProduct(@PathVariable("productId") Long productId) {
        return "Returning single Product with id: " + productId;
    }

    @PostMapping()
    public String addNewProduct(@RequestBody ProductDto productDto){
        return"Adding new Product"+productDto;

    }

    @PutMapping("/{productId}")
    public String updateProduct(@PathVariable("productId") Long productId){
        return "Updating Product with id: " + productId ;
    }

    @PatchMapping("/{productId}")
    public String patchProduct(@PathVariable("productId") Long productId){
        return "Patching Product with id: " + productId;
    }

    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId){
        return "Deleting Product with id: " + productId;
    }
}

