package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private List<Files> files;
    private final UserService userService;
    private final FileService fileService;

    public HomeController(UserService userService, FileService fileService) {
        this.userService = userService;
        this.fileService = fileService;
    }


    @PostConstruct
    public void postConstruct(){
        files = new ArrayList<>();
    }

    @GetMapping()
    public String homeView(Model model, Authentication authentication) {
        int userId = userService.getUser(authentication.getName()).getUserid();
        files = fileService.getFilesForUser(userId);
        model.addAttribute("files", files);
        return "home";
    }
}
