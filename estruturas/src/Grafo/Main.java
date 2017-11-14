/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import java.util.Scanner;

/**
 *
 * @author chicobentojr
 */
public class Main {
    
    public static void main(String[] args) {
        
        Grafo g = new Grafo();
        
        System.out.println(g);

        
        Vertice v1 = g.inserirVertice("v1");
        Vertice v2 = g.inserirVertice("v2");
        Vertice v3 = g.inserirVertice("v3");
        Vertice v4 = g.inserirVertice("v4");
        
        System.out.println(g);

        
        Aresta a = g.inserirAresta(v1, v2, null);
        Aresta b = g.inserirAresta(v1, v3, null);
        Aresta c = g.inserirAresta(v2, v4, null);
        Aresta d = g.inserirAresta(v3, v4, null);
                
        System.out.println(g);
        
        g.removeAresta(a);
        
        System.out.println(g);
        
        Aresta e = g.inserirAresta(v2, v3, null);
        
        System.out.println(g);
        
        Aresta f = g.inserirAresta(v1, v4, null);
        
        System.out.println(g);
        
        Aresta h = g.inserirAresta(v1, v1, null);
        
        System.out.println(g);
        
        Vertice v5 = g.inserirVertice("v5");
        
        System.out.println(g);
        
        g.removeVertice(v1);
        
        System.out.println(g);
    }
}
