package br.com.alura.screenmatch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping ("/home")
public class IndexController {
    @GetMapping
    public String carregaIndex(){
        return "home/index";
    }

    @GetMapping("boot")
    public String carregaBoot(){
        return "home/boot";
    }

    @GetMapping("java")
    public String carregaJava(){
        return "home/javas";
    }

    @GetMapping("ingles")
    public String carregaIngles(){
        return "home/ingles";
    }

    @GetMapping("c#")
    public String carregaC(){
        return "home/cun";
    }
}
