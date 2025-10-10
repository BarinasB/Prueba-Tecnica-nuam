package com.example.productapi.service;

import com.example.productapi.dto.ProductRequest;
import com.example.productapi.model.Product;
import com.example.productapi.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // 🔹 Obtener todos los productos
    public List<Product> getAll() {
        return productRepository.findAll();
    }

      // 🔹 Obtener productos paginados
    public Page<Product> getPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable);
    }

    // 🔹 Obtener un producto por ID
    public Optional<Product> getById(Long id) {
        return productRepository.findById(id);
    }

    // 🔹 Crear un producto
    public Product create(ProductRequest request) {
        Product product = new Product(
                request.getName(),
                request.getDescription(),
                request.getPrice()
        );
        return productRepository.save(product);
    }

    // 🔹 Actualizar un producto
    public Optional<Product> update(Long id, ProductRequest request) {
        Optional<Product> optional = productRepository.findById(id);
        if (optional.isEmpty()) {
            return Optional.empty();
        }

        Product existing = optional.get();
        existing.setName(request.getName());
        existing.setDescription(request.getDescription());
        existing.setPrice(request.getPrice());

        return Optional.of(productRepository.save(existing));
    }

    // 🔹 Eliminar un producto
    public boolean delete(Long id) {
        if (!productRepository.existsById(id)) {
            return false;
        }
        productRepository.deleteById(id);
        return true;
    }
}
