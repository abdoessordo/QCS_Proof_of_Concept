package com.ge.proof_of_concept._backup.users_history;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersHistoryRepository extends JpaRepository<UsersHistory, Long> {
}
