package com.op.bootfaces.persistence;

import com.op.bootfaces.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {
    Company findById(Long id);
}
