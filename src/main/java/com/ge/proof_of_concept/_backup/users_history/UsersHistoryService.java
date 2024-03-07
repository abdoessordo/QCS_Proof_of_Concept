package com.ge.proof_of_concept._backup.users_history;

import com.ge.proof_of_concept.user.users.Users;
import com.ge.proof_of_concept.user.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This class is a service for the UsersHistory entity
 * It manages the UsersHistory entity in the database and provides methods for CRUD operations
 *
 * Author: Abdellah ESSORDO
 * Created on: 03/03/2024
 */
@Service
public class UsersHistoryService   {

    private final UsersHistoryRepository usersHistoryRepository;
    private final UsersRepository usersRepository;

    @Autowired
    public UsersHistoryService(UsersHistoryRepository usersHistoryRepository, UsersRepository usersRepository) {
        this.usersHistoryRepository = usersHistoryRepository;
        this.usersRepository = usersRepository;
    }

    public void recordActionToHistory(UsersHistory usersHistory) {
        usersHistoryRepository.save(usersHistory);
    }

    /**
     * This method returns all history changes for a single user
     *
     * @param sso - the SSO of the user
     * @return List<UsersHistory> - a list of all history changes for the user
     */
    public List<UsersHistory> getUserHistory(Long sso) {
        // find user by sso
        Optional<Users> user = usersRepository.findById(sso);
        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        // Search for all history changes for the user, sorted by last modified date
        return usersHistoryRepository.findAllByUserOrderByUpdatedAtDesc(user.get());
    }
}
