package com.ge.proof_of_concept._backup.product_history;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This class is a repository for the ProductHistory entity
 * It manages the ProductHistory entity in the database and provides methods for CRUD operations
 *
 * Author: Abdellah ESSORDO
 * Created on: 06/03/2024
 */
@Repository
public interface ProductHistoryRepository extends JpaRepository<ProductHistory, Long> {
}
