package com.carolinedenis.projectmanager.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.carolinedenis.projectmanager.models.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {
	List<Task> findAll();
	List<Task> findByProjectId(Long id);
}
