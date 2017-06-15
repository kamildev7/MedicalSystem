package com.medicalsystem.repository;

import com.medicalsystem.domain.Anesthetic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnestheticRepository extends JpaRepository<Anesthetic, Integer> {
}
