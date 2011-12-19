/*
 * Universidade Estadual de Maringá
 * Departamento de Informática
 * Introdução à Inteligência Artificial
 * Desenvolvedores: Maicon Pazin, Renan Malavazi, Renato Brito, Willian Oizumi
 */
package br.uem.din.ia.quebraCabeca;

import br.uem.din.ia.quebraCabeca.estruturaDeDados.*;
import java.util.Stack;

/**
 *
 * @author Administrator
 */
public class BuscaEmProfundidade extends Busca<NoProfundidade> {
    
    private final Stack<NoProfundidade> pilha = new Stack();

    public BuscaEmProfundidade(IInteracaoUsuario iu, Integer[][] estadoInicial, Integer[][] estadoObjetivo) {                
        interacaoUsuario = iu;
        noInicial = criarNovoNo(estadoInicial);
        noObjetivo = criarNovoNo(estadoObjetivo);
    }       
    
    @Override        
    protected Iterable<NoProfundidade> getFronteira() {
        return pilha;
    }
    
    @Override
    public NoProfundidade buscar(){
        empilhar(noInicial);
        while(!pilha.isEmpty()){
            desenharFronteiraEAguardarUsuario();
            NoProfundidade no = desempilhar();
            no.setVisitado(true);
            if (ehObjetivo(no))
                return no;
            for (NoProfundidade filho : expandirFronteira(no)) {
                if (!filho.foiVisitado() && !pilha.contains(filho))
                    empilhar(filho);
            }
        }
        return null;
    }    
    
    public synchronized void empilhar(NoProfundidade no){
        pilha.push(no);
    }
    
    public synchronized NoProfundidade desempilhar(){
        if (!pilha.isEmpty())
            return pilha.pop();
            return null;
    }   

    @Override
    protected NoProfundidade getInstanciaNo() {
        return new NoProfundidade();
    }
    
    
}
