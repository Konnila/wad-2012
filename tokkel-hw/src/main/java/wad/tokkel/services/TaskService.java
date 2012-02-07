package wad.tokkel.services;

import java.util.List;
import wad.tokkel.models.Task;

public interface TaskService extends RepositoryService<Task> {

    List<Task> findByProject(Integer projectId);

    Task save(Task task, Integer projectId);
}
