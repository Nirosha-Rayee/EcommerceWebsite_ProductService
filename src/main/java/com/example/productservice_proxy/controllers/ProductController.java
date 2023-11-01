package com.example.productservice_proxy.controllers;

import com.example.productservice_proxy.dtos.ProductDto;
import com.example.productservice_proxy.models.Product;
import com.example.productservice_proxy.services.ProductServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    ProductServiceInterface productService;
    public ProductController(ProductServiceInterface productService){
        this.productService=productService;
    }


  @GetMapping(" ")
//  public String getAllProducts() {
//
//      return "Getting all the Products";
//  }

//  public List<Product> getAllProducts() {
//        return this.productService.getAllProducts();
//    } // this is a Shortcut without creating a RESPONSE ENTITY for the below code.


    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(this.productService.getAllProducts(), HttpStatus.OK );



    }


    @GetMapping("/{productId}")
    //public String getSingleProduct(@PathVariable("productId") Long productId) {
    //public Product getSingleProduct(@PathVariable("productId") Long productId) {
        //productService.getSingleProduct(productId);
        //return "Returning single Product with id: " + productId;
        //String product = this.productService.getSingleProduct(productId);
        //return "Returning single Product with id: " + product;
    public ResponseEntity<Product> getSingleProduct(@PathVariable("productId") Long productId) {

        //ResponseEntity<Product> responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
        //ResponseEntity<Product> responseEntity = new ResponseEntity<>(product, HttpStatus.NOT_FOUND);
        //return responseEntity;

        try {
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add("Accept", "application/json");
            headers.add("Content-Type", "application/json");
            headers.add("auth-token","hey access me");

            Product product = this.productService.getSingleProduct(productId);
            if(productId < 1) {
                throw new IllegalArgumentException("something went wrong");
            }

            //ResponseEntity<Product> responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
            ResponseEntity<Product> responseEntity = new ResponseEntity<>(product, headers,HttpStatus.OK);


            //return product;
            return responseEntity;
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
//    public String addNewProduct(@RequestBody ProductDto productDto){
   public ResponseEntity<Product> addNewProduct(@RequestBody ProductDto productDto){
        Product product = this.productService.addNewProduct(productDto);
        ResponseEntity<Product> responseEntity = new ResponseEntity<>(product, HttpStatus.OK);

        //return"Adding new Product"+productDto;
        return responseEntity;

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

