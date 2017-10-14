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

            arvore.inserir(20);

            arvore.inserir(10);
            arvore.inserir(15);
            arvore.inserir(5);
            arvore.inserir(4);
            arvore.inserir(6);
            arvore.inserir(14);
            arvore.inserir(16);

            arvore.inserir(30);
            arvore.inserir(45);
            arvore.inserir(25);
            arvore.inserir(24);
            arvore.inserir(26);
            arvore.inserir(44);
            arvore.inserir(46);

            int comando = -1, chave;
            String valor;

            do
            { 
                System.out.println("\nÁrvore Binária de Pesquisa:\n");
                arvore.mostrar();
                
                System.out.println("1 - Inserir <chave> <valor>;");
                System.out.println("2 - Remover <chave>;");
                System.out.println("3 - Em Ordem;");
                System.out.println("4 - Pós Ordem;");
                System.out.println("5 - Limpar Árvore;");
                System.out.println("6 - Obter <chave>;");
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
                    default:
                        System.out.println("Comando inválido!");
                        break;
                }

            } while (comando != 0);
    }
}
