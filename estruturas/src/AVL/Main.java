/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AVL;

import java.util.Scanner;

/**
 *
 * @author chicobentojr
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        Arvore arvore = new Arvore();
        
//        int[] inicio = { 40, 30, 60, 10, 35, 70, 5, 12, 3};
        int[] inicio = { 8, 6, 20, 2, 7, 11, 29, 3, 10, 12, 24, 32, 9, 22, 31 };
        
        for (int i = 0; i < inicio.length; i++) {
            arvore.inserir(inicio[i]);
        }
        

        int comando = -1, chave;
        String valor;

        do
        { 
            
            
            System.out.println(arvore);

            System.out.println("1 - Inserir <chave> <valor>;");
            System.out.println("2 - Remover <chave>;");
            System.out.println("3 - Em Ordem;");
            System.out.println("4 - Pós Ordem;");
            System.out.println("5 - Limpar Árvore;");
            System.out.println("6 - Obter <chave>;");
            System.out.println("7 - Rotação Simples Esquerda <chave>;");
            System.out.println("8 - Rotação Simples Direita <chave>;");
            System.out.println("9 - Rotação Dupla Esquerda <chave>;");
            System.out.println("10 - Rotação Dupla Direita <chave>;");
            System.out.println("11 - Alterar modo do balanceamento;");
            System.out.println("0 - Sair:");
            System.out.print("\nDigite algum comando: ");

            String texto = scanner.nextLine();
            System.out.println();

            String[] parametros = texto.trim().split(" ");

            comando = Integer.parseInt(parametros[0]);
            chave = parametros.length > 1 ? Integer.parseInt(parametros[1]) : 0;
            valor = parametros.length > 2 ? parametros[2] : null;

            No no;
            switch (comando)
            {
                case 1:
                    arvore.inserir(chave, valor);
                    break;
                case 2:
                    no = arvore.remover(chave);
                    if (no == null)
                    {
                        System.out.printf("Nó #%d não encontrado!", chave);
                    }
                    else
                    {
                       System.out.printf("Nó #%d removido com sucesso!\n", chave);
                    }
                    break;
                case 3:
                    arvore.emOrdem();
                    break;
                case 4:
                    arvore.posOrdem();
                    break;
                case 5:
                    arvore = new Arvore();
                    break;
                case 6:
                    no = arvore.obter(chave);
                    if (no == null || no.getChave() != chave)
                    {
                        System.out.println("Item não encontrado!");
                    }
                    else
                    {
                        System.out.printf("Nó #%03d | Valor = %d", no.getChave(), no.getValor());
                    }
                    break;
                case 7:
                    arvore.rotacaoEsquerdaSimples(chave);
                    break;
                case 8:
                    arvore.rotacaoDireitaSimples(chave);
                    break;
                case 9:
                    arvore.rotacaoEsquerdaDupla(chave);
                    break;
                case 10:
                    arvore.rotacaoDireitaDupla(chave);
                    break;
                case 11:
                    arvore.setAtualizarAutomaticamente(!arvore.isAtualizarAutomaticamente());
                    break;
                default:
                    System.out.println("Comando inválido!");
                    break;
            }

        } while (comando != 0);
    }
}
