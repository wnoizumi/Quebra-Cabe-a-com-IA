/*
 * Universidade Estadual de Maringá
 * Departamento de Informática
 * Introdução à Inteligência Artificial
 * Desenvolvedores: Maicon Pazin, Renan Malavazi, Renato Brito, Willian Oizumi
 */
package br.uem.din.ia.quebraCabeca;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Administrator
 */
public class BuscaEmProfundidadeTeste {
    
    public static BuscaEmProfundidade busca;
    
    public BuscaEmProfundidadeTeste() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {        
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

     @Test
     public void hello() {
         Integer[][] estadoInicial ={{1, 2, 3}, {4, 5, 6}, {7, 8, null}};  
         Integer[][] estadoObjetivo ={{1, 2, 3}, {4, 5, 6}, {7, null, 8}};  
         //Integer[][] estadoObjetivo ={{null, 1, 2}, {3, 4, 5}, {6, 7, 8}};  
         
         busca = new BuscaEmProfundidade(null, estadoInicial, estadoObjetivo);
         busca.run();
     }
}
