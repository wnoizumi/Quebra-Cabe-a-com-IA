/*
 * Universidade Estadual de Maringá
 * Departamento de Informática
 * Introdução à Inteligência Artificial
 * Desenvolvedores: Maicon Pazin, Renan Malavazi, Renato Brito, Willian Oizumi
 */
package br.uem.din.ia.quebraCabeca;

import br.uem.din.ia.quebraCabeca.estruturaDeDados.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 *
 * @author Administrator
 */
public class BuscaAEstrela extends Busca<NoAEstrela> {

    private final HashSet<NoAEstrela> conjuntoAberto = new HashSet<NoAEstrela>();
        
    public BuscaAEstrela(IInteracaoUsuario iu, Integer[][] estadoInicial, Integer[][] estadoObjetivo) {                
        interacaoUsuario = iu;
        noInicial = criarNovoNo(estadoInicial);
        noObjetivo = criarNovoNo(estadoObjetivo);
    }
        
    @Override
    public NoAEstrela buscar() {
        definirCustoEAdicionarCjtoAberto(noInicial);
        while(!conjuntoAberto.isEmpty()){
            desenharFronteiraEAguardarUsuario();
            NoAEstrela no = (NoAEstrela)Collections.min(conjuntoAberto);   
            if (ehObjetivo(no))
                return no;
            
            conjuntoAberto.remove(no);
            no.setVisitado(true);
            for (NoAEstrela filho : expandirFronteira(no)) {
                if (filho.foiVisitado())
                    continue;                
                if (!conjuntoAberto.contains(filho)){
                    definirCustoEAdicionarCjtoAberto(filho);
                }
            }
        }
        return null;
    }

    protected void definirCustoEAdicionarCjtoAberto(NoAEstrela filho) {
        filho.setCusto(calcularCustoNo(filho));
        conjuntoAberto.add(filho);
    }
    
    private int calcularCustoNo(NoAEstrela no){
        return blocosPosicoesErradas(no) + distanciaManhattan(no);
    }
    
    private int blocosPosicoesErradas(NoAEstrela no){
        
        int quantidade = 0;
        Integer[][] objetivo = noObjetivo.getEstado();
        Integer[][] estado = no.getEstado();
        for (int linha = 0; linha < estado.length; linha++) {
            for (int coluna = 0; coluna < estado[linha].length; coluna++) {

                if (objetivo[linha][coluna] == null && estado[linha][coluna] == null) {
                    continue;
                }

                if ((objetivo[linha][coluna] == null && estado[linha][coluna] != null)
                        || (estado[linha][coluna] == null && objetivo[linha][coluna] != null)) {
                    quantidade++;
                }
                else if (estado[linha][coluna].compareTo(objetivo[linha][coluna]) != 0) {
                    quantidade++;
                }
            }
        }
        
        return quantidade;
    }

    private int distanciaManhattan(NoAEstrela no){        
        Integer[][] objetivo = noObjetivo.getEstado();
        Integer[][] estado = no.getEstado();
        
        int total = 0;
         for (int linha = 0; linha < estado.length; linha++) {
            for (int coluna = 0; coluna < estado[linha].length; coluna++) {
                total += distanciaManhattan(estado[linha][coluna], linha, coluna, objetivo);
            }
         }
        
        return total;
    }
    
    private int distanciaManhattan(Integer valor, int i, int j, Integer[][] objetivo){
        for (int linha = 0; linha < objetivo.length; linha++) {
            for (int coluna = 0; coluna < objetivo[linha].length; coluna++) {
                
                if ((objetivo[linha][coluna] == null && valor != null)
                        || (valor == null && objetivo[linha][coluna] != null)) {
                    continue;
                }
                
                if (objetivo[linha][coluna] == null && valor == null
                        || objetivo[linha][coluna].compareTo(valor) == 0){
                    return Math.abs(linha - i) + Math.abs(coluna - j);
                }
            }
         }
        return 0;
    }

    @Override
    protected Iterable<NoAEstrela> getFronteira() {
        return conjuntoAberto;
    }

    @Override
    protected NoAEstrela getInstanciaNo() {
        return new NoAEstrela();
    }
}
