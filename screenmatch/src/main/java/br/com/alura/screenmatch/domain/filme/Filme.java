package br.com.alura.screenmatch.domain.filme;

import jakarta.persistence.*;

@Entity
@Table (name = "filmes")
public class Filme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer duracao_em_minutos;
    private Integer ano_lancamento;
    private String genero;


    public Filme(DadosCadastroFilmes dados) {
        this.nome = dados.nome();
        this.duracao_em_minutos = dados.duracao();
        this.ano_lancamento = dados.ano();
        this.genero = dados.genero();
    }
    public Filme(){}

    @Override
    public String toString() {
        return "Filmes{" +
                "nome='" + nome + '\'' +
                ", duracao=" + duracao_em_minutos +
                ", ano=" + ano_lancamento +
                ", genero='" + genero + '\'' +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getDuracao_em_minutos() {
        return duracao_em_minutos;
    }

    public void setDuracao_em_minutos(Integer duracao_em_minutos) {
        this.duracao_em_minutos = duracao_em_minutos;
    }

    public Integer getAno_lancamento() {
        return ano_lancamento;
    }

    public void setAno_lancamento(Integer ano_lancamento) {
        this.ano_lancamento = ano_lancamento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void atualizaDados(DadosAlteraFilmes dados) {
        this.nome = dados.nome();
        this.duracao_em_minutos = dados.duracao();
        this.ano_lancamento = dados.ano();
        this.genero = dados.genero();
    }
}
