package io.github.jonasfschuh.aws_RESTful.controller;

import io.github.jonasfschuh.aws_RESTful.enums.EventType;
import io.github.jonasfschuh.aws_RESTful.model.Product;
import io.github.jonasfschuh.aws_RESTful.repository.ProductRepository;
import io.github.jonasfschuh.aws_RESTful.service.ProductPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductRepository productRepository;
    private final ProductPublisher productPublisher;

    @Autowired
    public ProductController(ProductRepository productRepository, ProductPublisher productPublisher
    ) {
        this.productRepository = productRepository;
        this.productPublisher = productPublisher;
    }

    @GetMapping
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable long id) {
        Optional<Product> optProduct = productRepository.findById(id);
        if (optProduct.isPresent()) {
            return new ResponseEntity<Product>(optProduct.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Product> saveProduct(
            @RequestBody Product product) {
        Product productCreated = productRepository.save(product);

        productPublisher.publishProductEvent(productCreated, EventType.PRODUCT_CREATED, "lecter");

        return new ResponseEntity<Product>(productCreated,
                HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Product> updateProduct(
            @RequestBody Product product, @PathVariable("id") long id) {
        if (productRepository.existsById(id)) {
            product.setId(id);

            Product productUpdated = productRepository.save(product);

            productPublisher.publishProductEvent(productUpdated, EventType.PRODUCT_UPDATED, "vilson");

            return new ResponseEntity<Product>(productUpdated,
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") long id) {
        Optional<Product> optProduct = productRepository.findById(id);
        if (optProduct.isPresent()) {
            Product product = optProduct.get();

            productRepository.delete(product);

            productPublisher.publishProductEvent(product, EventType.PRODUCT_DELETED, "farbe");

            return new ResponseEntity<Product>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/bycode")
    public ResponseEntity<Product> findByCode(@RequestParam String code) {
        Optional<Product> optProduct = productRepository.findByCode(code);
        if (optProduct.isPresent()) {
            return new ResponseEntity<Product>(optProduct.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }














}
