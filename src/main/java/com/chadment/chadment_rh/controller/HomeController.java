package com.chadment.chadment_rh.controller;

import com.chadment.chadment_rh.model.User;
import com.chadment.chadment_rh.service.ServiceUserImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

@Controller
public class HomeController {

    @Autowired
    ServiceUserImp service ;


    @GetMapping("/login")
    public String LoginUser(@RequestParam("userInput") String userEmail, @RequestParam("passwordInput") String pass, RedirectAttributes ra){
        try {
            User user = service.get(userEmail);
            if (Objects.equals(user.getPassword(), pass)){
                ra.addAttribute("username",user.getNom_Complet());
                return "redirect:/index";
            }
            else{
                ra.addFlashAttribute("message", "Le mot de passe est incorrect!!!");
                return "redirect:/";
            }
        }
        catch (Exception ex){
            ra.addFlashAttribute("message", "L'Email du user n'existe pas dans la base de donnée!!!");
            return "redirect:/";
        }
    }

    @GetMapping("/index")
    public String Home(){
        return "index";
    }
    @GetMapping("/")
    public String loginPage(){
        return "login";
    }

}
