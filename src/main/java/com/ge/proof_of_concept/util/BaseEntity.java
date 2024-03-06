package com.ge.proof_of_concept.util;

import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

/**
 * This class acts as a base class for all other entities (User, Product, etc.).
 * It defines a common field deleted (boolean) to represent the deleted state.
 * It serves as a base class for all entities that need to be soft deleted.
 *
 * Fields:
 * deleted: boolean
 *
 * Methods:
 * Getters and Setters
 *
 * Author: Abdellah ESSORDO
 * Date: 06/03/2024
 */

@MappedSuperclass
public class BaseEntity {

    // Soft delete
    public LocalDateTime deletedAt = null;

    // Getters and Setters
    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
}
