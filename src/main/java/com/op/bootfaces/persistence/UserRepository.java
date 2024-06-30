package com.op.bootfaces.persistence;

import com.op.bootfaces.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

	User findByUsername(String username);

    User findById(Long id);

    List<User> findByUserType(String employee);

    List<User> findByCompanyNo(String companyNo);
}
