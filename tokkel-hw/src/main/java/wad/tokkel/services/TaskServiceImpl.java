package wad.tokkel.services;

import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import wad.tokkel.models.Project;
import wad.tokkel.models.Task;
import wad.tokkel.repositories.ProjectRepository;
import wad.tokkel.repositories.TaskRepository;

@Service("taskService")
public class TaskServiceImpl extends AbstractRepositoryService<Task>
implements TaskService {
  @Autowired
  private TaskRepository taskRepository;

  @Autowired
  private ProjectRepository projectRepository;

  @PostConstruct
  private void init() {
    setRepository(taskRepository);
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = true,
          rollbackFor = Throwable.class)
  public List<Task> findByProject(Integer projectId) {
    return taskRepository.findByProject(projectId);
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false,
          rollbackFor = Throwable.class)
  public Task save(Task task, Integer projectId) {
    Project project = null;
    if (projectId != null) {
      project = projectRepository.findOne(projectId);
      project.getTasks().add(task);
    }
    task.setProject(project);
    taskRepository.saveAndFlush(task);
    return task;
  }
}
