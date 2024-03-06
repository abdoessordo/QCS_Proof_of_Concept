package com.ge.proof_of_concept.catalog.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This interface is a repository for the Product entity
 * It manages the Product entity in the database and provides methods for CRUD operations
 *
 * Methods:
 *   - findByDeletedAtIsNotNull(): List<Product> - Find all soft deleted products
 *
 * Author: Abdellah ESSORDO
 * Date: 06/03/2024
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // This method returns a list of soft deleted products
    List<Product> findByDeletedAtIsNotNull();
}
