package com.ge.proof_of_concept.user.users;

import com.ge.proof_of_concept._backup.users_history.UsersHistory;
import com.ge.proof_of_concept._backup.users_history.UsersHistoryService;
import com.ge.proof_of_concept.user.users.dto.CreateUserRequest;
import com.ge.proof_of_concept.user.users.dto.CreateUserResponse;
import com.ge.proof_of_concept.user.users.dto.UserDto;
import com.ge.proof_of_concept.util.FilterUtil;
import com.ge.proof_of_concept.util.HistoryAction;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


/**
 * This class is a service for the Users entity
 * It manages the Users entity in the database and provides methods for CRUD operations
 *
 * Methods:
 * - getUsers
 * - getSoftDeletedUsers
 * - getUserBySSO
 * - addNewUser
 * - deleteUser
 * - getSoftDeletedUsers
 * - restoreUser
 *
 * Author: Abdellah ESSORDO
 * Created on: 03/03/2024
 */
@Service
public class UsersService {

    /**
     * TODO:
     *  updated_by should be the user who is currently logged in, currently it is the user who is being restored [ ]
     */

    private final UsersRepository usersRepository;
    private final UsersHistoryService usersHistoryService;
    private final EntityManager entityManager;

    @Autowired
    public UsersService(UsersRepository usersRepository, UsersHistoryService usersHistoryService, EntityManager entityManager) {
        this.usersRepository = usersRepository;
        this.usersHistoryService = usersHistoryService;
        this.entityManager = entityManager;
    }

    /**
     * This method returns a list of all non-soft deleted users
     *
     * @return List<Users>
     */
    public List<UserDto> getUsers() {
        // The two lines below are used to apply the deleted filter to the users table,
        // so that only the non-soft deleted users are returned
        Session session = entityManager.unwrap(Session.class);
        FilterUtil.applyDeletedFilter(session, Users.class);


        // Map User entities to UserDto objects
        return UserDto.fromUsers(usersRepository.findAll());
    }

    /**
     * This method returns a list of soft deleted users
     *
     * @return List<Users>
     */
    public List<UserDto> getSoftDeletedUsers() {

        // Map User entities to UserDto objects
        return UserDto.fromUsers(usersRepository.findByDeletedAtIsNotNull());
    }

    /**
     * This method returns a user by its SSO
     *
     * @param SSO: Long
     * @return Users
     */
    public Users getUserBySSO(Long SSO) {
        return usersRepository.findById(SSO)
                .orElseThrow(() -> new IllegalStateException("user with id " + SSO + " does not exist"));
    }

    /**
     * This method is used to add a new user to the database
     *
     * @param user: Users
     * @return void
     */
    public CreateUserResponse addNewUser(CreateUserRequest userRequest) {
        /**
         * TODO:
         *  Add validation for the user object [ ]
         *  Check if the user already exists [ ]
         *  Add the user to the users table [x]
         *  Add the user to the users_history table [x]
         */

        System.out.println("==================================== ");
        System.out.println("userRequest: " + userRequest);
        System.out.println("==================================== ");

        // Create a new user object
        Users newUser = new Users(userRequest.get_SSO(), userRequest.getName());

        // Add the user to the users table
        usersRepository.save(newUser);


        // Add the user to the users_history table
        usersHistoryService.recordActionToHistory(
                new UsersHistory(
                        newUser,
                        newUser.getName(),
                        newUser, // updated_by, Should be the user who is currently logged in
                        LocalDateTime.now(),
                        HistoryAction.CREATED
                )
        );

        // Create a new CreateUserResponse object
        CreateUserResponse response = new CreateUserResponse();
        response.set_SSO(newUser.get_SSO());
        response.setName(newUser.getName());

        return response;
    }


    /**
     * This method is used to delete a user from the database
     * Checks if the user exists and if it is already deleted, then soft deletes the user
     *
     * @param SSO: Long
     * @return void
     *
     * @throws IllegalStateException if the user with the given id does not exist
     */
    public void deleteUser(Long SSO) {
        // Check if the user exists
        boolean exists = usersRepository.existsById(SSO);
        if (!exists) {
            throw new IllegalStateException("user with id " + SSO + " does not exist");
        }

        // Check if the user is soft deleted
        Optional<Users> userOptional = usersRepository.findById(SSO);
        if (userOptional.isPresent()) {
            Users user = userOptional.get();

            if (user.getDeletedAt() != null) {
                throw new IllegalStateException("user with id " + SSO + " is already deleted");
            }

            // Soft delete the user from the users table
            usersRepository.deleteById(SSO);

            // Add the user to the users_history table
            usersHistoryService.recordActionToHistory(
                    new UsersHistory(
                            user,
                            user.getName(),
                            user, // updated_by, Should be the user who is currently logged in
                            LocalDateTime.now(),
                            HistoryAction.DELETED
                    )
            );

        }
    }


    /**
     * This method is used to restore a soft deleted user from the database
     *
     * @param SSO
     * @return void
     */
    public void restoreUser(Long SSO) {
        /**
         * TODO:
         *  Check if the user exists [x]
         *  Check if the user is already restored [x]
         *  Restore the user from the users table [x]
         *  Add the user to the users_history table [x]
         *  set deleted_at to null
         *  add a new entry to the users_history table
         */

        // Check if the user exists
        boolean exists = usersRepository.existsById(SSO);
        if (!exists) {
            throw new IllegalStateException("user with id " + SSO + " does not exist");
        }

        // Check if the user is already restored
        Optional<Users> userOptional = usersRepository.findById(SSO);
        if (userOptional.isPresent()) {
            Users user = userOptional.get();

            if (user.getDeletedAt() == null) {
                throw new IllegalStateException("user with id " + SSO + " is already restored");
            }

            // Restore the user from the users table
            // set deleted_at to null
            user.setDeletedAt(null);
            usersRepository.save(user);

            // add a new entry to the users_history table
            usersHistoryService.recordActionToHistory(
                    new UsersHistory(
                            user,
                            user.getName(),
                            user, // updated_by, Should be the user who is currently logged in
                            LocalDateTime.now(),
                            HistoryAction.RESTORED
                    )
            );

        }
    }
}
