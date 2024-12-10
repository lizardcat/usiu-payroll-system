package com.alexraza.usiu.service;

import com.alexraza.usiu.model.Position;
import com.alexraza.usiu.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PositionService {

    @Autowired
    private PositionRepository positionRepository;

    // Get all positions
    public List<Position> getAllPositions() {
        return positionRepository.findAll();
    }

    // Get a position by ID
    public Position getPositionById(Long id) {
        Optional<Position> position = positionRepository.findById(id);
        return position.orElse(null);
    }

    // Add a new position
    public Position addPosition(Position position) {
        return positionRepository.save(position);
    }

    // Update an existing position
    public Position updatePosition(Long id, Position positionDetails) {
        Optional<Position> positionOptional = positionRepository.findById(id);
        if (positionOptional.isPresent()) {
            Position position = positionOptional.get();
            position.setTitle(positionDetails.getTitle());
            position.setBaseSalary(positionDetails.getBaseSalary());
            return positionRepository.save(position);
        }
        return null;
    }

    // Delete a position by ID
    public void deletePosition(Long id) {
        positionRepository.deleteById(id);
    }
}
