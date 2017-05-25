package com.medicalsystem.repository;

import com.medicalsystem.model.Troponin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TroponinRepository extends JpaRepository<Troponin, Integer> {
}