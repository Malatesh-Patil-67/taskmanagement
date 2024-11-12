package com.celonis.tasks.tasks.controller;


import com.celonis.tasks.tasks.model.Task;
import com.celonis.tasks.tasks.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
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
}