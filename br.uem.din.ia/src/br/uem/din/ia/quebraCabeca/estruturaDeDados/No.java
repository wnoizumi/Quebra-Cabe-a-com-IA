/*
 * Universidade Estadual de Maringá
 * Departamento de Informática
 * Introdução à Inteligência Artificial
 * Desenvolvedores: Maicon Pazin, Renan Malavazi, Renato Brito, Willian Oizumi
 */
package br.uem.din.ia.quebraCabeca.estruturaDeDados;

import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author Administrator
 */
public abstract class No<T extends No> {

    protected boolean visitado;
    protected HashSet<T> nosAdjacentes;
    protected int id;
    protected Integer estado[][];
    protected T caminho;
    protected int custo;

    public No() {
        visitado = false;
        nosAdjacentes = new HashSet<T>();
    }

    public boolean foiVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    public HashSet<T> getNosAdjacentes() {
        return nosAdjacentes;
    }

    public void addNoAdjacente(T no) {
        if (!nosAdjacentes.contains(no)) {
            nosAdjacentes.add(no);
            no.addNoAdjacente(this);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEstado(Integer[][] estado) {
        this.estado = estado;
    }

    public Integer[][] getEstado() {
        return estado;
    }

    public No getCaminho() {
        return caminho;
    }

    public void setCaminho(T caminho) {
        this.caminho = caminho;
        this.addNoAdjacente(caminho);
    }
    
    public int getCusto(){
        return custo;
    }
    
    public void setCusto(int custo){
        this.custo = custo;
    }

    @Override
    public int hashCode() {
        if (estado == null) {
            return 0;
        }

        StringBuilder str = new StringBuilder();
        for (int linha = 0; linha < estado.length; linha++) {
            for (int coluna = 0; coluna < estado[linha].length; coluna++) {
                if (estado[linha][coluna] == null) {
                    str.append(0);
                } else {
                    str.append(estado[linha][coluna]);
                }
            }
        }
        return Integer.parseInt(str.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()) {
            return false;
        }

        Integer[][] comparado = ((No) obj).getEstado();

        for (int linha = 0; linha < estado.length; linha++) {
            for (int coluna = 0; coluna < estado[linha].length; coluna++) {

                if (comparado[linha][coluna] == null && estado[linha][coluna] == null) {
                    continue;
                }

                if ((comparado[linha][coluna] == null && estado[linha][coluna] != null)
                        || (estado[linha][coluna] == null && comparado[linha][coluna] != null)) {
                    return false;
                }

                if (estado[linha][coluna].compareTo(comparado[linha][coluna]) != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        if (estado != null) {
            StringBuilder str = new StringBuilder();
            for (int linha = 0; linha < estado.length; linha++) {
                for (int coluna = 0; coluna < estado[linha].length; coluna++) {
                    if (coluna > 0) {
                        str.append("| ");
                    }
                    if (estado[linha][coluna] != null) {
                        str.append(estado[linha][coluna].toString());
                    } else {
                        str.append("  ");
                    }
                }
                str.append("\n");
            }
            return str.toString();
        }

        return super.toString();
    }
}
