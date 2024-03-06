package com.ge.proof_of_concept.affair.customer;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

/**
 * This class is a model for the Customer entity
 * It manages the Customer entity in the database and provides methods for CRUD operations
 *
 * Fields:
 *     _id: Long, PK
 *     name: String
 *     deleted_at: LocalDateTime
 *
 * Methods:
 *   - Constructors
 *   - Getters and Setters
 *   - toString
 */

@Entity
@Table(name = "customer")
@SQLDelete(sql = "UPDATE customer SET deleted_at = NOW() WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
public class Customer {

    @Id
    private Long _id;
    private String name;

    // Soft delete
    private LocalDateTime deleted_at = null;

    //==================================================================================================================
    // Constructors
    //==================================================================================================================

    /**
     * Creates a new Customer object with no parameters
     */
    public Customer() {}

    /**
     * Creates a new Customer object with the given parameters
     *
     * @param _id: Long
     * @param name: String
     */
    public Customer(Long _id, String name) {
        this._id = _id;
        this.name = name;
    }

    /**
     * Creates a new Customer object with the given parameters
     *
     * @param name: String
     */
    public Customer(String name) {
        this.name = name;
    }

    //==================================================================================================================
    // End of Constructors
    // Getters and Setters
    //==================================================================================================================

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(LocalDateTime deleted_at) {
        this.deleted_at = deleted_at;
    }

    //==================================================================================================================
    // End of Getters and Setters
    //==================================================================================================================


    @Override
    public String toString() {
        return "Customer{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", deleted_at=" + deleted_at +
                '}';
    }
}
