package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NotesMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class NotesController {

    private UserService userService;
    private NotesService notesService;

    public NotesController(UserService userService, NotesService notesService) {
        this.userService = userService;
        this.notesService = notesService;
    }

   @PutMapping("/notes")
    public int updateNote( Notes notes){
        System.out.println("update called ************");
    return notesService.updateNotes(notes);
    }

    @PostMapping("/notes")
    public String createNote(Notes notes, Authentication authentication, Model model){
        int userid=userService.getUser(authentication.getName()).getUserid();
        notes.setUserid(userid);
        System.out.println("Adding notes called *****************");
        int count=notesService.addNotes(notes);
        try {
            if(count>0){
                model.addAttribute("success",true);
            }else {
                model.addAttribute("error",true);
            }
        }
        catch(Exception e){
            model.addAttribute("success", false);
        }
        return "result";
    }

    /*@GetMapping("/edit/{noteid}")
    public Notes editNote(Notes notes){
        notesService.addNotes(notes);
        return notes;
    }*/
}
