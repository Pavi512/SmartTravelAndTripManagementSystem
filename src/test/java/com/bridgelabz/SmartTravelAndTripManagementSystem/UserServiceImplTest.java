package com.bridgelabz.SmartTravelAndTripManagementSystem;

import com.bridgelabz.SmartTravelAndTripManagementSystem.dto.UserRequestDTO;
import com.bridgelabz.SmartTravelAndTripManagementSystem.dto.UserResponseDTO;
import com.bridgelabz.SmartTravelAndTripManagementSystem.exception.UserNotFoundException;
import com.bridgelabz.SmartTravelAndTripManagementSystem.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Class to test User service implementation
@SpringBootTest
public class UserServiceImplTest {

    // Field injection
    @Autowired
    private UserService userService;


    // Test whether user is registered correctly or not
    @Test
    public void registerUser() {

        // Creates request object
        UserRequestDTO user = new UserRequestDTO();

        user.setName("Test User");
        user.setEmail("register.user@example.com");
        user.setPassword("TestUser@123");
        user.setPhone("9876543210");
        user.setRole("User");

        // Registers the user
        UserResponseDTO saved = userService.registerUser(user);

        // Verifies that user
        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals("Test User", saved.getName());
        assertEquals("register.user@example.com", saved.getEmail());
        assertEquals("9876543210", saved.getPhone());
        assertEquals("User", saved.getRole());
    }


    // Test whether we are getting all users
    @Test
    public void getAllUsers() {

        // Creates first test user
        UserRequestDTO user1 = new UserRequestDTO();

        user1.setName("Test User One");
        user1.setEmail("test.user.one@example.com");
        user1.setPassword("TestUserOne@123");
        user1.setPhone("9876543210");
        user1.setRole("User");

        // Creates second test user
        UserRequestDTO user2 = new UserRequestDTO();

        user2.setName("Test User Two");
        user2.setEmail("test.user.two@example.com");
        user2.setPassword("TestUserTwo@123");
        user2.setPhone("9123456789");
        user2.setRole("User");

        // Saves both test users
        userService.registerUser(user1);
        userService.registerUser(user2);

        // Retrieves all users
        List<UserResponseDTO> users = userService.getAllUsers();

        assertNotNull(users);
        assertTrue(users.size() >= 2);
    }


    // Test whether we are getting the correct user when we enter ID
    @Test
    public void getUserById() {

        // Creates a test user
        UserRequestDTO user = new UserRequestDTO();

        user.setName("Test User");
        user.setEmail("find.user@example.com");
        user.setPassword("FindUser@123");
        user.setPhone("9123456789");
        user.setRole("User");

        // Saves and gets generated ID
        UserResponseDTO saved = userService.registerUser(user);

        UserResponseDTO found = userService.getUserById(saved.getId());

        // Verify that correct user is returned
        assertNotNull(found);
        assertEquals(saved.getId(), found.getId());
        assertEquals("Test User", found.getName());
        assertEquals("find.user@example.com", found.getEmail());
        assertEquals("9123456789", found.getPhone());
        assertEquals("User", found.getRole());
    }


    // Test whether user is updating correctly using ID
    @Test
    public void updateUser() {

        // Creates original user
        UserRequestDTO user = new UserRequestDTO();

        user.setName("Original User");
        user.setEmail("update.user@example.com");
        user.setPassword("Original@123");
        user.setPhone("9876543211");
        user.setRole("User");

        // Saves original user and gets generated ID
        UserResponseDTO saved = userService.registerUser(user);


        // Creates updated user data
        UserRequestDTO updateUser = new UserRequestDTO();

        updateUser.setName("Updated User");
        updateUser.setEmail("updated.user@example.com");
        updateUser.setPassword("Updated@123");
        updateUser.setPhone("9123456780");
        updateUser.setRole("User");

        UserResponseDTO updated = userService.updateUser(saved.getId(), updateUser);

        // Verifies that user was updated correctly
        assertNotNull(updated);
        assertEquals(saved.getId(), updated.getId());
        assertEquals("Updated User", updated.getName());
        assertEquals("updated.user@example.com", updated.getEmail());
        assertEquals("9123456780", updated.getPhone());
        assertEquals("User", updated.getRole());
    }

    // Test whether user is deleted correctly using ID
    @Test
    public void deleteUser() {

        // Creates a test user
        UserRequestDTO user = new UserRequestDTO();

        user.setName("Delete User");
        user.setEmail("delete.user@example.com");
        user.setPassword("DeleteUser@123");
        user.setPhone("9876543210");
        user.setRole("User");

        // Saves user and gets generated ID
        UserResponseDTO saved = userService.registerUser(user);

        // Deletes the user
        userService.deleteUser(saved.getId());

        // Verifies that user no longer exists
        assertThrows(
                UserNotFoundException.class,
                () -> userService.getUserById(saved.getId())
        );
    }
}