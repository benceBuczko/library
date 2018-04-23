package com.threelittlepigs.codecool.libraryManager.Services;

import com.threelittlepigs.codecool.libraryManager.Entities.Users.Member;
import com.threelittlepigs.codecool.libraryManager.Entities.Users.User;
import com.threelittlepigs.codecool.libraryManager.Repository.UserRepository;
import com.threelittlepigs.codecool.libraryManager.Utils.Validator;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Validator validator;


    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User registrateMember(Map<String, String> regData, Map<String, String> error) {
        User currentUser = userRepository.getUserByUserName(regData.get("userName"));
        if (currentUser == null) {
            if (validator.validateRegistration(regData, error)) {
                User newUser = createUserFromData(regData);
                userRepository.save(newUser);
                return newUser;
            }
        }
        return null;
    }

    private User createUserFromData(Map<String, String> regData) {
        return new Member(
                        regData.get("userName"),
                        regData.get("password"),
                        regData.get("firstName"),
                        regData.get("lastName"),
                        regData.get("email"),
                        regData.get("dateOfBirth"),
                        regData.get("address"),
                        regData.get("phoneNumber")
        );
    }

    public User loginUser(Map<String, String> logData, Map<String, String> error) {
        validator.validateLogin(logData, error);
        User currentUser = userRepository.getUserByUserName(logData.get("logUserName"));
        if (currentUser != null) {
            if (BCrypt.checkpw(logData.get("password"), currentUser.getPassword())){
                return currentUser;
            }
        }
        return null;
    }

    public User getUserById(int id) {
        return userRepository.findOne((long) id);
    }

    User getUserByName(String firstName, String lastName) {
        return userRepository.getUserByFirstNameAndLastName(firstName, lastName);
    }

    User getUserByEmailAddress(String email) {
        return userRepository.getUserByEmail(email);
    }

    User getUserByUsername(String username) {
        return userRepository.getUserByUserName(username);
    }
    void changeEmailAddress(String email, int id) {
        User currentUser = userRepository.findOne((long) id);
        currentUser.setEmail(email);
        userRepository.save(currentUser);
    }

    void changeAddress(String address, int id) {
        User currentUser = userRepository.findOne((long) id);
        currentUser.setAddress(address);
        userRepository.save(currentUser);
    }

    void changePassword(String pw, int id) {
        User currentUser = userRepository.findOne((long) id);
        currentUser.setPassword(pw);
        userRepository.save(currentUser);
    }

    void changePhoneNumber(String phoneNumber, int id) {
        User currentUser = userRepository.findOne((long) id);
        currentUser.setPhoneNumber(phoneNumber);
        userRepository.save(currentUser);
    }

    void changeUsername(String userName, int id) {
        User currentUser = userRepository.findOne((long) id);
        currentUser.setUserName(userName);
        userRepository.save(currentUser);
    }

    public Map<String, String> generateUserData(User currentUser) {
        Map<String, String> userData = new HashMap<>();
        userData.put("userName", currentUser.getUserName());
        userData.put("id", String.valueOf(currentUser.getId()));
        return userData;
    }

}
