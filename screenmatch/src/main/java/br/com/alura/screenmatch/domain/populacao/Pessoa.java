package br.com.alura.screenmatch.domain.populacao;

import com.github.javafaker.Faker;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Locale;

@Entity
@Table(name = "populacao")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private int idade;
    private final boolean vivo;
    private String owMain;


    public Pessoa(String nome, int idade, boolean vivo, String owMain) {
        this.nome = nome;
        this.idade = idade;
        this.vivo = true;
        this.owMain = owMain;
    }

    public Pessoa(String nome, int idade, boolean vivo) {
        this.nome = nome;
        this.idade = idade;
        this.vivo = vivo;
    }

    public Pessoa(){
        vivo = true;
    }




    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void envelhece(){
        this.idade++;
    }

    public void info() {
        System.out.println("Nome: " + this.nome);
        System.out.println("Idade: " + this.idade);
        System.out.println("Está vivo: " + this.vivo +"\n");
    }


/// Tentativa com Recursividade
////    public void cicloDaVida(Pessoa original, ArrayList pessoas, int r, int quantidadeDePessoas){
////        int counter = 0;
////        int anos = 0;
////        int aux = 0;
////
////        Faker nome = new Faker();
////        for (int i = 0; i < r; i++) {
////            original.envelhece();
////            if (i == 18 && counter < quantidadeDePessoas)
////            {
////                //pessoas[j].info;
////                Pessoa p = new Pessoa(nome.name().name());
////
////                System.out.println("Nova pessoa gerada");
////                pessoas.add(p);
////                p.info();
////                counter++;
////                if (pessoas.size() > quantidadeDePessoas){
////                    break;
////                } else{
////                    cicloDaVida(p,pessoas, r, quantidadeDePessoas);
////                }
//
////                switch (anos){
////                    case 20:
////                        break;
////                    case
////                }
//            }
//            anos++;
//            //System.out.println("Idade atual do original: " + original.getIdade());
//            original.info();
//            System.out.println("Ano atual: " + anos);
//        }
//    }

    public void cicloDaVida(int limiteAnos, int quantidadeDePessoas, ArrayList<Pessoa> povo, PopulacaoRepository repository){

        Faker faker = new Faker(new Locale("pt-BR"));
        Pessoa adao = new Pessoa("adao",1, true);
        repository.save(adao);
        povo.add(adao);
        boolean addPessoa = false;
        for (int i = 0; i < limiteAnos; i++) {
            for (Pessoa e: povo) {
                e.envelhece();
                if (e.getIdade() == 18){
                    addPessoa = true;
                }
            }
            if (addPessoa){
                Pessoa nova = new Pessoa(faker.name().name(), 1, true, faker.overwatch().hero());
                povo.add(nova);

                System.out.println(nova.toString() + "batata");
                addPessoa = false;
            }

        }
        System.out.println(povo);
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                ", vivo=" + vivo +
                ", owMain='" + owMain + '\'' +
                '}';
    }
}

