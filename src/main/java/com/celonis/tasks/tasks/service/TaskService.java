package com.celonis.tasks.tasks.service;

import com.celonis.tasks.tasks.model.Task;
import com.celonis.tasks.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(int id) {
        return taskRepository.findById(id);
    }

    public Task createTask(Task task) {
        task.setCreatedAt(new java.sql.Date(System.currentTimeMillis()));
        task.setUpdatedAt(new java.sql.Date(System.currentTimeMillis()));
        return taskRepository.save(task);
    }

    public Task updateTask(int id, Task task) {
        Optional<Task> existingTask = taskRepository.findById(id);
        if (existingTask.isPresent()) {
            Task t = existingTask.get();
            t.setTitle(task.getTitle());
            t.setDescription(task.getDescription());
            t.setStatus(task.getStatus());
            t.setUpdatedAt(new java.sql.Date(System.currentTimeMillis()));
            return taskRepository.save(t);
        }
        return null;
    }
    public boolean deleteTask(int id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }
}