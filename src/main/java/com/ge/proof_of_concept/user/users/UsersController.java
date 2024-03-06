package com.ge.proof_of_concept.user.users;


import com.ge.proof_of_concept._backup.users_history.UsersHistory;
import com.ge.proof_of_concept._backup.users_history.UsersHistoryService;
import com.ge.proof_of_concept.util.HistoryAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * This class is a controller for the Users entity
 * It handles the HTTP requests and responses for the Users entity
 * <p>
 * Endpoints:
 * GET /api/v1/users - returns a list of all users
 * GET /api/v1/users/deleted - returns a list of all soft deleted users
 * POST /api/v1/users/add - adds a new user to the database
 * DELETE /api/v1/users/{SSO} - deletes a user from the database
 * POST /api/v1/users/{SSO}/restore - restores a deleted user from the database
 * <p>
 * Author: Abdellah ESSORDO
 * Created on: 03/06/2024
 */

@RestController
@RequestMapping(path = "api/v1/users")
public class UsersController {
    /**
     * TODO:
     *  updated_by should be the user who is currently logged in, currently it is the user who is being restored [ ]
     */

    private final UsersService usersService;
    private final UsersHistoryService usersHistoryService;

    @Autowired
    public UsersController(UsersService usersService, UsersHistoryService usersHistoryService) {
        this.usersService = usersService;
        this.usersHistoryService = usersHistoryService;
    }


    /**
     * GET /api/v1/users
     * <p>
     * This method returns a list of all users
     *
     * @param void
     * @return List<Users> - a list of all users
     */
    @GetMapping
    public List<Users> getUsers() {
        return usersService.getUsers();
    }

    /**
     * POST /api/v1/users/add
     * <p>
     * This method adds a new user to the database,
     * and also adds a new entry to the users_history table
     *
     * @param user: Users - the user to be added
     * @return void
     */
    @PostMapping(path = "/add")
    public void addNewUser(@RequestBody Users user) {
        /**
         * TODO:
         *  Add validation for the user object [ ]
         *  Check if the user already exists [ ]
         *  Add the user to the users table [x]
         *  Add the user to the users_history table [x]
         */

        // Add the user to the users table
        usersService.addNewUser(user);

        // Add the user to the users_history table
        usersHistoryService.addNewUserHistory(
                new UsersHistory(
                        user,
                        user.getName(),
                        user, // updated_by, Should be the user who is currently logged in
                        LocalDateTime.now(),
                        HistoryAction.CREATED
                )
        );
    }

    /**
     * DELETE /api/v1/users/{userId}
     * <p>
     * This method soft deletes a user from the database,
     * and also adds a new entry to the users_history table
     *
     * @param SSO: Long - the SSO of the user to be deleted
     * @return void
     */
    @DeleteMapping(path = "{SSO}")
    public void deleteUser(@PathVariable("SSO") Long SSO) {
        /**
         * TODO:
         *  Check if the user exists [x]
         *  Check if the user is already deleted [x]
         *  Soft delete the user from the users table [x]
         *  Add the user to the users_history table [x]
         */

        // Check if the user exists
        boolean exists = usersService.existsById(SSO);
        if (!exists) {
            throw new IllegalStateException("user with id " + SSO + " does not exist");
        }

        // Check if the user is already deleted
        Users user = usersService.findById(SSO);
        if (user.getDeletedAt() != null) {
            throw new IllegalStateException("user with id " + SSO + " is already deleted");
        }

        // Soft delete the user from the users table
        usersService.deleteUser(SSO);

        // Add the user to the users_history table
        usersHistoryService.addNewUserHistory(
                new UsersHistory(
                        user,
                        user.getName(),
                        user, // updated_by, Should be the user who is currently logged in
                        LocalDateTime.now(),
                        HistoryAction.DELETED
                )
        );

    }

    /**
     * POST /api/v1/users/{SSO}/restore
     * <p>
     * This method restores a user from the database,
     * and also adds a new entry to the users_history table
     *
     * @param SSO: Long - the SSO of the user to be restored
     * @return void
     */
    @PostMapping(path = "{SSO}/restore")
    public void restoreUser(@PathVariable("SSO") Long SSO) {
        /**
         * TODO:
         *  Check if the user exists [x]
         *  Check if the user is already restored [x]
         *  Restore the user from the users table [x]
         *  Add the user to the users_history table [x]
         */

        // Check if the user exists
        boolean exists = usersService.existsById(SSO);
        if (!exists) {
            throw new IllegalStateException("user with id " + SSO + " does not exist");
        }

        // Check if the user is already restored
        Users user = usersService.findById(SSO);
        if (user.getDeletedAt() == null) {
            throw new IllegalStateException("user with id " + SSO + " is already restored");
        }

        // Restore the user from the users table
        usersService.restoreUser(user);

    }

    @GetMapping(path = "/deleted")
    public List<Users> getAllSoftDeletedUsers() {
        return usersService.getSoftDeletedUsers();
    }
}
