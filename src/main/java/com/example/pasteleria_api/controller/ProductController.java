package com.example.pasteleria_api.controller;

import com.example.pasteleria_api.model.ProductDto;
import com.example.pasteleria_api.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //Trae la lista de productos
    @GetMapping
    public List<ProductDto> getProducts() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable int id) {
        return productService.getById(id);
    }
}
