package br.com.alura.screenmatch.controller;

import br.com.alura.screenmatch.domain.populacao.Pessoa;
import br.com.alura.screenmatch.domain.populacao.PopulacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
    public String geraPopulacao(){
        Pessoa pessoas = new Pessoa();
        int limiteDeAnos = 36;
        int qtdPessoas = 40;
        ArrayList<Pessoa> povo = new ArrayList<>();
        pessoas.cicloDaVida(limiteDeAnos, qtdPessoas, povo, repository);
        return "redirect:/populacao";
    }

}
