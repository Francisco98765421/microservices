package br.edu.atitus.productservice.controllers;

import br.edu.atitus.productservice.dtos.ProductDTO;
import br.edu.atitus.productservice.entities.ProductEntity;
import br.edu.atitus.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("products")
public class ProductController {

    private final ProductRepository repository;

    @Value("${server.port}")
    private String port;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{idproduct}")
    public ProductDTO getProduct(
            @PathVariable Long idproduct,
            @RequestParam String targetCurrency
    ) {

        ProductEntity product = repository.findById(idproduct).orElseThrow();

        return new ProductDTO(
                product.getId(),
                product.getDescription(),
                product.getBrand(),
                product.getModel(),
                product.getPrice(),
                product.getCurrency(),
                product.getStock(),
                "Product-service running on Port: " + port,
                null, // obrigatório ser null por enquanto
                targetCurrency
        );
    }
}