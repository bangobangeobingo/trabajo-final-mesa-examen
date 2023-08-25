package ar.edu.unju.edm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrincipalController {
       @GetMapping({"/","index","home",""})
       public String  getIndex() {
    	   return "index";
       }
       @GetMapping({"index2"})
       public String  getIndex2() {
    	   return "index2";
       }
       @GetMapping({"/login","ingreso"})
       public String  getLogin() {
    	   return "login";
       }
}
