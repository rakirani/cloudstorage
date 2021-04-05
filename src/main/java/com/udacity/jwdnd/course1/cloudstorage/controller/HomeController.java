package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
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
    private List<Notes> notes;
    private final UserService userService;
    private final FileService fileService;
    private final NotesService notesService;

    public HomeController(UserService userService, FileService fileService, NotesService notesService) {
        this.userService = userService;
        this.fileService = fileService;
        this.notesService = notesService;
    }

    @PostConstruct
    public void postConstruct(){
        files = new ArrayList<>();
        notes=new ArrayList<>();
    }

    @GetMapping()
    public String homeView(Model model, Authentication authentication) {
        int userId = userService.getUser(authentication.getName()).getUserid();
        files = fileService.getFilesForUser(userId);
        notes=notesService.getAllNotes(userId);
        model.addAttribute("files", files);
        model.addAttribute("notes",notes);
        return "home";
    }
}
