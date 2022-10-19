package com.carolinedenis.projectmanager.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.carolinedenis.projectmanager.models.LoginUser;
import com.carolinedenis.projectmanager.models.Project;
import com.carolinedenis.projectmanager.models.Task;
import com.carolinedenis.projectmanager.models.User;
import com.carolinedenis.projectmanager.services.ProjectService;
import com.carolinedenis.projectmanager.services.TaskService;
import com.carolinedenis.projectmanager.services.UserService;

@Controller
public class MainController {
	@Autowired
	private UserService userService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private TaskService taskService;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "index.jsp";
	}
	
	@PostMapping("/register")
	public String register(
			@Valid @ModelAttribute("newUser") User newUser,
			BindingResult result,
			Model model,
			HttpSession session) {
		User user = userService.register(newUser, result);
		if (result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			return "index.jsp";
		}
		session.setAttribute("userId", user.getId());
		return "redirect:/";
	}
	
	@PostMapping("/login")
	public String login(
			@Valid @ModelAttribute("newLogin") LoginUser newLogin,
			BindingResult result, 
			Model model, 
			HttpSession session) {
		User user = userService.login(newLogin,  result);
		if (result.hasErrors() || user==null) {
			model.addAttribute("newUser", user.getId());
			return "index.jsp";
		}
		session.setAttribute("userId", user.getId());
		return "redirect:/dashboard";
	}
	
	@GetMapping("/dashboard")
	public String welcome(HttpSession session, Model model) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		Long userId = (Long) session.getAttribute("userId");
		model.addAttribute("user", userService.findById(userId));
		
		User user = userService.findById(userId);
		
//		ArrayList<Project> projects = (ArrayList<Project>) projectService.allProject();
//		model.addAttribute("projects", projects);
		
		ArrayList<Project> projectForUser = (ArrayList<Project>) projectService.allByWorker(user);
		model.addAttribute("projectForUser", projectForUser);
		
		ArrayList<Project> projectVaccant = (ArrayList<Project>) projectService.allByNoWorker(user);
		model.addAttribute("projectVaccant", projectVaccant);
		
		return "dashboard.jsp";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.setAttribute("userId", null);
		return "redirect:/";
	}
	
	@GetMapping("/new/project")
	public String newProject(Model model,
			HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		model.addAttribute("project", new Project());
		return "NewProject.jsp";
	}
	
	@PostMapping("/project")
	public String  createProject(
			@Valid @ModelAttribute("project") Project project,
			BindingResult result,
			HttpSession session
			) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		if (result.hasErrors()) {
			return "NewProject.jsp";
		} else {
			projectService.addProject(project);
			return "redirect:/dashboard";
		}
	}
	
	@GetMapping("/project/{projectId}")
	public String showOneProject(
			@PathVariable ("projectId") Long id,
			Model model,
			HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		Project project = projectService.findById(id);
		model.addAttribute("project", project);
		return "oneProject.jsp";
	}
	
	@DeleteMapping("/project/{projectId}")
	public String deleteProject(
			@PathVariable("projectId") Long id,
    		HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		Project project = projectService.findById(id);
		projectService.deleteProject(project);
		return "redirect:/dashboard";
	}
	
	@GetMapping("project/edit/{projectId}")
	public String editProject(
			@PathVariable("projectId") Long id,
    		HttpSession session,
    		Model model) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		Project project = projectService.findById(id);
		if ((Long) session.getAttribute("userId") != project.getLeader().getId()) {
			return "redirect:/logout";
		}
		model.addAttribute("project", project);
		return "EditProject.jsp";
	}
	
	@PutMapping("/project/edit/{id}")
	public String updateProject(
			@Valid @ModelAttribute("project") Project project,
			BindingResult result,
			HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		Long userInSessionId = (Long) session.getAttribute("userId");
		if (result.hasErrors()) {
			return "EditProject.jsp";
		} else {
			projectService.updateProject(project, userInSessionId);
			return "redirect:/dashboard";
		}
	}
	
	@PutMapping("/jointeam/{id}")
	public String joinTeam(
			@RequestParam("projectId") Long projectId,
			@RequestParam("userId") Long userId,
			HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		Project project = projectService.findById(projectId);
		User worker = userService.findById(userId);
		project.getWorkers().add(worker);
		projectService.addWorker(project);
		return "redirect:/dashboard";
	}
	
	@PutMapping("/leaveteam/{id}")
	public String leaveTeam(
			@RequestParam("projectId") Long projectId,
			@RequestParam("userId") Long userId,
			HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redurect:/logout";
		}
		Project project = projectService.findById(projectId);
		User user = userService.findById(userId);
		
		user.getProgress().remove(project);
		userService.updateUser(user);
		return "redirect:/dashboard";
	}
	
	@GetMapping("/project/{id}/new/task")
	public String newTask(Model model,
			HttpSession session,
			@PathVariable("id") Long id) {
		model.addAttribute("task", new Task());
		if (session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		Project project = projectService.findById(id);
		model.addAttribute("project", project);
		model.addAttribute("tasks",taskService.allTasksByProject(id));
		return "task.jsp";
	}
	
	@PostMapping("/project/{id}/new/task")
	public String  createTask(
			@Valid @ModelAttribute("task") Task task,
			BindingResult result,
			HttpSession session,
			@PathVariable("id") Long id,
			Model model) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
//		Long userId = (Long) session.getAttribute("userId");
		Project project = projectService.findById(id);
		if (result.hasErrors()) {
			model.addAttribute("project", project);
			model.addAttribute("tasks",taskService.allTasksByProject(id));
			return "task.jsp";
		} else {
//			Task newTask = new Task(task.getName());
//			newTask.setProject(project);
//			newTask.setCreator(userService.findById(userId));
//			taskService.createTask(newTask);
			taskService.createTask(task);
			return "redirect:/project/" + id + "/new/task";
		}
	}
}
