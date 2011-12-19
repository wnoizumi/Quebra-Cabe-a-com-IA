/*
 * Universidade Estadual de Maringá
 * Departamento de Informática
 * Introdução à Inteligência Artificial
 * Desenvolvedores: Maicon Pazin, Renan Malavazi, Renato Brito, Willian Oizumi
 */
package br.uem.din.ia.quebraCabeca;

import br.uem.din.ia.quebraCabeca.estruturaDeDados.*;
import java.util.HashMap;
import java.util.Hashtable;

/**
 *
 * @author Administrator
 */
public abstract class Busca<T extends No> implements Runnable {

    protected T noInicial;
    protected T noObjetivo;
    protected IInteracaoUsuario interacaoUsuario;
    protected int id = 1;
    protected final HashMap<Integer, T> nosGerados = new HashMap<Integer, T>();
    
    protected abstract Iterable<T> getFronteira();
    protected abstract T getInstanciaNo();
    protected abstract T buscar();

    @Override
    public void run() {
        T resultado = buscar(); 
        imprimirSolucao(resultado);
    }
    
    protected boolean ehObjetivo(T no){
        if (no.equals(noObjetivo))
            return true;
        return false;
    }
    
    protected Iterable<T> expandirFronteira(T no) {
        gerarAdjacentes(no);
        return no.getNosAdjacentes();
    }

    protected void gerarAdjacentes(T no) {

        Integer[][] estado = no.getEstado();
        fim:
        {
            for (int linha = 0; linha < estado.length; linha++) {
                for (int coluna = 0; coluna < estado[linha].length; coluna++) {
                    if (estado[linha][coluna] == null) {
                        gerarAdjacenteBaixo(linha, coluna, no);
                        gerarAdjacenteCima(linha, coluna, no);
                        gerarAdjacenteEsquerda(coluna, linha, no);
                        gerarAdjacenteDireita(coluna, linha, no);
                        break fim;
                    }
                }
            }
        }
    }

    protected void gerarAdjacenteDireita(int coluna, int linha, T caminho) {
        Integer[][] estado = caminho.getEstado();
        if ((coluna + 1) < estado[linha].length) {
            Integer[][] direita = clonarEstado(estado);
            direita[linha][coluna + 1] = estado[linha][coluna];
            direita[linha][coluna] = estado[linha][coluna + 1];

            criarNovoNo(direita, caminho);
        }
    }

    protected void gerarAdjacenteEsquerda(int coluna, int linha, T caminho) {
        Integer[][] estado = caminho.getEstado();
        if ((coluna - 1) > -1) {
            Integer[][] esquerda = clonarEstado(estado);
            esquerda[linha][coluna - 1] = estado[linha][coluna];
            esquerda[linha][coluna] = estado[linha][coluna - 1];

            criarNovoNo(esquerda, caminho);
        }
    }

    protected void gerarAdjacenteCima(int linha, int coluna, T caminho) {
        Integer[][] estado = caminho.getEstado();
        if ((linha - 1) > -1) {
            Integer[][] cima = clonarEstado(estado);
            cima[linha - 1][coluna] = estado[linha][coluna];
            cima[linha][coluna] = estado[linha - 1][coluna];

            criarNovoNo(cima, caminho);
        }
    }

    protected void gerarAdjacenteBaixo(int linha, int coluna, T caminho) {
        Integer[][] estado = caminho.getEstado();
        if ((linha + 1) < estado.length) {
            Integer[][] baixo = clonarEstado(estado);
            baixo[linha + 1][coluna] = estado[linha][coluna];
            baixo[linha][coluna] = estado[linha + 1][coluna];

            criarNovoNo(baixo, caminho);
        }
    }
        
    protected void criarNovoNo(Integer[][] estado, T caminho) {
        T novoNo = criarNovoNo(estado);
        if (novoNo.getCaminho() == null && !novoNo.foiVisitado()) {
            novoNo.setCaminho(caminho);
        } else {
            novoNo.addNoAdjacente(caminho);
        }
    }

    protected T criarNovoNo(Integer[][] estado) {
        
        T novoNo = getInstanciaNo();        
        novoNo.setEstado(estado);
        if (!nosGerados.containsKey(novoNo.hashCode())) {
            novoNo.setId(id++);
            nosGerados.put(novoNo.hashCode(), novoNo);
            imprimirEstado(novoNo);
            return novoNo;
        } else {
            return nosGerados.get(novoNo.hashCode());
        }
    }

    protected Integer[][] clonarEstado(Integer[][] estado) {
        Integer[][] clone = new Integer[estado.length][estado[0].length];

        for (int linha = 0; linha < estado.length; linha++) {
            System.arraycopy(estado[linha], 0, clone[linha], 0, estado[linha].length);
        }
        return clone;
    }

    protected void imprimirSolucao(T resultado) {
        if (interacaoUsuario != null)
            interacaoUsuario.imprimirSolucao(resultado);
    }
 
    protected void imprimirEstado(T novoNo) {
        if (interacaoUsuario != null) {
            interacaoUsuario.imprimirEstado(novoNo);
        }
    }
    
    protected void desenharFronteiraEAguardarUsuario() {
        if (interacaoUsuario != null) {
            interacaoUsuario.desenharFronteira(getFronteira());
            while (interacaoUsuario.aguardarUsuario()) {}
        }
    }
}
