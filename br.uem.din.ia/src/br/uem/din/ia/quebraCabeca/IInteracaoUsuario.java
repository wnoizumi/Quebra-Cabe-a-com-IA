/*
 * Universidade Estadual de Maringá
 * Departamento de Informática
 * Introdução à Inteligência Artificial
 * Desenvolvedores: Maicon Pazin, Renan Malavazi, Renato Brito, Willian Oizumi
 */
package br.uem.din.ia.quebraCabeca;

import br.uem.din.ia.quebraCabeca.estruturaDeDados.No;

/**
 *
 * @author Administrator
 */
public interface IInteracaoUsuario {
    public boolean aguardarUsuario();
    public void imprimirEstado(No no);   
    public <T extends No> void desenharFronteira(Iterable<T> fronteira);
    public void imprimirSolucao(No no);
}
