/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telecomunicacao;

import Grafo.Grafo;
import Grafo.Vertice;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author felipe
 */
public class Main {

    public static void main(String[] args) {
        /*
         * Coleta
         */
        
        
        Scanner s = new Scanner(System.in);
        Grafo grafo = new Grafo();

        int nCidades = s.nextInt();
        int nArestas = s.nextInt();
        s.nextLine();

        Vertice cidades[] = new Vertice[nCidades];

        for (int i = 0; i < nCidades; i++) {
            String cidade = s.nextLine();
            cidades[i] = grafo.inserirVertice(new Cidade(i, cidade));
        }

        for (int i = 0; i < nArestas; i++) {
            int x = s.nextInt();
            int y = s.nextInt();

            grafo.inserirAresta(cidades[x - 1], cidades[y - 1], 1);
        }

        System.out.println(grafo);
        
        /* 
         * Resolução
         */
        
        List<Vertice> vertices = grafo.vertices();
        vertices.sort((Vertice vi, Vertice vj) -> grafo.grau(vi) - grafo.grau(vj));
        
        int i = 1;
        while(!vertices.isEmpty()) {
            Vertice v = vertices.remove(0);
            vertices.removeIf((Vertice vk) -> !grafo.eAdjacente(v, vk));
            i++;
        }
        
        System.out.println("São necessário: " + (i-1) + " emissoras.");
    }
}
