package com.ge.proof_of_concept.user.users;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class is a controller for the Users entity
 * It handles the HTTP requests and responses for the Users entity
 *
 * Endpoints:
 * GET /api/v1/users - returns a list of all users
 * GET /api/v1/users/deleted - returns a list of all soft deleted users
 * POST /api/v1/users/add - adds a new user to the database
 * DELETE /api/v1/users/{SSO} - deletes a user from the database
 * POST /api/v1/users/{SSO}/restore - restores a deleted user from the database
 *
 * Author: Abdellah ESSORDO
 * Created on: 03/03/2024
 */
@RestController
@RequestMapping(path = "api/v1/users")
public class UsersController {

    /**
     * TODO:
     *      Return JSON response for all the endpoints [ ]
     */

    private final UsersService usersService;
    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }


    /**
     * GET /api/v1/users
     *
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
     *
     * This method adds a new user to the database,
     * and also adds a new entry to the users_history table
     *
     * @param user: Users - the user to be added
     * @return void
     */
    @PostMapping(path = "/add")
    public void addNewUser(@RequestBody Users user) {
        usersService.addNewUser(user);
    }

    /**
     * DELETE /api/v1/users/{userId}
     *
     * This method soft deletes a user from the database,
     * and also adds a new entry to the users_history table
     *
     * @param SSO: Long - the SSO of the user to be deleted
     * @return void
     */
    @DeleteMapping(path = "{SSO}")
    public void deleteUser(@PathVariable("SSO") Long SSO) {
        usersService.deleteUser(SSO);
    }


    /**
     * POST /api/v1/users/{SSO}/restore
     *
     * This method restores a user from the database,
     * and also adds a new entry to the users_history table
     *
     * @param SSO: Long - the SSO of the user to be restored
     * @return void
     */
    @PostMapping(path = "{SSO}/restore")
    public void restoreUserBySSO(@PathVariable("SSO") Long SSO) {
        usersService.restoreUser(SSO);

    }

    /**
     * GET /api/v1/users/deleted
     *
     * This method returns a list of all soft deleted users
     *
     * @param void
     * @return List<Users> - a list of all soft deleted users
     */
    @GetMapping(path = "/deleted")
    public List<Users> getAllSoftDeletedUsers() {
        return usersService.getSoftDeletedUsers();
    }
}
