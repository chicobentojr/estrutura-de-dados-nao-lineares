/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telecomunicacao;

import Grafo.Grafo;
import Grafo.Vertice;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
            int d = s.nextInt();

            grafo.inserirAresta(cidades[x - 1], cidades[y - 1], d);
        }

        System.out.println(grafo);
        
        /* 
         * Resolução
         */
        
        List<Vertice> vertices = grafo.vertices();
        vertices.sort((Vertice vi, Vertice vj) -> grafo.grau(vj) - grafo.grau(vi));
        printListVertice("Vertices", grafo,vertices);
        
        int[] emissoras = new int[nCidades];
        
        int i = 1;
        while(!vertices.isEmpty()) {
            Vertice v = vertices.remove(0);
            emissoras[((Cidade)v.getValor()).getIndice()] = i;
            List<Vertice> vNaoAdjacentes = new ArrayList();
            for (Iterator<Vertice> it = vertices.iterator(); it.hasNext();) {
                Vertice w = it.next();
                if (!grafo.eAdjacente(v, w)) {
                    emissoras[((Cidade)w.getValor()).getIndice()] = i;
                    vNaoAdjacentes.add(w);
                }
            }
            printListVertice("Não adjacentes a " + v.getValor(), grafo, vNaoAdjacentes);
            vertices.removeAll(vNaoAdjacentes);
            i++;
        }
        
        /*
         * Apresentação
         */
        
        System.out.println("\n\nSão necessário: " + (i-1) + " emissoras.\n");
        
        ArrayList mapa[] = new ArrayList[i-1];
        
        for (int j = 0; j < mapa.length; j++) {
            mapa[j] = new ArrayList<Cidade>();
        }
        
        for (Iterator<Vertice> it = grafo.vertices().iterator(); it.hasNext();) {
            Vertice v = it.next();
            Cidade c = (Cidade)v.getValor();
            mapa[emissoras[c.getIndice()] - 1].add(c);
        }
        
        for (int j = 0; j < mapa.length; j++) {
            System.out.println("Emissora " + (j + 1));
            for (Cidade c : (ArrayList<Cidade>)mapa[j]) {
                System.out.println(c.getNome() + " (" + c.getIndice() + ")");
            }
            System.out.println("");
        }
        
    }
    
    private static void printListVertice(String s, Grafo grafo, List<Vertice> l) {
        System.out.print(s + " [");
        for (int i = 0; i < l.size(); i++) {
            System.out.print("(" + ((Cidade)l.get(i).getValor()).getIndice() + ", " + grafo.grau(l.get(i)) + ")" + (i < l.size() - 1 ? ", " : ""));
        }
        System.out.println("]");
    }
    
}
