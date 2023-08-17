package br.com.alura.screenmatch.domain.filme;

import org.springframework.data.jpa.repository.JpaRepository;

//repositorio de acesso a dados
public interface FilmeRepository extends JpaRepository<Filme, Long> {
    //jpaRepository recebe a entidade que vai trabalhar e o tipo do ID

}
