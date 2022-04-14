package com.example.s18290mas.controller;

import com.example.s18290mas.entity.Employee;
import com.example.s18290mas.entity.Repair;
import com.example.s18290mas.repository.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class RepairController {

    @Autowired
    private RepairRepository eRepo;

    @GetMapping({"/showRepairs"})
    public ModelAndView getAllRepairs(){
        ModelAndView mav = new ModelAndView("list-repairs");
        List<Repair> list = eRepo.findAll();
        mav.addObject("repairs",list);
        return mav;
    }

    @GetMapping("/showRepairsForm")
    public ModelAndView showRepairsForm(@RequestParam Long repairsId){
        ModelAndView mav = new ModelAndView("details-repair");
        Repair repair = eRepo.findById(repairsId).get();
        List<Employee> employees = repair.servisants();
        mav.addObject("employees",employees);
        mav.addObject("repair",repair);
        return mav;
    }

}
