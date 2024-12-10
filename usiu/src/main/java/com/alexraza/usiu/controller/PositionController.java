package com.alexraza.usiu.controller;

import com.alexraza.usiu.model.Department;
import com.alexraza.usiu.model.Position;
import com.alexraza.usiu.repository.DepartmentRepository;
import com.alexraza.usiu.repository.PositionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/positions")
public class PositionController {

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    // GET endpoint to load the page and display existing positions
    @GetMapping
    public String getPositionsPage(Model model) {
        List<Position> positions = positionRepository.findAll();
        model.addAttribute("positions", positions);
        model.addAttribute("newPosition", new Position()); // For the add form
        model.addAttribute("departments", departmentRepository.findAll()); // For department dropdown
        return "positions"; // Refers to positions.html Thymeleaf template
    }

    // POST endpoint to handle form submission for adding a new position
    @PostMapping("/add")
    public String addPosition(@ModelAttribute("newPosition") Position newPosition) {
        if (newPosition.getDepartment() != null && departmentRepository.existsById(newPosition.getDepartment().getId())) {
            // Retrieve the department object before saving
            Optional<Department> departmentOpt = departmentRepository.findById(newPosition.getDepartment().getId());
            departmentOpt.ifPresent(newPosition::setDepartment);
            positionRepository.save(newPosition);
        }
        return "redirect:/positions";
    }


    // GET endpoint for deleting a position
    @GetMapping("/delete/{id}")
    public String deletePosition(@PathVariable Long id) {
        if (positionRepository.existsById(id)) {
            positionRepository.deleteById(id);
        }
        return "redirect:/positions"; // Redirects back to positions list
    }
}
