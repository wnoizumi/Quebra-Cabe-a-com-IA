/*
 * Universidade Estadual de Maringá
 * Departamento de Informática
 * Introdução à Inteligência Artificial
 * Desenvolvedores: Maicon Pazin, Renan Malavazi, Renato Brito, Willian Oizumi
 */
package br.uem.din.ia.quebraCabeca;

import br.uem.din.ia.quebraCabeca.estruturaDeDados.*;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Administrator
 */
public class BuscaEmLargura extends Busca<NoProfundidade> {
    
    private final Queue<NoProfundidade> fila = new LinkedList<NoProfundidade>();

    public BuscaEmLargura(IInteracaoUsuario iu, Integer[][] estadoInicial, Integer[][] estadoObjetivo) {                
        interacaoUsuario = iu;
        noInicial = criarNovoNo(estadoInicial);
        noObjetivo = criarNovoNo(estadoObjetivo);
    }       
    
    @Override        
    protected Iterable<NoProfundidade> getFronteira() {
        return fila;
    }
    
    @Override
    public NoProfundidade buscar(){
        enfilerar(noInicial);
        while(!fila.isEmpty()){
            desenharFronteiraEAguardarUsuario();
            NoProfundidade no = desenfilerar();
            no.setVisitado(true);
            if (ehObjetivo(no))
                return no;
            for (NoProfundidade filho : expandirFronteira(no)) {
                if (!filho.foiVisitado() && !fila.contains(filho))
                    enfilerar(filho);
            }
        }
        return null;
    }    
    
    public synchronized void enfilerar(NoProfundidade no){
        fila.add(no);
    }
    
    public synchronized NoProfundidade desenfilerar(){
        return fila.poll();
    }   

    @Override
    protected NoProfundidade getInstanciaNo() {
        return new NoProfundidade();
    }
    
    
}
