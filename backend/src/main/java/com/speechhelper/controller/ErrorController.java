package com.speechhelper.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController {

	//Handles the default return value for the backend, which is a helpful message displaying that the API is running!
	 @RequestMapping("/")
     @ResponseBody
     public String defaultMessage () {
         return "<h1>Backend Server is Running!</h1>";
     }
}
