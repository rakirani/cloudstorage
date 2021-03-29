package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.HashService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignupController {
    private UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String signupPage(){
    return "signup";
    }

  @PostMapping("/signup")
    public String signupUser(@ModelAttribute Users users, Model model){
      String errorMsg=null;

      if (!userService.isUserNameAvailable(users.getUsername())){
          errorMsg= "The username already exists.";
      }
      if (errorMsg==null){
          int rowsAdded=userService.createUser(users);
          if (rowsAdded<0){
              errorMsg= "There was an error signing you up. Please try again.";
          }
      }
      if (errorMsg==null){
          model.addAttribute("signupSuccess",true);
      }else {
          model.addAttribute("errorMsg",errorMsg);
      }
      return "signup";
  }
}
