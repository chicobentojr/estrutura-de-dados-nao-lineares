package Labirinto;

import Grafo.Grafo;
import Grafo.Vertice;
import java.util.ArrayList;
import java.util.List;



public class Main {
    
    private static final int INFINITY = 9999;
    
    private static String[][] getLabirinto(String data) throws InvalidMaze {
        String rows[] = data.split("\n");
        if (rows.length < 1) throw new InvalidMaze();
        
        String maze[][] = new String[rows.length][rows[0].length()];

        for (int i = 0; i < rows.length; i++) {
            maze[i] = rows[i].split("");
        }
        
        return maze;
    }

    public static void main(String[] args) throws InvalidMaze {
        
        String dat = LeitorDeArquivo.lerArquivo("/home/felipe/Workspaces/IFRN/ednl/estruturas/src/Labirinto/labirinto.dat");
        String labirintoRaw[][] = getLabirinto(dat);
        Vertice labirintoVertice[][] = new Vertice[labirintoRaw.length][labirintoRaw[0].length];
        Grafo labirinto = new Grafo();
        int indiceVertice = 1;
        Vertice inicio = null;
        
        for (int i = 0; i < labirintoRaw.length; i++) {
            for (int j = 0; j < labirintoRaw[i].length; j++) {
                if (labirintoRaw[i][j].equals("1")) continue;
                labirintoVertice[i][j] = labirinto.inserirVertice(new Ponto(indiceVertice++, labirintoRaw[i][j]));
                if (labirintoRaw[i][j].equals("2")) {
                    inicio = labirintoVertice[i][j]; 
                }
                if (i > 0 && !labirintoRaw[i-1][j].equals("1")) {
                    System.out.println("Aresta entre: " + labirintoVertice[i][j].getValor().toString() +" <-> " + labirintoVertice[i-1][j].getValor().toString());
                    labirinto.inserirAresta(labirintoVertice[i][j], labirintoVertice[i-1][j], 1);
                }
                if (j > 0 && !labirintoRaw[i][j-1].equals("1")) {
                    System.out.println("Aresta entre: " + labirintoVertice[i][j].getValor().toString() +" <-> " + labirintoVertice[i][j-1].getValor().toString() );
                    labirinto.inserirAresta(labirintoVertice[i][j], labirintoVertice[i][j-1], 1);
                }
            }
        }
        
        System.out.println(labirinto);
        System.out.println(inicio.getValor());
        
        ArrayList<Vertice> vertices = labirinto.vertices();
        ArrayList<Vertice> percorridos = new ArrayList<Vertice>();
        ArrayList<Vertice> naoPercorridos = labirinto.vertices();
        
        percorridos.add(inicio);
        vertices.remove(inicio);
        naoPercorridos.remove(inicio);
        
        int[] distancias = new int[vertices.size() + 2];
        Vertice[] antecessor = new Vertice[vertices.size() + 2];
        distancias[index(inicio)] = distancia(labirinto, inicio, inicio);
        
        for (Vertice v : vertices) {
            distancias[index(v)] = distancia(labirinto, inicio, v);
        }
        
        Vertice w = inicio;
        
        System.out.println("Tamanho percorridos: "+ percorridos.size());
        System.out.println("Tamanho vertices: " + vertices.size());

        while(percorridos.size() != labirinto.vertices().size()) {
            if (w != null) System.out.println("Ultimo w: " + ((Ponto)w.getValor()).getChave());
            
            w = maisProximo(naoPercorridos, distancias);
            
            if (w != null) System.out.println("Novo w: " + ((Ponto)w.getValor()).getChave() + " (" +distancias[index(w)]+ ")");
            
//            if (antecessor[index(w)] != null && !percorridos.contains(antecessor[index(w)])) {
//                percorridos.add(antecessor[index(w)]);
//                naoPercorridos.remove(antecessor[index(w)]);
//            }
            
            if (distancias[index(w)] >= INFINITY) break;

            percorridos.add(w);
            naoPercorridos.remove(w);
            
            if (((Ponto)w.getValor()).getValor().equals("3")) {
                System.out.println("Parou no: " + w.getValor());
                break;
            }
            
            for (Vertice v : naoPercorridos) {
                if (distancias[index(w)] + distancia(labirinto, w, v) < distancias[index(v)]) {
                    antecessor[index(v)] = w;
                }
                distancias[index(v)] = Math.min(distancias[index(v)], distancias[index(w)] + distancia(labirinto, w, v));
            }            
        }
        
        System.out.println("Tamanho percorridos: "+ percorridos.size());
        System.out.println("Tamanho vertices: " + vertices.size());
        System.out.println("");
        
        System.out.println("Labirinto:");
        for (int i = 0; i < labirintoVertice.length; i++) {
            for (int j = 0; j < labirintoVertice[i].length; j++) {
                Vertice v = labirintoVertice[i][j];
                String str = "░░░░";
                if (v != null) {
                    String borda = " ";
                    if (((Ponto)v.getValor()).getValor().equals("3")) {
                        borda = "↗";
                    } else if (((Ponto)v.getValor()).getValor().equals("2")) {
                        borda = "↙";
                    }
                    str = borda + String.format("%02d", ((Ponto)v.getValor()).getChave()) + borda;
                }
                System.out.print(str);
            }
            System.out.println("");
        }
        
        System.out.println("");
        
        System.out.println("Antecessores: ");
        for (Vertice v : percorridos) {
            if (antecessor[index(v)] == null) continue;
            System.out.print(antecessor[index(v)].getValor());
            System.out.print(" -> ");
            System.out.print(v.getValor());
            System.out.println("");
        }
        
        System.out.println("Percorridos: ");
        for (Vertice v : percorridos) {
            System.out.print(v.getValor());
            System.out.print(" ");
        }
        
        System.out.println("");
        
        if (w != null && ((Ponto)w.getValor()).getValor().equals("3")) {
            System.out.println("Conseguiu.");
            System.out.print("Caminho: ");
            String caminho = "";
            int i = percorridos.size() - 1;
            Vertice v = percorridos.get(i);
            Vertice vAntecessor = antecessor[index(v)];
            caminho = index(v) + caminho;
            
            while (vAntecessor != null) {
                i = percorridos.indexOf(vAntecessor);
                v = vAntecessor;
                vAntecessor = antecessor[index(v)];
                caminho = index(v) + "-> " + caminho;
            }
            
            caminho = index(percorridos.get(0)) + "-> " + caminho;
            
            System.out.println(caminho);
        } else {
            System.out.println("Não conseguiu.");
        }
        
    }
    
    private static Vertice maisProximo(List<Vertice> vertices, int[] distancias) {
        Vertice w = vertices.get(0);
        int minDistancia = distancias[index(w)];
        for (Vertice v : vertices) {
            if (distancias[index(v)] < minDistancia) {
                System.out.println("D[v] < D[w], " + ((Ponto)v.getValor()).getChave() + " < " + ((Ponto)w.getValor()).getChave() + ", " + distancias[index(v)] + " < " + minDistancia);
                w = v;
                minDistancia = distancias[index(v)];
            }
        }
        return w;
    }
    
    private static int index(Vertice v) {
        return Integer.parseInt(v.getValor().toString());
    }
    
    private static int distancia(Grafo g, Vertice a, Vertice b) {
        if (g.eAdjacente(a, b)) {
            return 1;
        }
        
        if (a.equals(b)) {
            return 0;
        }
        
        return INFINITY;
    }
}
