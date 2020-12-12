package md.cernev.ProductManagment.controllers;

import md.cernev.ProductManagment.entities.User;
import md.cernev.ProductManagment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    @Autowired
    UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (!userService.saveUser(user)) {
            model.addAttribute("usernameError", "User with such login already exists.");
            return "/registration";
        }
        if (user.getWallet() < 0 || user.getWallet() > 1000) {
            model.addAttribute("usernameError", "Invalid wallet type");
            return "/registration";
        }
        return "redirect:/";
    }
}
