/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AVL;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author chicobentojr
 */
public class Arvore {
    
    public static int INSERCAO = 1;
    public static int REMOCAO = -1;
    
    private No raiz;
    private int tamanho;
    
    public No getRaiz()
    {
        return raiz;
    }
    
    public void setRaiz(No raiz)
    {
        this.raiz = raiz;
    }

    public Arvore()
    {
        this.tamanho = 0;
    }

    public boolean EVazia()
    {
        return this.getRaiz() == null;
    }
    
    public No obter(int chave)
    {
        if (this.getRaiz() == null)
        {
            return this.getRaiz();
        }
        return obter(chave, this.getRaiz());
    }

    private No obter(int chave, No no)
    {
        if (no.EExterno())
        {
            return no;
        }
        if (chave < no.getChave())
        {
            return no.getEsquerdo() != null ? this.obter(chave, no.getEsquerdo()) : no;                
        }
        else if (chave == no.getChave())
        {
            return no;
        }
        else if (chave > no.getChave())
        {
            return no.getDireito() != null ? this.obter(chave, no.getDireito()) : no;
        }
        return null;
    }

    public void atualizarFatorBalanceamento(No no, int operacao)
    {
        No pai = no.getPai();
        if (no == null || pai == null) return;
        
        if (operacao == Arvore.INSERCAO) {
            
            if (no.getChave() > pai.getChave()) {
                pai.setFatorBalanceamento(pai.getFatorBalanceamento() - 1);
            }
            else  {
                pai.setFatorBalanceamento(pai.getFatorBalanceamento() + 1);
            }
            
            if (pai.getFatorBalanceamento() != 0) {
                if (pai.getFatorBalanceamento() > 1 || pai.getFatorBalanceamento() < -1) {
                    balancearArvore(pai);
                } else {
                    atualizarFatorBalanceamento(pai, operacao);
                }
            } 
        } else if (operacao == Arvore.REMOCAO) {
            if (no.getChave() > pai.getChave()) {
                pai.setFatorBalanceamento(pai.getFatorBalanceamento() + 1);
            }
            else  {
                pai.setFatorBalanceamento(pai.getFatorBalanceamento() - 1);
            }
            
            if ((pai.getFatorBalanceamento() == -2) || pai.getFatorBalanceamento() == 2 ) {
                balancearArvore(pai);
                atualizarFatorBalanceamento(pai.getPai(), operacao);
            } else if (pai.getFatorBalanceamento() == 0) {
                atualizarFatorBalanceamento(pai, operacao);
            }
        }
    }

    public void rotacaoEsquerdaSimples(int chave)
    {
        No no = this.obter(chave);
        if (no != null) rotacaoEsquerdaSimples(no);
    }
    
    private No rotacaoEsquerdaSimples(No no)
    {
        No direito = no.getDireito();
        No pai = no.getPai();
        No novoDireito = direito.getEsquerdo();
        
        if (pai != null){
            if (no.getChave() > pai.getChave())
                pai.setDireito(direito);
            else
                pai.setEsquerdo(direito);
        } else {
            this.setRaiz(direito);
        }
        direito.setPai(pai);
        direito.setEsquerdo(no);
        
        no.setPai(direito);
        no.setDireito(novoDireito);

        if (novoDireito != null) {
            novoDireito.setPai(no);
        }
        
        no.setFatorBalanceamento(no.getFatorBalanceamento() + 1 - Math.min(direito.getFatorBalanceamento(), 0));
        direito.setFatorBalanceamento(direito.getFatorBalanceamento() + 1 + Math.max(no.getFatorBalanceamento(), 0));

        return no;
        
    }
    
    public void rotacaoDireitaSimples(int chave)
    {
        No no = this.obter(chave);
        if (no != null) rotacaoDireitaSimples(no);
    }
    
    private No rotacaoDireitaSimples(No no)
    {
        No esquerdo = no.getEsquerdo();
        No pai = no.getPai();
        No novoEsquerdo = esquerdo.getDireito();
        
        if (pai != null){
            if (no.getChave() > pai.getChave())
                pai.setDireito(esquerdo);
            else
                pai.setEsquerdo(esquerdo);
        } else {
            this.setRaiz(esquerdo);
        }
        esquerdo.setPai(pai);
        esquerdo.setDireito(no);
        
        no.setPai(esquerdo);
        no.setEsquerdo(novoEsquerdo);

        if (novoEsquerdo != null) {
            novoEsquerdo.setPai(no);
        }
        
        no.setFatorBalanceamento(no.getFatorBalanceamento() - 1 - Math.max(esquerdo.getFatorBalanceamento(), 0));
        esquerdo.setFatorBalanceamento(esquerdo.getFatorBalanceamento() - 1 + Math.min(no.getFatorBalanceamento(), 0));

        return no;
    }
    
    public void rotacaoEsquerdaDupla(int chave)
    {
        No no = this.obter(chave);
        if (no != null) rotacaoEsquerdaDupla(no);
    }
    
    private No rotacaoEsquerdaDupla(No no)
    {   
        No b = no.getDireito();
        
        this.rotacaoDireitaSimples(b);
        this.rotacaoEsquerdaSimples(no);
        
        return no;
    }
    
    public void rotacaoDireitaDupla(int chave)
    {
        No no = this.obter(chave);
        if (no != null) rotacaoDireitaDupla(no);
    }
    
    private No rotacaoDireitaDupla(No no)
    {
        No b = no.getEsquerdo();
        
        this.rotacaoEsquerdaSimples(b);
        this.rotacaoDireitaSimples(no);
        
        return no;
    }
    
    private void balancearArvore(No no)
    {
        int fator = no.getFatorBalanceamento();
        
        switch (fator) {
            case 2:
                if (no.getEsquerdo() != null && no.getEsquerdo().getFatorBalanceamento() < 0) {
                    this.rotacaoDireitaDupla(no);
                } else {
                    this.rotacaoDireitaSimples(no);
                }
                break;
            case -2:
                if (no.getDireito()!= null && no.getDireito().getFatorBalanceamento() > 0) {
                    this.rotacaoEsquerdaDupla(no);
                } else {
                    this.rotacaoEsquerdaSimples(no);
                }
                break;
                    
        }
    }
    
    public void inserir(int chave)
    {
        this.inserir(chave, null);
    }
    
    public void inserir(int chave, Object valor)
    {
        this.tamanho++;
        No novoNo = new No(chave, valor);

        if (this.getRaiz() == null)
        {
            this.setRaiz(novoNo);
            return;
        }

        No no = this.obter(chave);

        if (no.getChave() != chave)
        {
            novoNo.setPai(no);

            if (chave > no.getChave())
            {
                no.setDireito(novoNo);
            }
            else
            {
                no.setEsquerdo(novoNo);
            }
            atualizarFatorBalanceamento(novoNo, INSERCAO);
        }
        else
        {
            no.setValor(valor);
        }
    }
    
    public No remover(int chave)
    {
        if (this.getRaiz() == null)
        {
            return null;
        }
        return this.remover(chave, this.getRaiz());
    }

    public No remover(int chave, No no)
    {
        if (no == null)
        {
            return null;
        }
        if (chave < no.getChave())
        {
            return this.remover(chave, no.getEsquerdo());
        }
        else if (chave > no.getChave())
        {
            return remover(chave, no.getDireito());
        }
        else 
        {
            if (no.getEsquerdo() != null && no.getDireito() != null)
            {
                No retorno = no;
                No novo = no.getDireito();

                while (novo.getEsquerdo() != null && no.getDireito() != null)
                {
                    novo = novo.getEsquerdo();
                }
                novo = remover(novo.getChave(), no);
                no.setChave(novo.getChave());
                no.setValor(novo.getValor());
                
                return retorno;
            }
            else if (no.getEsquerdo() != null)
            {
                No removido = no;
                atualizarFatorBalanceamento(no, REMOCAO);

                if (no.getPai() == null)
                {
                    this.setRaiz(no.getEsquerdo());
                }
                else
                {
                    if (no.getPai().getChave() < no.getChave())
                    {
                        no.getPai().setDireito(no.getEsquerdo());
                    }
                    else
                    {
                        no.getPai().setEsquerdo(no.getEsquerdo());
                    }
                }
                no.getEsquerdo().setPai(no.getPai());

                return removido;
            }
            else if (no.getDireito() != null)
            {
                No removido = no;
                atualizarFatorBalanceamento(no, REMOCAO);
                if (no.getPai() == null)
                {
                    this.setRaiz(no.getDireito());
                }
                else
                {
                    if (no.getPai().getChave() < no.getChave())
                    {
                        no.getPai().setDireito(no.getDireito());
                    }
                    else
                    {
                        no.getPai().setEsquerdo(no.getDireito());
                    }
                }
                no.getDireito().setPai(no.getPai());

                return removido;
            }
            else
            {
                No pai = no.getPai();

                if (pai == null)
                {
                    this.setRaiz(null);
                    return no;
                }
                atualizarFatorBalanceamento(no, REMOCAO);
                no.setPai(null);
                if (no.getChave() > pai.getChave())
                {
                    pai.setDireito(null);
                }
                else
                {
                    pai.setEsquerdo(null);
                }
                return no;
            }
        }
    }

    public List<No> getFilhos()
    {
        List<No> filhos = new ArrayList<No>();

        this.getFilhos(filhos, this.getRaiz());

        return filhos;
    }

    private void getFilhos(List<No> filhos, No no)
    {
        if (no != null)
        {
            if (no.EInterno())
            {
                this.getFilhos(filhos, no.getEsquerdo());
            }
            filhos.add(no);
            if (no.EInterno())
            {
                this.getFilhos(filhos, no.getDireito());
            }
        }
    }

    public void emOrdem()
    {
        this.emOrdem(this.getRaiz());
    }

    private void emOrdem(No no)
    {
        if (no != null)
        {
            if (no.EInterno())
            {
                emOrdem(no.getEsquerdo());
            }
            
            System.out.printf("[%03d] - [%s] - Profundidade = [%d] - Altura = [%d]\n", 
                    no.getChave(), no.getValor(), no.getProfundidade(), no.getAltura());
            
            if (no.EInterno())
            {
                emOrdem(no.getDireito());
            }
        }
    }

    public void posOrdem()
    {
        posOrdem(this.getRaiz());
    }

    private void posOrdem(No no)
    {
        if (no != null)
        {
            if (no.EInterno())
            {
                posOrdem(no.getDireito());
            }
            
            System.out.printf("[%03d] - [%s] - Profundidade = [%d] - Altura = [%d]\n",
                    no.getChave(), no.getValor(), no.getProfundidade(), no.getAltura());
            
            if (no.EInterno())
            {
                posOrdem(no.getEsquerdo());
            }
        }
    }

    public int getAltura()
    {
        return this.getRaiz() != null ? this.getRaiz().getAltura() : 0;
    }

    public int getProofundidade()
    {
        return this.getRaiz() != null ? this.getRaiz().getProfundidade() : 0;
    }

    public void mostrar()
    {
        if (this.getRaiz() == null)
        {
            System.out.println("A árvore está vazia!");
        }

        for (int i = 0; i <= this.getAltura(); i++)
        {
            for (int j = 0; j <= this.getFilhos().size() + 1; j++)
            {
                boolean ok = false;
                int index = 0;

                for (No filho : this.getFilhos())
                {
                    if (filho.getProfundidade() == i && index + 1 == j)
                    {
                        System.out.printf("%03d[%d]", filho.getChave(), filho.getFatorBalanceamento());
                        ok = true;
                        break;
                    }
                    index++;
                }

                if (ok) continue;

                System.out.print("------");
            }
            System.out.println();
        }
        System.out.println();
    }
}
