package com.ge.proof_of_concept.user.users;

import com.ge.proof_of_concept._backup.users_history.UsersHistory;
import com.ge.proof_of_concept._backup.users_history.UsersHistoryService;
import com.ge.proof_of_concept.util.FilterUtil;
import com.ge.proof_of_concept.util.HistoryAction;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final UsersHistoryService usersHistoryService;
    private final EntityManager entityManager;

    @Autowired
    public UsersService(UsersRepository usersRepository, UsersHistoryService usersHistoryService, EntityManager entityManager) {
        this.usersRepository = usersRepository;
        this.usersHistoryService = usersHistoryService;
        this.entityManager = entityManager;
    }


    public List<Users> getUsers() {
        Session session = entityManager.unwrap(Session.class);
        FilterUtil.applyDeletedFilter(session, Users.class);

        return usersRepository.findAll();
    }

    public void addNewUser(Users user) {
        usersRepository.save(user);
    }

    public boolean existsById(Long userId) {
        return usersRepository.existsById(userId);
    }

    public Users findById(Long userId) {
        return usersRepository.findById(userId).orElse(null);
    }

    public void deleteUser(Long userId) {
        usersRepository.deleteById(userId);
    }

    public List<Users> getSoftDeletedUsers() {
        return usersRepository.findByDeletedAtIsNotNull();
    }


    public void restoreUser(Users user) {
        /**
         * User assumed that exists by the time this method is called
         *
         * TODO:
         *  - set deleted_at to null
         *  - add a new entry to the users_history table
         */

        // set deleted_at to null
        user.setDeletedAt(null);
        usersRepository.save(user);

        // add a new entry to the users_history table
        usersHistoryService.addNewUserHistory(
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
