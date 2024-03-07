package com.ge.proof_of_concept._backup.users_history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This class is a controller for the UsersHistory entity
 * It manages the UsersHistory entity in the database and provides methods for CRUD operations
 *
 * Author: Abdellah ESSORDO
 * Created on: 06/03/2024
 */
@RestController
@RequestMapping("api/v1/users/history")
public class UsersHistoryController {

    /**
     *
     */

    private final UsersHistoryService usersHistoryService;

    @Autowired
    public UsersHistoryController(UsersHistoryService usersHistoryService) {
        this.usersHistoryService = usersHistoryService;
    }

    /**
     * This method returns all history changes for a single user
     *
     * @param SSO - the SSO of the user
        * @return List<UsersHistory> - a list of all history changes for the user
     */
    @GetMapping(path = "{SSO}")
    public List<UsersHistory> getUserHistory(@PathVariable Long SSO) {
        return usersHistoryService.getUserHistory(SSO);
    }
}
