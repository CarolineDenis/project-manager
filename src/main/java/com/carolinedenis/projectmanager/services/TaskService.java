package com.carolinedenis.projectmanager.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.carolinedenis.projectmanager.models.Task;
import com.carolinedenis.projectmanager.repositories.TaskRepository;

@Service
public class TaskService {
	private final TaskRepository taskRepo;

	public TaskService(TaskRepository taskRepo) {
		this.taskRepo = taskRepo;
	}

	public List<Task> allTasks() {
		return taskRepo.findAll();
	}
	
	public List<Task> allTasksByProject(Long id) {
		return taskRepo.findByProjectId(id);
	}
	
	public Task createTask(Task task) {
		return taskRepo.save(task);
	}
	
}
