package br.com.alura.screenmatch.controller;

import br.com.alura.screenmatch.domain.populacao.OrdenaPorAno;
import br.com.alura.screenmatch.domain.populacao.Pessoa;
import br.com.alura.screenmatch.domain.populacao.PopulacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @DeleteMapping
    @Transactional //inicia uma transação com o banco de dados com o Spring
    public String deletaPopulacao(){
        repository.deleteAll();
        return "redirect:populacao";
    }

}
