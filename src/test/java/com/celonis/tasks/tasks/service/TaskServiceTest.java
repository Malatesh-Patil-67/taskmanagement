package com.celonis.tasks.tasks.service;

import com.celonis.tasks.tasks.model.Task;
import com.celonis.tasks.tasks.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllTasks() {
        // Mock the repository behavior
        Task task1 = new Task(1, "Task 1", "Description 1", "Pending", null, null);
        Task task2 = new Task(2, "Task 2", "Description 2", "In Progress", null, null);
        when(taskRepository.findAll()).thenReturn(Arrays.asList(task1, task2));

        // Call the service method
        var tasks = taskService.getAllTasks();

        // Validate the results
        assertNotNull(tasks);
        assertEquals(2, tasks.size());
        assertEquals("Task 1", tasks.get(0).getTitle());
        assertEquals("Task 2", tasks.get(1).getTitle());

        // Verify interactions with the repository
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    public void testGetTaskById_TaskExists() {
        // Mock the repository behavior
        Task task = new Task(1, "Task 1", "Description 1", "Pending", null, null);
        when(taskRepository.findById(1)).thenReturn(Optional.of(task));

        // Call the service method
        Optional<Task> result = taskService.getTaskById(1);

        // Validate the results
        assertTrue(result.isPresent());
        assertEquals("Task 1", result.get().getTitle());

        // Verify interactions with the repository
        verify(taskRepository, times(1)).findById(1);
    }

    @Test
    public void testGetTaskById_TaskNotFound() {
        // Mock the repository behavior
        when(taskRepository.findById(1)).thenReturn(Optional.empty());

        // Call the service method
        Optional<Task> result = taskService.getTaskById(1);

        // Validate the results
        assertFalse(result.isPresent());

        // Verify interactions with the repository
        verify(taskRepository, times(1)).findById(1);
    }

    @Test
    public void testCreateTask() {
        // Prepare a task to save
        Task task = new Task(0, "New Task", "Description of new task", "Pending", null, null);

        // Mock the repository behavior
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        // Call the service method
        Task createdTask = taskService.createTask(task);

        // Validate the results
        assertNotNull(createdTask);
        assertEquals("New Task", createdTask.getTitle());

        // Verify interactions with the repository
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    public void testUpdateTask_TaskExists() {
        // Mock the repository behavior
        Task existingTask = new Task(1, "Existing Task", "Description", "Pending", null, null);
        Task updatedTask = new Task(1, "Updated Task", "Updated description", "In Progress", null, null);

        when(taskRepository.findById(1)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(any(Task.class))).thenReturn(updatedTask);

        // Call the service method
        Task result = taskService.updateTask(1, updatedTask);

        // Validate the results
        assertNotNull(result);
        assertEquals("Updated Task", result.getTitle());
        assertEquals("Updated description", result.getDescription());

        // Verify interactions with the repository
        verify(taskRepository, times(1)).findById(1);
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    public void testUpdateTask_TaskNotFound() {
        // Mock the repository behavior
        Task updatedTask = new Task(1, "Updated Task", "Updated description", "In Progress", null, null);
        when(taskRepository.findById(1)).thenReturn(Optional.empty());

        // Call the service method
        Task result = taskService.updateTask(1, updatedTask);

        // Validate the results
        assertNull(result);

        // Verify interactions with the repository
        verify(taskRepository, times(1)).findById(1);
    }

    @Test
    public void testDeleteTask_TaskExists() {
        // Mock the repository behavior
        when(taskRepository.existsById(1)).thenReturn(true);
        doNothing().when(taskRepository).deleteById(1);

        // Call the service method
        boolean result = taskService.deleteTask(1);

        // Validate the results
        assertTrue(result);

        // Verify interactions with the repository
        verify(taskRepository, times(1)).existsById(1);
        verify(taskRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteTask_TaskNotFound() {
        // Mock the repository behavior
        when(taskRepository.existsById(1)).thenReturn(false);

        // Call the service method
        boolean result = taskService.deleteTask(1);

        // Validate the results
        assertFalse(result);

        // Verify interactions with the repository
        verify(taskRepository, times(1)).existsById(1);
    }
}
