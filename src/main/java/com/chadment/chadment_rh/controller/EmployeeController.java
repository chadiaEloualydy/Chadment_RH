package com.chadment.chadment_rh.controller;

import com.chadment.chadment_rh.model.Employee;
import com.chadment.chadment_rh.service.ServiceDepImp;
import com.chadment.chadment_rh.service.ServiceEmpImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Component
public class EmployeeController {
    @Autowired
    ServiceEmpImp service ;
    @Autowired
    ServiceDepImp serDep ;

    @GetMapping("/employees")
    public String listerAllEmp(Model model){
        model.addAttribute("employees",service.listAll());
        model.addAttribute("nbrEmp",service.NbreTotalEmp());
        model.addAttribute("masssalariale",service.listAll().stream().mapToDouble(Employee::getSalaire).sum());
        return "employees";
    }

    @GetMapping("/newemp")
    public String ShowAddform(Model model){
        model.addAttribute("emp",new Employee());
        model.addAttribute("deps",serDep.listAll());
        return "/new_emp";
    }

    @RequestMapping(value = "/saveemp",method = RequestMethod.POST)
    public String saveemployee(@ModelAttribute(name = "emp")Employee emp, RedirectAttributes ra){
            service.saveEmp(emp);
            ra.addFlashAttribute("message", "L'employée est ajouté avec succée !!");
            return "redirect:/employees";
    }

    @RequestMapping("/editEmp/{id}")
    public ModelAndView ShowEditForm (@PathVariable(name = "id") Integer id){
        ModelAndView mav = new ModelAndView("new_emp");
        mav.addObject("emp",service.get(id));
        mav.addObject("deps",serDep.listAll());
        return mav ;
    }

    @RequestMapping(value = "/updateemp/{id}",method = RequestMethod.POST)
    public String Editemployee(@ModelAttribute(name = "emp")Employee emp, @PathVariable(name = "id") Integer id, RedirectAttributes ra){
        service.updateEmp(id,emp);
        ra.addFlashAttribute("message", "L'employée est modifié avec succée !!");
        return"redirect:/employees";
    }

    @RequestMapping("/deleteEmp/{id}")
    public String deleteEmp(@PathVariable(name = "id") Integer id, RedirectAttributes ra){
        service.delete(id);
        ra.addFlashAttribute("message","L'employée "+id+ " est supprimée avec succée !!");
        return"redirect:/employees";
    }

    @GetMapping("/searchEmp")
    public String getEmployeesByNom(@RequestParam("employeeInput") String employeeInput, Model model) {
        Employee employeesByNom = service.getByNom(employeeInput);
        model.addAttribute("employees", employeesByNom);
        return "employees";
    }
}
