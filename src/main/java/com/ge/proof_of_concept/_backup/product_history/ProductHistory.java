package com.ge.proof_of_concept._backup.product_history;

import com.ge.proof_of_concept.catalog.product.Product;
import com.ge.proof_of_concept.user.users.Users;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

/**
 * This class is a model for the ProductHistory entity
 * It manages the ProductHistory entity in the database and provides methods for CRUD operations
 *
 * This entity is a backup of the Product entity, and servers as a history of the Product entity
 * It keeps track of the changes made to the Product entity, and the users who made the changes
 *
 * Fields:
 *     _id: Long, PK
 *     product_id: Long, FK
 *     name: String
 *     price: Double
 *     updated_by: User, FK
 *     updated_at: LocalDateTime
 *     deleted_at: LocalDateTime
 *
 * Methods:
 *   - Constructors
 *   - Getters and Setters
 *   - toString
 */

@Entity
@Table(name = "product_history")
@SQLDelete(sql = "UPDATE product_history SET deleted_at = NOW() WHERE _id = ?")
@Where(clause = "deleted_at IS NULL")
public class ProductHistory {

    @Id
    private Long _id;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "_id")
    private Product product;
    private String name;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "updated_by", referencedColumnName = "_SSO")
    private Users updated_by;
    private LocalDateTime updated_at;
    private LocalDateTime deleted_at = null;

    //==================================================================================================================
    // Constructors
    //==================================================================================================================

    /**
     * Creates a new ProductHistory object with no parameters
     */
    public ProductHistory() {
    }

    /**
     * Creates a new ProductHistory object with the given parameters
     *
     * @param _id: Long
     * @param product: Product
     * @param name: String
     * @param price: Double
     * @param updated_by: User
     * @param updated_at: LocalDateTime
     * @param deleted_at: LocalDateTime
     */
    public ProductHistory(Long _id, Product product, String name, Double price, Users updated_by, LocalDateTime updated_at, LocalDateTime deleted_at) {
        this._id = _id;
        this.product = product;
        this.name = name;
        this.price = price;
        this.updated_by = updated_by;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
    }

    /**
     * Creates a new ProductHistory object with the given parameters
     *
     * @param product: Product
     * @param name: String
     * @param price: Double
     * @param updated_by: User
     * @param updated_at: LocalDateTime
     * @param deleted_at: LocalDateTime
     */
    public ProductHistory(Product product, String name, Double price, Users updated_by, LocalDateTime updated_at, LocalDateTime deleted_at) {
        this.product = product;
        this.name = name;
        this.price = price;
        this.updated_by = updated_by;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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

    public Users getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(Users updated_by) {
        this.updated_by = updated_by;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
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
        return "ProductHistory{" +
                "_id=" + _id +
                ", product=" + product +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", updated_by=" + updated_by +
                ", updated_at=" + updated_at +
                ", deleted_at=" + deleted_at +
                '}';
    }
}
