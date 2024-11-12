package com.celonis.tasks.tasks.controller;

import com.celonis.tasks.tasks.model.Task;
import com.celonis.tasks.tasks.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Optional;

public class TaskControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
    }

    @Test
    public void testGetAllTasks() throws Exception {
        // Mock the service layer
        Task task1 = new Task(1, "Task 1", "Description 1", "Pending", null, null);
        Task task2 = new Task(2, "Task 2", "Description 2", "In Progress", null, null);
        when(taskService.getAllTasks()).thenReturn(Arrays.asList(task1, task2));

        // Perform the GET request
        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));

        // Verify interactions with service layer
        verify(taskService, times(1)).getAllTasks();
    }

    @Test
    public void testGetTaskById() throws Exception {
        // Mock the service layer
        Task task = new Task(1, "Task 1", "Description 1", "Pending", null, null);
        when(taskService.getTaskById(1)).thenReturn(Optional.of(task));

        // Perform the GET request
        mockMvc.perform(get("/tasks/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Task 1"));

        // Verify interactions with service layer
        verify(taskService, times(1)).getTaskById(1);
    }

    @Test
    public void testCreateTask() throws Exception {
        // Create a Task object to send in the POST request
        Task task = new Task(0, "New Task", "New task description", "Pending", null, null);

        // Mock the service layer to return the created task
        when(taskService.createTask(any(Task.class))).thenReturn(task);

        // Perform the POST request
        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"New Task\", \"description\":\"New task description\", \"status\":\"Pending\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("New Task"))
                .andExpect(jsonPath("$.description").value("New task description"));

        // Verify interactions with service layer
        verify(taskService, times(1)).createTask(any(Task.class));
    }

    @Test
    public void testUpdateTask() throws Exception {
        // Mock the service layer
        Task existingTask = new Task(1, "Existing Task", "Existing task description", "Pending", null, null);
        Task updatedTask = new Task(1, "Updated Task", "Updated task description", "Completed", null, null);
        when(taskService.getTaskById(1)).thenReturn(Optional.of(existingTask));
        when(taskService.updateTask(eq(1), any(Task.class))).thenReturn(updatedTask);

        // Perform the PUT request
        mockMvc.perform(put("/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Updated Task\", \"description\":\"Updated task description\", \"status\":\"Completed\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Task"))
                .andExpect(jsonPath("$.description").value("Updated task description"));

        // Verify interactions with service layer
        verify(taskService, times(1)).updateTask(eq(1), any(Task.class));
    }
}
