package br.com.alura.screenmatch.controller;

import br.com.alura.screenmatch.domain.populacao.OrdenaPorAno;
import br.com.alura.screenmatch.domain.populacao.Pessoa;
import br.com.alura.screenmatch.domain.populacao.PopulacaoRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequestMapping("/populacao")
public class PopulacaoController {
    @Autowired
    private PopulacaoRepository repository;
    @GetMapping
    public String carregaInicio(){
        return "populacao/inicio";
    }

    @PostMapping
    @Transactional
    public String geraPopulacao(int limiteAnos, int anoInicio){
        Pessoa pessoas = new Pessoa();

        pessoas.cicloDaVida(limiteAnos, anoInicio, repository);
        return "redirect:/populacao/tabela";
    }
    @GetMapping("/tabela")
    public String carregaPopulacao(Model model){
        model.addAttribute("lista", repository.findAll());
        return "populacao/inicio";
    }
    @GetMapping("/dados")
    public String carregaDados(Model model){
        model.addAttribute("lista", repository.findAll());
        return "populacao/dados";
    }

    @RequestMapping(value = "dados", method = RequestMethod.POST)
    public String upload(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartRequest.getFile("file");
        File pdf = new File("C:/Users/Fernanda/IdeaProjects/TestesSpring/screenmatch/src/main/resources/file.pdf");
        try {
            multipartFile.transferTo(pdf);
            System.out.println(pdf.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {

            return "redirect:/home";
        }
    }


    @DeleteMapping
    @Transactional //inicia uma transação com o banco de dados com o Spring
    public String deletaPopulacao(){
        repository.deleteAll();
        return "redirect:populacao";
    }

}
