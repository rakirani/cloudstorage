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

        @PostMapping("/notes")
        public String createNote(@ModelAttribute Notes notes, Authentication authentication, Model model){
        try{
            int userid=userService.getUser(authentication.getName()).getUserid();
            int count=0;
            notes.setUserid(userid);
        if(notes.getNoteid()==0){
            count=notesService.addNotes(notes);

        }else {
            count = notesService.updateNotes(notes);
        }

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

    }
