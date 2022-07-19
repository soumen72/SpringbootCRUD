package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
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
@RequestMapping("/credentials")
public class CredentialController {

    private Logger logMsg = LoggerFactory.getLogger(CredentialController.class);


    private final UserService userService;
    private final CredentialService credentialService;
    public CredentialController(UserService userService, CredentialService credentialService) {
        this.userService = userService;
        this.credentialService = credentialService;
    }

    @PostMapping
    public String createEditCredential(Authentication authentication, Credential credential, Model model) {
        User user = userService.getUser(authentication.getName());
        Integer userId = user.getUserId();

        credential.setUserId(userId);

        Integer CredentialId = credential.getCredentialId();

        if (CredentialId == null){

            System.out.println(CredentialId +" ********** if");

            try {

                credentialService.insert(credential);
                model.addAttribute("successMessage", "Credential is created successfully.");

            } catch (Exception e) {
                logMsg.error("Cause: " + e.getCause() + ". Message: " + e.getMessage());
                model.addAttribute("errorMessage", "Error inserting Credential. Please try again!");

            }
        }
        else if (CredentialId != null) {

//            System.out.println(credential.getKey());
//            System.out.println(credential.getCredentialId()+" **********");

            try {
                System.out.println(user.getSalt()+" try of update");
                credentialService.update(credential);
                model.addAttribute("successMessage", "Credential is updated successfully.");

            } catch (Exception e) {
                logMsg.error("Cause: " + e.getCause() + ". Message: " + e.getMessage());
                model.addAttribute("errorMessage", "Error updating Credential. Please try again!");
            }
        }

        return "/result";
    }

    @GetMapping("/delete/{credentialId}")
    public String deleteCredential(@PathVariable(value = "credentialId") Integer credentialId, Model model) {

        if (credentialId != 0) {
            try {
                credentialService.delete(credentialId);
                model.addAttribute("successMessage", "Credential is deleted successfully.");
                return "/result";
            } catch (Exception e) {
                logMsg.error("Cause: " + e.getCause() + ". Message: " + e.getMessage());

                model.addAttribute("errorMessage", "Error deleting Credential. Please try again!");
                return "/result";
            }
        }

        return "/home";
    }
}