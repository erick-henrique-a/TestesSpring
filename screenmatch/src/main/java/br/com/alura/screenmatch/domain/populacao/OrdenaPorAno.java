package br.com.alura.screenmatch.domain.populacao;

import java.util.Comparator;

public class OrdenaPorAno implements Comparator<Pessoa> {
    public int compare(Pessoa a, Pessoa b){
        return a.getAno_morte() - b.getAno_morte();
    }
}
