package com.threelittlepigs.codecool.libraryManager.Repository;

import com.threelittlepigs.codecool.libraryManager.Entities.Users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// This is how you define basic CRUD operations on the Customer entity
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getUserByFirstNameAndLastName(String firstName, String lastName);
    User getUserByUserName(String userName);
    User getUserByEmail(String email);

}