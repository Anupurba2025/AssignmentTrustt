package com.example.MovieApp.controller;

import com.example.MovieApp.entity.User;
import com.example.MovieApp.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testGetUserByID() throws Exception{
        User user = new User(1L, "Anupurba", "anu@gmail.com", "1234",LocalDateTime.of(2024, 12, 15, 14, 30));
        Mockito.when(userService.getUserById(1L)).thenReturn(Optional.of(user));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("Anupurba"))
                .andExpect(jsonPath("$.email").value("anu@gmail.com"))
                .andExpect(jsonPath("$.password").value("1234"));
    }
    @Test
    public void testGetAllUsers() throws Exception {
        List<User> users = Arrays.asList(
                new User(1L, "Anu", "anu@gmail.com", "1234", LocalDateTime.of(2024, 12, 15, 14, 30)),
                new User(2L, "Ranu", "ranu@gmail.com", "12345", LocalDateTime.of(2024, 10, 15, 1, 30))
        );

        Mockito.when(userService.getAllUsers()).thenReturn(users);

        // Perform GET request and check each field individually
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].username").value("Anu"))
                .andExpect(jsonPath("$[0].email").value("anu@gmail.com"))
                .andExpect(jsonPath("$[0].password").value("1234"))
                .andExpect(jsonPath("$[0].joinDate").value("2024-12-15T14:30:00"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].username").value("Ranu"))
                .andExpect(jsonPath("$[1].email").value("ranu@gmail.com"))
                .andExpect(jsonPath("$[1].password").value("12345"))
                .andExpect(jsonPath("$[1].joinDate").value("2024-10-15T01:30:00"));
    }

    @Test
    public void testDeleteUserByID() throws Exception{
        Mockito.doNothing().when(userService).deleteUser(1L);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/users/1"))
                .andExpect(status().isOk());
        Mockito.verify(userService,Mockito.times(1)).deleteUser(1L);
    }
}
