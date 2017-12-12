/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import Grafo.Grafo.VerticeNaoEncontrado;
import Labirinto.Ponto;
import java.util.ArrayList;

/**
 *
 * @author chicobentojr
 */
public class Grafo {
    
    private ArrayList<Vertice> vertices = new ArrayList<Vertice>();
    private ArrayList<Aresta> arestas = new ArrayList<Aresta>();
    
    
    public Vertice[] finalVertices(Aresta a) {
        Vertice[] retorno = new Vertice[2];
        
        retorno[0] = a.getInicio();
        retorno[1] = a.getFim();
        
        return retorno;
    }
    
    public Vertice oposto(Vertice v, Aresta a) throws VerticeNaoEncontrado  {
        Vertice retorno = null;
        
        if (a.getInicio().equals(v) || a.getFim().equals(v)) {
            retorno = a.getInicio().equals(v) ? a.getFim() : a.getInicio();
        } else {
            throw new VerticeNaoEncontrado();
        }
        
        return retorno;
    }
    
    public boolean eAdjacente(Vertice v, Vertice w) {
        for (Aresta a : this.arestas) {
            if ((a.getInicio().equals(v) && a.getFim().equals(w)) || 
                (a.getInicio().equals(w) && a.getFim().equals(v))) {
                return true;
            }
        }
        return false;
    }
    
    public int grau(Vertice v) {
        return arestasIncidentes(v).size();
    }
    
    public Object substituir(Vertice v, Object x) {
        v.setValor(x);
        return x;
    }
    
    public Object substituir(Aresta a, Object x) {
        a.setValor(x);
        return x;
    }
    
    public Vertice inserirVertice(Object o) {
        Vertice v = new Vertice(o);
        vertices.add(v);
        return v;
    }
    
    public Aresta inserirAresta(Vertice v, Vertice w, Object o) {
        Aresta a = new Aresta(v, w, o);
        arestas.add(a);
        return a;
    }
    
    public Object removeVertice(Vertice v) {
        
        for (int i = 0; i < arestas.size(); i++) {
            Aresta a = arestas.get(i);
            if (a.getInicio().equals(v) || a.getFim().equals(v)) {
                arestas.remove(a);
                i--;
            }
        }
        vertices.remove(v);
        
        return v.getValor();
    }
    
    public Object removeAresta(Aresta a) {
        arestas.remove(a);
        return a.getValor();
    }
    
    public ArrayList<Aresta> arestasIncidentes(Vertice v) {
        ArrayList<Aresta> retorno = new ArrayList<Aresta>();
        for (Aresta a : arestas) {
            if (a.getInicio().equals(v) || a.getFim().equals(v)) {
                retorno.add(a);
            }
        }
        return retorno;
    }
    
    public ArrayList<Vertice> vertices() {
        return (ArrayList<Vertice>)this.vertices.clone();
    }
    
    public ArrayList<Aresta> arestas() {
        return (ArrayList<Aresta>)this.arestas.clone();
    }
    
    @Override
    public String toString() {
        if (vertices.size() == 0) {
            return "Grafo vazio";
        }
        String retorno = "\nGRAFO:\n\n";
        retorno += "|    |";
        for (Vertice v : vertices) {
            retorno += " " + String.format("%02d", Integer.parseInt(v.getValor().toString())) + " |";
        }
        retorno += "\n";
        
        for (Vertice v : vertices) {
            retorno += "| " + String.format("%02d", Integer.parseInt(v.getValor().toString())) + " |";
            for (Vertice w : vertices) {
                retorno += " " + (this.eAdjacente(v,w) ? "★★" : "  ") + " |";
            }
            retorno += "\n";
        }
        
        return retorno;
    }
    
    
    public class VerticeNaoEncontrado extends Exception {
        public VerticeNaoEncontrado() {
            super("Vertice não encontrado!");
        }
    }
}