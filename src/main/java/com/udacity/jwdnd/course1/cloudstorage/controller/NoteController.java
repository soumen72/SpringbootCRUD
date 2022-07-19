package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/notes")
public class NoteController {

    private Logger logmsg = LoggerFactory.getLogger(NoteController.class);


    private final UserService userService;
    private final NotesService notesService;
    public NoteController(UserService userService, NotesService notesService) {
        this.userService = userService;
        this.notesService = notesService;
    }

    @PostMapping()
    public String createEditNote(Authentication authentication, Note note, Model model) {
        User user = userService.getUser(authentication.getName());
        Integer userId = user.getUserId();
        note.setUserId(userId);


        Integer noteId = note.getNoteId();
        if (noteId == null) {

            try {
                notesService.insert(note);
                model.addAttribute("successMessage", "Your note is created successfully.");
            } catch (Exception e) {
                model.addAttribute("errorMessage", "Error inserting note. Please try again!");
                logmsg.error("Cause: " + e.getCause() + ". Message: " + e.getMessage());
            }
            return "/result";
        } else if (noteId != null) {

            try {
                notesService.update(note);
                model.addAttribute("successMessage", "Your note was updated successfully.");
            } catch (Exception e) {
                logmsg.error("Cause: " + e.getCause() + ". Message: " + e.getMessage());
                model.addAttribute("errorMessage", "Error updating note. Please try again!");
            }
            return "/result";
        }


        return "/result";

    }

    @GetMapping("/delete/{noteId}")
    public String deleteNote(@PathVariable Integer noteId, Model model) {

        if (noteId != 0) {

            try {
                notesService.delete(noteId);
                model.addAttribute("successMessage", "Your note is deleted successfully.");
                return "/result";
            } catch (Exception e) {
                logmsg.error("Cause: " + e.getCause() + ". Message: " + e.getMessage());
                model.addAttribute("errorMessage", "Error deleting note. Please try again!");
                return "/result";
            }
        }

        return "/home";
    }
}