package com.carolinedenis.projectmanager.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.carolinedenis.projectmanager.models.Project;
import com.carolinedenis.projectmanager.models.User;

public interface ProjectRepository extends CrudRepository<Project, Long> {
	List<Project> findAll();
	List<Project> findAllByWorkers(User user);
	List<Project> findByWorkersNotContains(User user);
}
