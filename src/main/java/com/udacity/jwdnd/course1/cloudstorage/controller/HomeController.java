package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final UserService userService;
    private final FileService fileService;
    private final NotesService noteService;
    private final CredentialService credentialService;
    private final EncryptionService encryptionService;

    public HomeController(UserService userService, FileService fileService, NotesService noteService, CredentialService credentialService, EncryptionService encryptionService) {
        this.userService = userService;
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
    }

//
    @GetMapping()
    public String getHomeControllerPage(Authentication authentication, Note note, Credential credential, Model model){

        User user = userService.getUser(authentication.getName());

        //System.out.println(fileService.getFiles(userId)  +"  "+userId+" ************ home controller");


        List<File> files = fileService.getFiles(user.getUserId());
        model.addAttribute("all_files", files);


        List<Note> notes = noteService.getNotes(user.getUserId());
        model.addAttribute("all_notes", notes);
        model.addAttribute("note", note);


        List<Credential> credentials = credentialService.getCredentials(user.getUserId());

        //displays and will take credentials in the left as reference key
        model.addAttribute("all_credentials", credentials);
        model.addAttribute("encryptionService", encryptionService);
        model.addAttribute("credential", credential);


        return "home";
    }

}
