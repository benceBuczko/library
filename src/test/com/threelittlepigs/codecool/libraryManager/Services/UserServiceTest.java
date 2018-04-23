package com.threelittlepigs.codecool.libraryManager.Services;

import com.threelittlepigs.codecool.libraryManager.Entities.Users.Member;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private static UserService us;

    @BeforeAll
    static void init(){
        us = new UserService();
    }

    @Test
    void testGetUserById() {
        assertNotNull(us.getUserById(1));
    }

    @Test
    void testGetUserByIdWithNull() {
        assertNull(us.getUserById(-1));
    }

    @Test
    void testChangeEmailAddress() {
        String prev_email = us.getUserById(1).getEmail();
        us.changeEmailAddress(prev_email + " changed", 1);
        assertNotEquals(prev_email, us.getUserById(1).getEmail());
    }

    /*@Test
    void testLogin() {
        Map<String, String> loginData = new HashMap<>();
        loginData.put("logUserName", "membergo");
        loginData.put("password", "asdasdasd");
        assertTrue(us.loginUser(loginData));
    }

    @Test
    void testLoginFalse() {
        Map<String, String> loginData = new HashMap<>();
        loginData.put("userName", "");
        loginData.put("password", "");
        assertFalse(us.loginUser(loginData));
    }*/

    @Test
    void testGetUserByName() {
        assertNotNull(us.getUserByName("Béla", "Membertest"));
    }

    @Test
    void testGetUserByNameReturnsNull() {
        assertNull(us.getUserByName("", ""));
    }

    @Test
    void testGetUserByEmail() {
        assertEquals(us.getUserByEmailAddress("bk@gmail.cm").getEmail(), "bk@gmail.cm");
    }

    @Test
    void testGetUserByEmailReturnsNull() {
        assertNull(us.getUserByEmailAddress(""));
    }

    @Test
    void testGetUserByUsername() {
        assertNotNull(us.getUserByUsername("membergo"));
    }

    @Test
    void testChangeUsername() {
        String prevUsername = us.getUserById(2).getUserName();
        us.changeUsername(prevUsername + " changed", 2);
        assertNotEquals(prevUsername, us.getUserById(2).getUserName());
    }

    @Test
    void testChangeAddress() {
        String prevAddress = us.getUserById(2).getAddress();
        us.changeAddress(prevAddress + " changed", 2);
        assertNotEquals(prevAddress, us.getUserById(2).getAddress());
    }

    @Test
    void testChangePassword() {
        String prevPassword = us.getUserById(2).getPassword();
        us.changePassword(prevPassword + " changed", 2);
        assertNotEquals(prevPassword, us.getUserById(2).getPassword());
    }

    @Test
    void testChangePhoneNumber() {
        String prevPhoneNumber = us.getUserById(2).getPhoneNumber();
        us.changePhoneNumber(prevPhoneNumber + " changed", 2);
        assertNotEquals(prevPhoneNumber, us.getUserById(2).getPhoneNumber());
    }

    @Test
    void testRegistration() {
        Map<String, String> regData = new HashMap<>();
        regData.put("userName", "TestCase");
        regData.put("password", "asdasdasd");
        regData.put("firstName", "Test");
        regData.put("lastName", "Case");
        regData.put("email", "tc@gm.com");
        regData.put("dateOfBirth", "2007-12-03");
        regData.put("address", "testclown");
        regData.put("phoneNumber", "0908070605");
    }
}