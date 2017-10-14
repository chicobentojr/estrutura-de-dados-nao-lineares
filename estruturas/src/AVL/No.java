/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AVL;

/**
 *
 * @author chicobentojr
 */
public class No {
    
    private No pai;
    private No esquerdo;
    private No direito;
    private int chave;
    private Object valor;

    public No getPai() {
        return pai;
    }

    public void setPai(No pai) {
        this.pai = pai;
    }

    public No getEsquerdo() {
        return esquerdo;
    }

    public void setEsquerdo(No esquerdo) {
        this.esquerdo = esquerdo;
    }

    public No getDireito() {
        return direito;
    }

    public void setDireito(No direito) {
        this.direito = direito;
    }

    public int getChave() {
        return chave;
    }

    public void setChave(int chave) {
        this.chave = chave;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public No()
    {

    }

    public No(int chave, Object valor)
    {
        this.chave = chave;
        this.valor = valor;
    }


    public boolean temDireito()
    {
        return this.getDireito() != null;
    }

    public boolean temEsquerdo()
    {
        return this.getEsquerdo() != null;
    }

    public boolean EInterno()
    {
        return this.temDireito()|| this.temEsquerdo();
    }

    public boolean EExterno()
    {
        return !EInterno();
    }

    public int getProfundidade()
    {
        return this.getPai() == null ? 0 : 1 + this.getPai().getProfundidade();
    }

    public int getAltura()
    {
        if (this.EExterno())
        {
            return 0;
        }
        else
        {
            int altura = 0;
            int alturaDireito = 0;
            int alturaEsquerdo = 0;

            if (this.temDireito())
            {
                alturaDireito = this.getDireito().getAltura();
            }
            if (this.temEsquerdo())
            {
                alturaEsquerdo = this.getEsquerdo().getAltura();
            }

            altura = alturaDireito > alturaEsquerdo ? alturaDireito : alturaEsquerdo;
            return altura + 1;
        }
    }
}
