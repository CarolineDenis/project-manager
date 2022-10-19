package com.carolinedenis.projectmanager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.carolinedenis.projectmanager.models.Project;
import com.carolinedenis.projectmanager.models.User;
import com.carolinedenis.projectmanager.repositories.ProjectRepository;

@Service
public class ProjectService {
	private final ProjectRepository projectRepo;
	
	public ProjectService(ProjectRepository projectRepo) {
		this.projectRepo = projectRepo;
	}
	
	public List<Project> allProject() {
		return projectRepo.findAll();
	}
	
	public List<Project> allByWorker(User user) {
		return projectRepo.findAllByWorkers(user);
	}
	
	public List<Project> allByNoWorker(User user) {
		return projectRepo.findByWorkersNotContains(user);
	}
		
	public Project findById(Long id) {
		Optional<Project> optionalProject = projectRepo.findById(id);
		if(optionalProject.isPresent()) {
			return optionalProject.get();
		} else {
			return null;
		}
	}
	
	public Project addProject(Project project) {
		return projectRepo.save(project);
	}
	
	public Project addWorker(Project project) {
		return projectRepo.save(project);
	}
	
	public Project updateProject(Project project, Long id) {
		User user = project.getLeader();
		Long userProjectId = user.getId();
		if (userProjectId != id) {
			return null;
		}
		return projectRepo.save(project);
	}
	
	public void deleteProject(Project project) {
		projectRepo.delete(project);
	}
}
