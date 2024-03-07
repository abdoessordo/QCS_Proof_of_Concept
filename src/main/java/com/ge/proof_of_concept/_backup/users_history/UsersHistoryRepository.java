package com.ge.proof_of_concept._backup.users_history;

import com.ge.proof_of_concept.user.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersHistoryRepository extends JpaRepository<UsersHistory, Long> {

    List<UsersHistory> findAllByUserOrderByUpdatedAtDesc(Users user);
}
