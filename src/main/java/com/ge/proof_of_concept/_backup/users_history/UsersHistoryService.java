package com.ge.proof_of_concept._backup.users_history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersHistoryService {

    private final UsersHistoryRepository usersHistoryRepository;

    @Autowired
    public UsersHistoryService(UsersHistoryRepository usersHistoryRepository) {
        this.usersHistoryRepository = usersHistoryRepository;
    }

    public void addNewUserHistory(UsersHistory usersHistory) {
        usersHistoryRepository.save(usersHistory);
    }
}
