package com.ge.proof_of_concept._backup.users_history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    public UsersHistoryService(UsersHistoryRepository usersHistoryRepository) {
        this.usersHistoryRepository = usersHistoryRepository;
    }

    public void recordActionToHistory(UsersHistory usersHistory) {
        usersHistoryRepository.save(usersHistory);
    }
}
