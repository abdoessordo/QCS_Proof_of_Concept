package com.ge.proof_of_concept.user.users;


import com.ge.proof_of_concept.user.users.dto.CreateUserRequest;
import com.ge.proof_of_concept.user.users.dto.CreateUserResponse;
import com.ge.proof_of_concept.user.users.dto.UserDto;
import com.ge.proof_of_concept.util.dto.ResponseErrorVo;
import com.ge.proof_of_concept.util.dto.ResponseVO;
import com.ge.proof_of_concept.util.dto.ResponseVOBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
     *    Return JSON response for all the endpoints [ ]
     */

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }


    /**
     * GET /api/v1/users
     *
     * This method returns a list of all non-soft deleted users
     *
     * @param
     * @return List<Users> - a list of all users
     */
    @GetMapping
    public ResponseEntity<ResponseVO<List<UserDto>>> getAllUsers() {

        try {

            // Retreive all users
            List<UserDto> users = usersService.getUsers();

            // Construct the response object
            ResponseVO<List<UserDto>> response = new ResponseVOBuilder<List<UserDto>>()
                    .success()
                    .addData(users)
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception e) {

            // Handle exceptions and return error response
            ResponseErrorVo error = new ResponseErrorVo("INTERNAL_SERVER_ERROR", "Something went wrong");
            ResponseVO<List<UserDto>> errorResponse = new ResponseVOBuilder<List<UserDto>>()
                    .error(error)
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }

    }

    /**
     * GET /api/v1/users/deleted
     *
     * This method returns a list of all soft deleted users
     *
     * @param
     * @return List<Users> - a list of all soft deleted users
     */
    @GetMapping(path = "/deleted")
    public ResponseEntity<ResponseVO<List<UserDto>>> getAllSoftDeletedUsers() {

        try {

            // Retreive all soft deleted users
            List<UserDto> users = usersService.getSoftDeletedUsers();

            // Construct the response object
            ResponseVO<List<UserDto>> response = new ResponseVOBuilder<List<UserDto>>()
                    .success()
                    .addData(users)
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception e) {


            // Handle exceptions and return error response
            ResponseErrorVo error = new ResponseErrorVo("INTERNAL_SERVER_ERROR", "Something went wrong");
            ResponseVO<List<UserDto>> errorResponse = new ResponseVOBuilder<List<UserDto>>()
                    .error(error)
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }

    }

    /**
     * POST /api/v1/users/add
     *
     * This method adds a new user to the database,
     * and also adds a new entry to the users_history table
     *
     * @param userRequest: Users - the user to be added
     * @return void
     */
    @PostMapping(path = "/add")
    public ResponseEntity<ResponseVO<CreateUserResponse>> addUser(@RequestBody CreateUserRequest userRequest) {

        try {
            // Add the user to the database
            CreateUserResponse newUser = usersService.addNewUser(userRequest);

            // Construct the response object
            ResponseVO<CreateUserResponse> response = new ResponseVOBuilder<CreateUserResponse>()
                    .success()
                    .addMessage("User added successfully")
                    .addData(newUser)
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception e) {

            System.out.println(e);


            // Handle exceptions and return error response
            ResponseErrorVo error = new ResponseErrorVo("INTERNAL_SERVER_ERROR", "Something went wrong");
            ResponseVO<CreateUserResponse> errorResponse = new ResponseVOBuilder<CreateUserResponse>()
                    .error(error)
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }


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


}
