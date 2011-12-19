/*
 * Universidade Estadual de Maringá
 * Departamento de Informática
 * Introdução à Inteligência Artificial
 * Desenvolvedores: Maicon Pazin, Renan Malavazi, Renato Brito, Willian Oizumi
 */
package br.uem.din.ia.quebraCabeca.estruturaDeDados;

import br.uem.din.ia.quebraCabeca.estruturaDeDados.No;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author Administrator
 */
public class Grafo {
    private HashSet<No> nos;
    
    public Grafo()
    {
        nos = new HashSet<No>();
    }
    
    public HashSet<No> getNos()
    {
        return nos;
    }
    
    public boolean addNo(No no)
    {
        if (nos.contains(no))
            return false;
        
        nos.add(no);
        return true;
    }
}
