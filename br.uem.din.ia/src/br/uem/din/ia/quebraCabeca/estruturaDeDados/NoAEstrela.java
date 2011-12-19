/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.din.ia.quebraCabeca.estruturaDeDados;

/**
 *
 * @author Administrator
 */
public class NoAEstrela extends No<NoAEstrela> implements Comparable {
    
    public NoAEstrela() {
        super();
    }
    
    @Override
    public int compareTo(Object o) {
        NoAEstrela comparado = (NoAEstrela)o;        
        return (this.getCusto() - comparado.getCusto());
    }
}
