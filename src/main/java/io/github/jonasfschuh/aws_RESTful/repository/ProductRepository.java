package io.github.jonasfschuh.aws_RESTful.repository;


import io.github.jonasfschuh.aws_RESTful.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Optional<Product> findByCode(String code);
}
