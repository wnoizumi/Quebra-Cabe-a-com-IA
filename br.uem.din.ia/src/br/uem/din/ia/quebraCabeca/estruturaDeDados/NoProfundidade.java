/*
 * Universidade Estadual de Maringá
 * Departamento de Informática
 * Introdução à Inteligência Artificial
 * Desenvolvedores: Maicon Pazin, Renan Malavazi, Renato Brito, Willian Oizumi
 */
package br.uem.din.ia.quebraCabeca.estruturaDeDados;

/**
 *
 * @author Administrator
 */
public class NoProfundidade extends No<NoProfundidade> {
    public NoProfundidade(){
        super();
    }
    
    @Override
    public int getCusto(){
        return 1;
    }
}
