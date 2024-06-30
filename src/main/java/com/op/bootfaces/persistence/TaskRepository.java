package com.op.bootfaces.persistence;

import com.op.bootfaces.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {
    Task findById(Long id);

	List<Task> findByTaskOwner(String currentUserId);
}
