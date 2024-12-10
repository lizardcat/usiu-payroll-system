package com.alexraza.usiu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alexraza.usiu.model.Position;

public interface PositionRepository extends JpaRepository<Position, Long> {
}
