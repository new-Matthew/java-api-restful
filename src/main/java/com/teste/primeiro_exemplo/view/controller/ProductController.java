package com.teste.primeiro_exemplo.view.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.teste.primeiro_exemplo.model.Product;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.teste.primeiro_exemplo.services.ProductService;
import com.teste.primeiro_exemplo.shared.ProductDTO;
import com.teste.primeiro_exemplo.view.model.ProductRequest;
import com.teste.primeiro_exemplo.view.model.ProductResponse;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @GetMapping // quando alguém fizer consulta /api/products chama o método getAll usando mapeamento padrão
    public ResponseEntity<List<ProductResponse>> getAll(){
        List<ProductDTO> products = productService.getAll();

        ModelMapper mapper = new ModelMapper();

        List<ProductResponse> response = products.stream()
        .map(productDto -> mapper.map(productDto, ProductResponse.class))
        .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProductResponse>> getById(@PathVariable Integer id){

       // try {
        Optional<ProductDTO> dto = productService.getById(id);

        ProductResponse product = new ModelMapper().map(dto.get(), ProductResponse.class);

        return new ResponseEntity<>(Optional.of(product), HttpStatus.OK);

       // } catch (Exception e) {
       //     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       // }

    }

    @PostMapping
    public ResponseEntity<ProductResponse> toAdd(@RequestBody ProductRequest productReq){ // convete em produto (biding)
        ModelMapper mapper = new ModelMapper();

        ProductDTO productDto = mapper.map(productReq, ProductDTO.class);

        productDto = productService.toAdd(productDto);

        return new ResponseEntity<>(mapper.map(productDto, ProductResponse.class), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id){
        productService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> toUpdate(@RequestBody Product productReq, @PathVariable Integer id){
        
        ModelMapper mapper = new ModelMapper(); // fazer um objeto se transformar em outro obj
        ProductDTO productDto = mapper.map(productReq, ProductDTO.class);

        productDto = productService.toUpdate(id, productDto);
        
        return new ResponseEntity<>(
            mapper.map(productDto, ProductResponse.class),
            HttpStatus.OK);
    }

}

