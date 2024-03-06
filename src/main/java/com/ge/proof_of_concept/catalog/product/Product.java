package com.ge.proof_of_concept.catalog.product;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;


/**
 * This class is a model for the Product entity
 * It manages the Product entity in the database and provides methods for CRUD operations
 *
 * Fields:
 *      _id: Long, PK
 *      name: String
 *      price: Double
 *      deleted_at: LocalDateTime
 *
 * Methods:
 *    - Constructors
 *    - Getters and Setters
 *    - toString
 *
 * Author: Abdellah ESSORDO
 * Date: 03/03/2024
 */
@Entity
@Table(name = "product")
@SQLDelete(sql = "UPDATE product SET deleted_at = NOW() WHERE _id = ?")
@Where(clause = "deleted_at IS NULL")
public class Product {

    @Id
    private Long _id;
    private String name;
    private Double price;

    // Soft delete
    private LocalDateTime deleted_at = null;

    //==================================================================================================================
    // Constructors
    //==================================================================================================================

    /**
     * Creates a new Product object with no parameters
     */
    public Product() {}

    /**
     * Creates a new Product object with the given parameters
     *
     * @param _id: Long
     * @param name: String
     * @param price: Double
     */
    public Product(Long _id, String name, Double price) {
        this._id = _id;
        this.name = name;
        this.price = price;
    }

    /**
     * Creates a new Product object with the given parameters
     *
     * @param name: String
     * @param price: Double
     */
    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }


    //================================================================================================
    // End of constructors
    // Getters and setters
    //================================================================================================

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDateTime getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(LocalDateTime deleted_at) {
        this.deleted_at = deleted_at;
    }

    //================================================================================================
    // End of Getters and Setters
    //================================================================================================


    @Override
    public String toString() {
        return "Product{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", deleted_at=" + deleted_at +
                '}';
    }
}
