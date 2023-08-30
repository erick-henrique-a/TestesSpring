package br.com.alura.screenmatch.controller;

import br.com.alura.screenmatch.domain.filme.DadosAlteraFilmes;
import br.com.alura.screenmatch.domain.filme.DadosCadastroFilmes;
import br.com.alura.screenmatch.domain.filme.Filme;
import br.com.alura.screenmatch.domain.filme.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/filmes")


public class FilmeController {
    @Autowired //Spring Fica responsavel por achar e instanciar
    private FilmeRepository repository;
    @GetMapping("formulario")
    public String carregaPaginaFormulario(Long id, Model model){
        if(id != null){
            var filme = repository.getReferenceById(id);
            model.addAttribute(filme);
        }
        return "filmes/formulario";
    }

    @GetMapping("listagem")
    public String carregaPaginaListagem(Model model){
        model.addAttribute("lista", repository.findAll());
        return "filmes/listagem";
    }

    @PostMapping
    @Transactional //inicia uma transação com o banco de dados com o Spring

    public String cadastrafilme(DadosCadastroFilmes dados){
        Filme filme = new Filme(dados);
        repository.save(filme);
        return "redirect:/filmes/listagem";
    }
    @PutMapping
    @Transactional //inicia uma transação com o banco de dados com o Spring
    public String alterafilme(DadosAlteraFilmes dados){
        var filme = repository.getReferenceById(dados.id());
        filme.atualizaDados(dados);
        return "redirect:/filmes/listagem";
    }

    @DeleteMapping
    @Transactional //inicia uma transação com o banco de dados com o Spring
    public String deletafilme(Long id){
        repository.deleteById(id);
        return "redirect:filmes/listagem";
    }

}
