package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/files")
public class FileController {
    //we have to 2

    private Logger logmsg = LoggerFactory.getLogger(FileController.class);

    private final FileService fileService;
    private final UserService userService;
    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile multipartFile, Authentication authentication, Model model) {

        if (multipartFile.isEmpty()) {

            model.addAttribute("errorMessage", "File not selected to upload");
            return "/result";
        }

        User user = userService.getUser(authentication.getName());

        Integer userId = user.getUserId();
        System.out.println(userId+" userId");

        if (!fileService.isFilenameAvailable(multipartFile.getOriginalFilename(), userId)) {
            model.addAttribute("errorMessage", "oops ! There is a file with the similar name");
            return "/result";
        }

        try {

            System.out.println("try of filecontroller *****");
            fileService.insert(multipartFile, userId);
            model.addAttribute("successMessage", "Your file is uploaded successfully.");
            System.out.println("try of filecontroller2 *******");

            return "/result";
        } catch (Exception e) {


            System.out.println("catch of filecontroller");
            System.out.println(e.getMessage());
            logmsg.error("Cause: " + e.getCause() + ". Message: " + e.getMessage());
            model.addAttribute("errorMessage", "Error inserting the file. Please try again!");

            return "/result";
        }

    }
    @GetMapping("/delete/{fileId}")
    public String deleteFile(@PathVariable Integer fileId, Model model) {

        try {
            fileService.delete(fileId);
            model.addAttribute("successMessage", "Your file is deleted successfully.");
            return "/result";

        } catch (Exception e) {
            logmsg.error("Cause: " + e.getCause() + ". Message: " + e.getMessage());
            model.addAttribute("errorMessage", "Error deleting the file. Please try again!");

        }


        return "/result";
    }


    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("fileId") Integer fileId) {
       
        HttpHeaders http = new HttpHeaders();


        //file 
        File file = fileService.getFile(fileId);
        http.add(http.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"");
        http.add("Pragma", "zero-cache");
        http.add("Expires", "0");
        http.add("Cache-control", "zero-cache, zero-store, must-revalidate");


        ByteArrayResource res = new ByteArrayResource(file.getFileData());

        ResponseEntity.BodyBuilder ok = ResponseEntity.ok();
        ok.headers(http);
        ResponseEntity<Resource> responseEntity= ok.body(res);
        return responseEntity;
    }

}
