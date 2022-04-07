package com.example.s18290mas.controller;

import com.example.s18290mas.entity.Employee;
import com.example.s18290mas.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository eRepo;

    @GetMapping({"/showEmployees","/","/list"})
    public ModelAndView getAllEmployees(){
        ModelAndView mav = new ModelAndView("list-employees");
        List<Employee> list = eRepo.findAll();
        mav.addObject("employees",list);
        return mav;
    }
}
