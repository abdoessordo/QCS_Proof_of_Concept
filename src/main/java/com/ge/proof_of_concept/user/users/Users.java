package com.ge.proof_of_concept.user.users;

import com.ge.proof_of_concept.util.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import java.time.LocalDateTime;

/**
 * This class is a model for the Users entity
 * It manages the Users entity in the database and provides methods for CRUD operations
 *
 * Fields:
 *     _SSO: Long, PK
 *     name: String
 *     deleted_at: LocalDateTime
 *
 * Methods:
 *   - Constructors
 *   - Getters and Setters
 *   - toString
 */

@Entity
@Table(name = "users")
@SQLDelete(sql = "UPDATE users SET deleted_at = NOW() WHERE _SSO = ?")
@FilterDef(name = "deletedUsersFilter", parameters = @ParamDef(name = "includeDeleted", type = boolean.class))
@Filter(name = "deletedUsersFilter", condition = "deleted_at IS NULL")
public class Users extends BaseEntity {

    @Id
    private Long _SSO;
    private String name;


    //==================================================================================================================
    // Constructors
    //==================================================================================================================

    /**
     * Creates a new User object with no parameters
     */
    public Users() {
    }

    /**
     * Creates a new User object with the given parameters
     *
     * @param _SSO: Long
     * @param name: String
     * @param deletedAt: LocalDateTime
     */
    public Users(Long _SSO, String name, LocalDateTime deletedAt) {
        this._SSO = _SSO;
        this.name = name;
        this.deletedAt = deletedAt;
    }

    /**
     * Creates a new User object with the given parameters
     *
     * @param name: String
     * @param deletedAt: LocalDateTime
     */
    public Users(String name, LocalDateTime deletedAt) {
        this.name = name;
        this.deletedAt = deletedAt;
    }

    //==================================================================================================================
    // End of constructors
    // Getters and Setters
    //==================================================================================================================

    public Long get_SSO() {
        return _SSO;
    }

    public void set_SSO(Long _SSO) {
        this._SSO = _SSO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    //==================================================================================================================
    // End of Getters and Setters
    //==================================================================================================================


    @Override
    public String toString() {
        return "Users{" +
                "_SSO=" + _SSO +
                ", name='" + name + '\'' +
                '}';
    }
}

