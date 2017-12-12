/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telecomunicacao;

/**
 *
 * @author felipe
 */
public class Cidade {

    private String nome;
    private int indice;

    public Cidade(int indice, String nome) {
        this.indice = indice;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    @Override
    public String toString() {
        return ((Integer)(1 + this.indice)).toString();
    }
}
