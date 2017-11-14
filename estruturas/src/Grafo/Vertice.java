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
public class Vertice {
    private Object valor;
    
    public Vertice(Object valor) {
        //this.id = id;
        this.valor = valor;
    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }
}
