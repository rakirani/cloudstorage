package com.udacity.jwdnd.course1.cloudstorage.controller;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/files")
public class FileController {

    private UserService userService;
    private FileService fileService;

    public FileController(UserService userService, FileService fileService) {
        this.userService = userService;
        this.fileService = fileService;
    }

    @PostMapping("/fileUpload")
    public String handleFileUpload(@RequestParam("fileUpload") MultipartFile fileUpload, Authentication authentication, Model model) throws IOException {
        fileService.addFile(fileUpload,userService.getUser(authentication.getName()).getUserid());
        return "redirect:/result?success";
    }

    @GetMapping("/view")
    public ResponseEntity<ByteArrayResource> viewFile(@RequestParam("fileid") Integer fileid){
        Files files=fileService.findFile(fileid);
        ByteArrayResource byteArrayResource=new ByteArrayResource(files.getFiledata());
        return ResponseEntity.ok().contentType(new MediaType(files.getContenttype())) .contentLength(Long.parseLong(files.getFilesize())) .body(byteArrayResource);
    }

}
