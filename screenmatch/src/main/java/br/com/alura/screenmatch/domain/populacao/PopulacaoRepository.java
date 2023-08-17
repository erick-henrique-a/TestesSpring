package br.com.alura.screenmatch.domain.populacao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PopulacaoRepository extends JpaRepository<Pessoa, Long> {
}
