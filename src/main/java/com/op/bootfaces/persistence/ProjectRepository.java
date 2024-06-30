package com.op.bootfaces.persistence;

import com.op.bootfaces.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Long> {
    Project findById(Long id);

	List<Project> findByCompanyNo(String currentUserId);
}
