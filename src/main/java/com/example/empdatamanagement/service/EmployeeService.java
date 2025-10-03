package com.example.empdatamanagement.service;

import com.example.empdatamanagement.entity.Employee;
import com.example.empdatamanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repo;

    public List<Employee> listAll() {
        return repo.findAll();
    }

    public Employee save(Employee e) {
        return repo.save(e);
    }

    public Employee get(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<Employee> search(String keyword) {
        return repo.findByNameContainingIgnoreCase(keyword);
    }
}
