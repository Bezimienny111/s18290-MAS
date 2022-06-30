package com.example.s18290mas.controller;

import com.example.s18290mas.entity.Employee;
import com.example.s18290mas.entity.Repair;
import com.example.s18290mas.model.DTO_Bonus;
import com.example.s18290mas.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

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

            eRepo.save(employee);

        return "redirect:/list";
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long employeeId){
        ModelAndView mav = new ModelAndView("add-employee-form");
        Employee employee = eRepo.findById(employeeId).get();
        System.out.println(employee);
        mav.addObject("employee",employee);
        return mav;
    }

   @GetMapping("/showBonusFormDto")
    public ModelAndView showBonusFormDto(@RequestParam Long employeeId, float bonus,float max_bonus, int repairs, int min_repairs){
        ModelAndView mav;
        String text;

        if(min_repairs > repairs){
            mav = new ModelAndView("error-form");
            text = "Employee has not enough repairs to add bonus to salary. Press 'Back' button to Employees List";
            mav.addObject("text_error", text);
        }else if(bonus >= max_bonus){
            mav = new ModelAndView("error-form");
            text = "Employee has already maximum total bonus. Press 'Back' button to Employees List";
            mav.addObject("text_error", text);
        }else{
            mav = new ModelAndView("bonus-form");
            DTO_Bonus dto_bonus = new DTO_Bonus(employeeId,bonus,max_bonus,0f);
            text = "Employee has repairs to add bonus to salary. Write value of bonus";
            mav.addObject("text_good", text);
            mav.addObject("dto",dto_bonus);
        }
        return mav;
    }

/*  @GetMapping("/showBonusFormEmp")
  public ModelAndView showBonusFormEmp(@RequestAttribute Employee emp,@RequestParam int min_repairs, float max_bonus){
      ModelAndView mav;
      String text;
      System.out.println(emp.getTotalRepairs());
      System.out.println(min_repairs);
      System.out.println(max_bonus);
      if(min_repairs >= emp.getTotalRepairs()){
          mav = new ModelAndView("error-form");
          text = "Employee has not enough repairs to add bonus to salary. Press 'Back' button to Employees List";
          mav.addObject("text_error", text);
      }else if(emp.getBonusSalary() >= max_bonus){
          mav = new ModelAndView("error-form");
          text = "Employee has already maximum total bonus. Press 'Back' button to Employees List";
          mav.addObject("text_error", text);
      }else{
          mav = new ModelAndView("bonus-form");
          text = "Employee has repairs to add bonus to salary. Write value of bonus";
          mav.addObject("text_good", text);
          mav.addObject("employee",emp);
      }
      return mav;
  }

*/

    @PostMapping("/showConfirmBonus")
    public ModelAndView showConfirmBonus(@ModelAttribute DTO_Bonus dto){
        ModelAndView mav;
        String text;
        Optional<Employee> emp = eRepo.findById(dto.getId());
        if(emp.get().getBonusSalary()+dto.getBonustoadd() > dto.getBonus_max()) {
            mav = new ModelAndView("error-form");
            text = "Employee bonus is to big. Press 'Back' button to Employees List";
            mav.addObject("text_error", text);
        }else{
            mav = new ModelAndView("final-form");
            text = "Bonus Added. Press 'Back' button to Employees List";
            mav.addObject("mess", text);
            emp.get().setBonusSalary(emp.get().getBonusSalary()+ dto.getBonustoadd());
            eRepo.save(emp.get());
        }
        return mav;
    }


    @GetMapping("/showDetailsForm")
    public ModelAndView showDetailsForm(@RequestParam Long employeeId){
        ModelAndView mav = new ModelAndView("details-employee");
        Employee employee = eRepo.findById(employeeId).get();
        //List<Employee> employees = eRepo.findAll();

       // System.out.println();
        System.out.println(employee.GetListFromSet());
        //System.out.println();
       // System.out.println(employees);
       // System.out.println();
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
