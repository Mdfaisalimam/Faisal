package com.example.taskmanager.service;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repo;

    public List<Task> getAllTasks() {
        return repo.findAll();
    }

    public Task createTask(Task task) {
        return repo.save(task);
    }

    public Optional<Task> getTaskById(Long id) {
        return repo.findById(id);
    }

    public Task updateTask(Long id, Task updated) {
        return repo.findById(id).map(task -> {
            task.setTitle(updated.getTitle());
            task.setDescription(updated.getDescription());
            task.setCompleted(updated.isCompleted());
            return repo.save(task);
        }).orElse(null);
    }

    public void deleteTask(Long id) {
        repo.deleteById(id);
    }
}
