package wad.tokkel.repositories;

import java.util.List;
import wad.tokkel.models.Task;

public interface TaskRepositoryCustom {

    List<Task> findByProject(Integer projectId);
}
