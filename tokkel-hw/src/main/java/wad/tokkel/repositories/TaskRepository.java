package wad.tokkel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wad.tokkel.models.Task;

public interface TaskRepository
        extends JpaRepository<Task, Integer>, TaskRepositoryCustom {
    // empty
}
