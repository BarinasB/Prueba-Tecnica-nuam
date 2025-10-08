package com.example.productapi.controller;

import com.example.productapi.dto.ProductRequest;
import com.example.productapi.model.Product;
import com.example.productapi.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // 🔹 Obtener todos los productos
    @GetMapping
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    // 🔹 Obtener un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    // 🔹 Crear un producto
    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody ProductRequest request) {
        Product product = new Product(
                request.getName(),
                request.getDescription(),
                request.getPrice()
        );

        Product saved = productRepository.save(product);
        return ResponseEntity.created(URI.create("/api/products/" + saved.getId())).body(saved);
    }

    // 🔹 Actualizar un producto
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @Valid @RequestBody ProductRequest request) {
        Optional<Product> optional = productRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Product existing = optional.get();
        existing.setName(request.getName());
        existing.setDescription(request.getDescription());
        existing.setPrice(request.getPrice());

        Product updated = productRepository.save(existing);
        return ResponseEntity.ok(updated);
    }

    // 🔹 Eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!productRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

