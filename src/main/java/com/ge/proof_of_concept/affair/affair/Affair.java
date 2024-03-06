package com.ge.proof_of_concept.affair.affair;

import com.ge.proof_of_concept.affair.customer.Customer;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;


/**
 * This class is a model for the Affair entity
 * It manages the Affair entity in the database and provides methods for CRUD operations
 *
 * Fields:
 *     _id: Long, PK
 *     customer: Customer, FK
 *     deleted_at: LocalDateTime
 *
 * Methods:
 *   - Constructors
 *   - Getters and Setters
 *   - toString
 */
@Entity
@Table(name = "affair")
@SQLDelete(sql = "UPDATE affair SET deleted_at = NOW() WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
public class Affair {

    @Id
    private Long _id;

    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "_id")
    private Customer customer;

    // Soft delete
    private LocalDateTime deleted_at = null;

    //==================================================================================================================
    // Constructors
    //==================================================================================================================

    /**
     * Creates a new Affair object with no parameters
     */
    public Affair() {}

    /**
     * Creates a new Affair object with the given parameters
     *
     * @param _id: Long
     * @param customer: Customer
     * @param deleted_at: LocalDateTime
     */
    public Affair(Long _id, Customer customer, LocalDateTime deleted_at) {
        this._id = _id;
        this.customer = customer;
        this.deleted_at = deleted_at;
    }

    /**
     * Creates a new Affair object with the given parameters
     *
     * @param customer: Customer
     */
    public Affair(Customer customer) {
        this.customer = customer;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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
        return "Affair{" +
                "_id=" + _id +
                ", customer=" + customer +
                ", deleted_at=" + deleted_at +
                '}';
    }
}
