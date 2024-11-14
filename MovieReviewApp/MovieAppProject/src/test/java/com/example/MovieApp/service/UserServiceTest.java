package com.example.MovieApp.service;

import com.example.MovieApp.entity.User;
import com.example.MovieApp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getUserByID() {
        // Creating a sample user
        User user = new User();
        user.setId(1L);
        user.setUsername("Anupurba");
        user.setEmail("anupurba@gmail.com");
        user.setPassword("12345");
        user.setJoinDate(LocalDateTime.of(2024, 12, 15, 14, 30));

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Optional<User> fetchedUser = userService.getUserById(1L);
        assertNotNull(fetchedUser);
        assertEquals(1L, fetchedUser.get().getId());
    }
    @Test
    public void getAllUser(){
        List<User> users = Arrays.asList(
                new User(1L, "Anupurba", "anu@gmail.com", "1234", LocalDateTime.of(2024, 12, 15, 14, 30)),
                new User(2L, "Ranu", "ranu@gmail.com", "12345", LocalDateTime.of(2024, 10, 15, 1, 30)),
                new User(3L, "John", "john@example.com", "5678", LocalDateTime.of(2023, 5, 20, 10, 0))
        );
        Mockito.when(userRepository.findAll()).thenReturn(users);
        List<User> result = userService.getAllUsers();
        assertNotNull(result);
        assertEquals(3,result.size());
    }

    @Test
    public void deleteUserByID(){
        User user = new User(1L, "Anupurba", "anu@gmail.com", "1234", LocalDateTime.of(2024, 12, 15, 14, 30));
        Mockito.when(userRepository.existsById(1L)).thenReturn(true);
        Mockito.doNothing().when(userRepository).deleteById(1L);
        userRepository.deleteById(1L);
        Mockito.verify(userRepository,Mockito.times(1)).deleteById(1L);
    }
    @Test
    public void testUserNotFound() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<User> result = userService.getUserById(1L);

        assertFalse(result.isPresent());
    }

}
