/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

/**
 *
 * @author chicobentojr
 */
public class Aresta {
    private Vertice inicio;
    private Vertice fim;
    private Object valor;
    
    public Aresta(Vertice a, Vertice b, Object valor) {
        this.inicio = a;
        this.fim = b;
        this.valor = valor;
    }

    public Vertice getInicio() {
        return inicio;
    }

    public void setInicio(Vertice inicio) {
        this.inicio = inicio;
    }

    public Vertice getFim() {
        return fim;
    }

    public void setFim(Vertice fim) {
        this.fim = fim;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }
}
