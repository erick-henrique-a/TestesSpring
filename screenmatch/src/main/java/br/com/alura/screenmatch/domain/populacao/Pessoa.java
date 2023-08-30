package br.com.alura.screenmatch.domain.populacao;

import com.github.javafaker.Faker;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Random;

@Entity
@Table(name = "populacao")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    @Getter
    @Setter
    private String nome;
    @Getter
    @Setter
    private int idade;
    @Getter
    @Setter
    private boolean vivo;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private int ano_morte;
    @Getter
    private String main_lol;
    @Getter
    private String elo;
    @Getter
    @Setter
    private String doenca;



    public Pessoa(String nome, int idade, boolean vivo) {
        this.nome = nome;
        this.idade = idade;
        this.vivo = vivo;
    }

    public Pessoa(String nome, int idade, boolean vivo, String email, String main_lol, String rank) {
        this.nome = nome;
        this.idade = idade;
        this.vivo = vivo;
        this.email = email;
        this.main_lol = main_lol;
        this.elo = rank;
    }

    public Pessoa(String nome, int idade, boolean vivo, String main_lol, String rank) {
        this.nome = nome;
        this.idade = idade;
        this.vivo = vivo;
        this.main_lol = main_lol;
        this.elo = rank;
    }
    public Pessoa(){
        vivo = true;
    }

    public void Morto(Pessoa pessoa, Faker fake){
        this.vivo = false;
        setDoenca(fake.medical().diseaseName());
    }

    public void envelhece(boolean vivo){
        if (vivo){
            this.idade++;
        }
    }

    public void info() {
        System.out.println("Nome: " + this.nome);
        System.out.println("Idade: " + this.idade);
        System.out.println("Está vivo: " + this.vivo +"\n");
    }

    public void cicloDaVida(int limiteAnos, int anoInicio, PopulacaoRepository repository){
        ArrayList<Pessoa> povo = new ArrayList<>();
        Random rand = new Random();
        Faker faker = new Faker(new Locale("pt-BR"));
        Pessoa adao = new Pessoa("Adão",1, true, "adao@dimas.com.br", "Garen", "Challenger");
        adao.setEmail("adao@dimas.com.br");
        repository.save(adao);
        povo.add(adao);
        faker.medical().diseaseName();
        boolean addPessoa = false;
        for (int i = 0; i < limiteAnos; i++) {
            for (Pessoa e: povo) {
                e.envelhece(e.vivo);
                if (e.getIdade() == 18){
                    addPessoa = true;

                }
                if (rand.nextInt(1, 1000/e.getIdade()) == 1 && e.getIdade() > 18 && e.isVivo()){
                    e.Morto(e, faker);
                    e.setAno_morte(anoInicio);}
            }
            if (addPessoa){
                Pessoa nova = new Pessoa(faker.name().name(), 1, true, faker.leagueOfLegends().champion(), faker.leagueOfLegends().rank());
                nova.setEmail(faker.internet().emailAddress(nova.getNome()).replaceAll(" ", "").toLowerCase());
                povo.add(nova);
                repository.save(nova);
                addPessoa = false;
            }
            anoInicio++;
            System.out.println(anoInicio);
        }
        System.out.println("Sem ordenação:");
        System.out.println(povo);
        System.out.println("Com ordenação:");
        Collections.sort(povo, new OrdenaPorAno());
        System.out.println(povo);
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                ", vivo=" + vivo +
                '}';
    }
}

