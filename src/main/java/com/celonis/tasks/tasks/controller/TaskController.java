package com.celonis.tasks.tasks.controller;


import com.celonis.tasks.tasks.model.Task;
import com.celonis.tasks.tasks.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks/")
@Api(tags = "Task Operations")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    @ApiOperation(value = "Get all tasks")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get task by ID")
    public ResponseEntity<Task> getTaskById(@PathVariable int id) {
        Optional<Task> task = taskService.getTaskById(id);
        return task.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ApiOperation(value = "Create a new task")
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update task by ID")
    public ResponseEntity<Task> updateTask(@PathVariable int id, @RequestBody Task task) {
        Task updatedTask = taskService.updateTask(id, task);
        return updatedTask != null ? ResponseEntity.ok(updatedTask) : ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete task by ID")
    public ResponseEntity<Void> deleteTask(@PathVariable int id) {
        return taskService.deleteTask(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }


}