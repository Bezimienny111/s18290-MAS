package com.example.s18290mas.controller;

import com.example.s18290mas.entity.Employee;
import com.example.s18290mas.entity.Repair;
import com.example.s18290mas.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    @GetMapping({"/addEmployeeForm"})
    public ModelAndView addEmployeeForm(){
        ModelAndView mav = new ModelAndView("add-employee-form");
        Employee emp = new Employee();
        mav.addObject("employee",emp);
        return mav;
    }
    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute Employee employee){
        eRepo.save((employee));
        return "redirect:/list";
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long employeeId){
        ModelAndView mav = new ModelAndView("add-employee-form");
        Employee employee = eRepo.findById(employeeId).get();
        mav.addObject("employee",employee);
        return mav;
    }

    @GetMapping("/showDetailsForm")
    public ModelAndView showDetailsForm(@RequestParam Long employeeId){
        ModelAndView mav = new ModelAndView("details-employee");
        Employee employee = eRepo.findById(employeeId).get();
        List<Repair> repairs = employee.GetListFromSet();
        mav.addObject("employee",employee);
        mav.addObject("repairs",repairs);
        return mav;
    }

    @GetMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam Long employeeId){
        eRepo.deleteById(employeeId);
        return "redirect:/list";
    }

}
