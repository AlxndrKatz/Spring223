package su.soviet.carsMVC.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import su.soviet.carsMVC.service.UserService;

@Controller
public class LoanController {

    private final UserService service;

    @Autowired
    public LoanController(UserService service) {
        this.service = service;
    }

    @GetMapping("/loan")
    public String getLoanByUserId(@RequestParam(value = "userId", required = false) Long userId,
                                  Model model) {
        model.addAttribute("loan", service.assessLoan(userId));
        return "loan";
    }
}