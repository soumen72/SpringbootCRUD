package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class EroorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {

        return "404_Page_Not_Found";
    }

    @Override
    public String getErrorPath() {

        return null;
    }
}
