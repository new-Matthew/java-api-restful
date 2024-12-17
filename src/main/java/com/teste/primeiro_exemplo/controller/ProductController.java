package com.teste.primeiro_exemplo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.teste.primeiro_exemplo.model.Product;
import java.util.List;
import java.util.Optional;

import com.teste.primeiro_exemplo.services.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @GetMapping // quando alguém fizer consulta /api/products chama o método getAll usando mapeamento padrão
    public List<Product> getAll(){
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Product> getById(@PathVariable Integer id){
        return productService.getById(id);
    }

    @PostMapping
    public Product toAdd(@RequestBody Product product){ // convete em produto (biding)
        return productService.toAdd(product);
    }
}

