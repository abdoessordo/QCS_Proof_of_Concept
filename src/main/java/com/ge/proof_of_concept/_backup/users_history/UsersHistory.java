package com.ge.proof_of_concept._backup.users_history;

import com.ge.proof_of_concept.user.users.Users;
import com.ge.proof_of_concept.util.HistoryAction;
import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * This class is a model for the UsersHistory entity
 * It manages the UsersHistory entity in the database and provides methods for CRUD operations
 * 
 * This entity is a backup of the Product entity, and servers as a history of the Product entity
 * It keeps track of the changes made to the Product entity, and the users who made the changes
 * 
 * Fields:
 * _id: Long, PK
 * user_SSO: Long, FK
 * name: String
 * updated_by: User, FK
 * updated_at: LocalDateTime
 * action: Enum (CREATED, UPDATED, DELETED, RESTORED)
 * 
 * Methods:
 * - Constructors
 * - Getters and Setters
 * - toString
 *
 * Author: Abdellah ESSORDO
 * Created on: 03/03/2024
 */
@Entity
@Table(name = "users_history")
public class UsersHistory {


    @Id
    @SequenceGenerator(
            name = "users_history_sequence",
            sequenceName = "users_history_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "users_history_sequence"
    )
    private Long _id;

    @ManyToOne
    @JoinColumn(name = "user_SSO", referencedColumnName = "_SSO")
    private Users user;
    private String name;

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
     * Creates a new UsersHistory object with no parameters
     */
    public UsersHistory() {
    }

    /**
     * Creates a new UsersHistory object with the given parameters
     *
     * @param _id:        Long
     * @param user:       Users
     * @param name:       String
     * @param updated_by: User
     * @param updated_at: LocalDateTime
     * @param action:     HistoryAction
     */
    public UsersHistory(Long _id, Users user, String name, Users updated_by, LocalDateTime updated_at, HistoryAction action) {
        this._id = _id;
        this.user = user;
        this.name = name;
        this.updated_by = updated_by;
        this.updated_at = updated_at;
        this.action = action;
    }


    /**
     * Creates a new UsersHistory object with the given parameters
     *
     * @param user:       Users
     * @param name:       String
     * @param updated_by: User
     * @param updated_at: LocalDateTime
     * @param action:     HistoryAction
     */
    public UsersHistory(Users user, String name, Users updated_by, LocalDateTime updated_at, HistoryAction action) {
        this.user = user;
        this.name = name;
        this.updated_by = updated_by;
        this.updated_at = updated_at;
        this.action = action;
    }


    //==================================================================================================================
    // End of constructors
    // Getters and Setters
    //==================================================================================================================


    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    public void fillTransient() {
        // Fill the transient field with the enum description
        this.actionDescription = this.action;
    }

    @Override
    public String toString() {
        return "UsersHistory{" +
                "_id=" + _id +
                ", user=" + user +
                ", name='" + name + '\'' +
                ", updated_by=" + updated_by +
                ", updated_at=" + updated_at +
                ", action=" + action +
                '}';
    }
}
