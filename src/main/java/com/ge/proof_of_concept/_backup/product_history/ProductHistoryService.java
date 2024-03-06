package com.ge.proof_of_concept._backup.product_history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class is a service for the ProductHistory entity
 * It manages the ProductHistory entity in the database and provides methods for CRUD operations
 *
 * Author: Abdellah ESSORDO
 * Created on: 06/03/2024
 */
@Service
public class ProductHistoryService {

    private final ProductHistoryRepository productHistoryRepository;

    @Autowired
    public ProductHistoryService(ProductHistoryRepository productHistoryRepository) {
        this.productHistoryRepository = productHistoryRepository;
    }

    public void recordActionToHistory(ProductHistory productHistory) {
        productHistoryRepository.save(productHistory);
    }
}
