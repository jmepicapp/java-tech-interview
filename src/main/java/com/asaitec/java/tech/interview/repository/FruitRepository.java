package com.asaitec.java.tech.interview.repository;

import com.asaitec.java.tech.interview.model.FruitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FruitRepository extends JpaRepository<FruitEntity, Long> {
}
