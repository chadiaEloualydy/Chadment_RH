package com.chadment.chadment_rh.controller;

import com.chadment.chadment_rh.model.Department;
import com.chadment.chadment_rh.model.Employee;
import com.chadment.chadment_rh.service.ServiceDepImp;
import com.chadment.chadment_rh.service.ServiceEmpImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class DepartmentController {
    @Autowired
    ServiceDepImp service ;
    @Autowired
    ServiceEmpImp seremp ;

    @GetMapping("/departments")
    public String getAllDeps(Model model) {
        model.addAttribute("nbrDep",service.Count());
        model.addAttribute("deps",service.listAll());
        model.addAttribute("depWithMaxNbrEmp",service.plusNbrEmp());
        return "departments";
    }
    @GetMapping("/newdep")
    public String addDep(Model model) {
        model.addAttribute("dep", new Department());
        model.addAttribute("pageTitle", "Ajouter Departement");
        return "new_dep";
    }

    @RequestMapping(value = "/savedep", method = RequestMethod.POST)
    public String saveDepartment(@ModelAttribute("dep") Department dep, RedirectAttributes ra) {
        boolean idExists = service.checkIfIdExists(dep.getIdDept());
        if (idExists) {
            ra.addFlashAttribute("message", "L'ID du département existe déjà. Veuillez en choisir un autre !");
            return "redirect:/newdep";
        }
        else {
            service.saveDep(dep);
            ra.addFlashAttribute("message", "Le departemenent est ajouté avec succée !!");
            return "redirect:/departments";
        }
    }

    @RequestMapping("/editdep/{id}")
    public String showEditDepPage(@PathVariable(name = "id") String id, Model model) {
        model.addAttribute("dep",service.get(id));
        model.addAttribute("pageTitle","Modifier Le Departement (ID : "+id+")");
        return "new_dep";
    }

    @RequestMapping(value = "/updatedep/{id}",method = RequestMethod.POST)
    public String Editdepartment(@ModelAttribute(name = "dep") Department dep, @PathVariable(name = "id") String id , RedirectAttributes ra) {
        dep.setIdDept(id);
        service.saveDep(dep);
        ra.addFlashAttribute("message", "Le departement est modifié avec succée !!");
        return"redirect:/departments";
    }

    @RequestMapping("/deletedep/{id}")
    public String deletedepartment(RedirectAttributes ra, @PathVariable(name = "id") String id){
        service.delete(id);
        ra.addFlashAttribute("message", "Le departement " + id + " est supprimée avec succée !!");
        return "redirect:/departments";
    }

    @GetMapping("/searchDepEmpsbyid")
    public String getEmployeesByDepartment(@RequestParam("departmentInput") String departmentInput, Model model) {
        try{
            List<Employee> employeesByDepartment = service.getEmployeesByDepartment(departmentInput);
            model.addAttribute("employees", employeesByDepartment);
            return "employees";
        }
        catch(Exception ex){
            model.addAttribute("message", "L'id de cette departement n'existe pas dans la base d e donnée!!");
            return "index";
        }
    }
}
