package wad.tokkel.controllers;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wad.tokkel.models.Task;
import wad.tokkel.services.TaskService;

@Controller("taskController")
public class TaskController {
  @Autowired
  private TaskService taskService;

  @RequestMapping(method = RequestMethod.GET, value = "tasks",
          produces = "application/json")
  @ResponseBody
  public Iterable<Task> list() {
    return taskService.findAll();
  }

  @RequestMapping(method = RequestMethod.GET,
          value = "projects/{projectId}/tasks",
          produces = "application/json")
  @ResponseBody
  public Iterable<Task> list(@PathVariable Integer projectId) {
    return taskService.findByProject(projectId);
  }

  @RequestMapping(method = RequestMethod.POST, value = "tasks",
          consumes = "application/json", produces = "application/json")
  @ResponseBody
  public Task create(@RequestBody Task task) {
    return taskService.save(task, task.getProjectId());
  }

  @RequestMapping(method = RequestMethod.POST, value = "projects/{projectId}/tasks",
          consumes = "application/json", produces = "application/json")
  @ResponseBody
  public Task create(@RequestBody Task task, @PathVariable Integer projectId) {
    return taskService.save(task, projectId);
  }

  @RequestMapping(method = RequestMethod.GET, value = "tasks/{taskId}",
          produces = "application/json")
  @ResponseBody
  public Task get(@PathVariable Integer taskId) {
    return taskService.findOne(taskId);
  }
  
  @RequestMapping(method = RequestMethod.GET, value = "projects/{projectId}/tasks/{taskId}",
          produces = "application/json")
  @ResponseBody
  public Task get(@PathVariable Integer projectId, @PathVariable Integer taskId) {
    return taskService.findOne(taskId);
  }

  @RequestMapping(method = RequestMethod.PUT, value = "tasks/{taskId}",
          consumes = "application/json", produces = "application/json")
  @ResponseBody
  public Task update(@PathVariable Integer taskId,
      @RequestBody Task task) {
    Task existingTask = taskService.findOne(taskId);
    if (existingTask == null) {
      return null;
    }

    if (task.isStart()) {
      if (task.getStartedTime() != null) {
        return null;
      }

      existingTask.setStartedTime(new Date());
    } else if (task.isStop()) {
      if (task.getStartedTime() == null || task.getStoppedTime() != null) {
        return null;
      }

      existingTask.setStoppedTime(new Date());
    }
    // TODO: here we could copy other fields if changing them was possible
    return taskService.saveAndFlush(existingTask);
  }

  @RequestMapping(method = RequestMethod.PUT, value = "projects/{projectId}/tasks/{taskId}",
          consumes = "application/json", produces = "application/json")
  @ResponseBody
  public Task update(@PathVariable Integer projectId,
      @PathVariable Integer taskId, @RequestBody Task task) {
    return update(taskId, task);
  }
  
  @RequestMapping(method = RequestMethod.DELETE, value = "tasks/{taskId}")
  @ResponseBody
  public void delete(@PathVariable Integer taskId) {
    taskService.delete(taskId);
  }

  @RequestMapping(method = RequestMethod.DELETE, value = "projects/{projectId}/tasks/{taskId}")
  @ResponseBody
  public void delete(@PathVariable Integer projectId, @PathVariable Integer taskId) {
    // TODO: projectId should be used here, but we are lazy
    taskService.delete(taskId);
  }
}
