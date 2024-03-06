package com.ge.proof_of_concept.user.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This interface is a repository for the Users entity
 * It manages the Users entity in the database and provides methods for CRUD operations
 *
 * Methods:
 *   - findByDeletedAtIsNotNull(): List<Users> - Find all soft deleted users
 *
 * Author: Abdellah ESSORDO
 * Date: 03/03/2024
 */
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    // This method returns a list of soft deleted users
    List<Users> findByDeletedAtIsNotNull();
}
