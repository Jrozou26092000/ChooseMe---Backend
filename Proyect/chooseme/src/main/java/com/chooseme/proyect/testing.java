package com.chooseme.proyect;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // This means that this class is a Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class testing {

  /*@GetMapping(path="/all")
  public @ResponseBody String getAllUsers() {
    // This returns a JSON or XML with the users
    return "Hola Mundo demo all";
  }*/
}