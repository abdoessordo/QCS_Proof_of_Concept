package com.ge.proof_of_concept._backup.product_history;

import com.ge.proof_of_concept.catalog.product.Product;
import com.ge.proof_of_concept.user.users.Users;
import com.ge.proof_of_concept.util.HistoryAction;
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
 * _id: Long, PK
 * product_id: Long, FK
 * name: String
 * price: Double
 * updated_by: User, FK
 * updated_at: LocalDateTime
 * deleted_at: LocalDateTime
 *
 * Methods:
 * - Constructors
 * - Getters and Setters
 * - toString
 */

@Entity
@Table(name = "product_history")
@SQLDelete(sql = "UPDATE product_history SET deleted_at = NOW() WHERE _id = ?")
@Where(clause = "deleted_at IS NULL")
public class ProductHistory {

    @Id
    @SequenceGenerator(
            name = "product_history_sequence",
            sequenceName = "product_history_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_history_sequence"
    )
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
    private HistoryAction action;
    @Enumerated(EnumType.STRING)
    @Transient
    private HistoryAction actionDescription;

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
     * @param _id:        Long
     * @param product:    Product
     * @param name:       String
     * @param price:      Double
     * @param updated_by: User
     * @param updated_at: LocalDateTime
     * @param action:     HistoryAction
     */
    public ProductHistory(Long _id, Product product, String name, Double price, Users updated_by, LocalDateTime updated_at, HistoryAction action) {
        this._id = _id;
        this.product = product;
        this.name = name;
        this.price = price;
        this.updated_by = updated_by;
        this.updated_at = updated_at;
        this.action = action;
    }

    /**
     * Creates a new ProductHistory object with the given parameters
     *
     * @param product:    Product
     * @param name:       String
     * @param price:      Double
     * @param updated_by: User
     * @param updated_at: LocalDateTime
     * @param action:     HistoryAction
     */
    public ProductHistory(Product product, String name, Double price, Users updated_by, LocalDateTime updated_at, HistoryAction action) {
        this.product = product;
        this.name = name;
        this.price = price;
        this.updated_by = updated_by;
        this.updated_at = updated_at;
        this.action = action;
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

    public HistoryAction getAction() {
        return action;
    }

    public void setAction(HistoryAction action) {
        this.action = action;
    }

    public HistoryAction getActionDescription() {
        return actionDescription;
    }

    public void setActionDescription(HistoryAction actionDescription) {
        this.actionDescription = actionDescription;
    }
    //==================================================================================================================
    // End of Getters and Setters
    //==================================================================================================================

    @PostLoad
    private void fillTransient() {
        if (action != null) {
            this.actionDescription = HistoryAction.valueOf(action.name());
        }
    }

    @Override
    public String toString() {
        return "ProductHistory{" +
                "_id=" + _id +
                ", product=" + product +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", updated_by=" + updated_by +
                ", updated_at=" + updated_at +
                '}';
    }
}
