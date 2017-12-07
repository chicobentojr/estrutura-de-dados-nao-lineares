/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Labirinto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author chicobentojr
 */
public class LeitorDeArquivo {

    public static String lerArquivo(String caminho) {
        BufferedReader br = null;
        FileReader fr = null;
        String retorno = new String();

        try {

            fr = new FileReader(caminho);
            br = new BufferedReader(fr);

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                retorno += sCurrentLine;
                retorno += "\n";
            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (br != null) {
                    br.close();
                }

                if (fr != null) {
                    fr.close();
                }

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }
        
        return retorno;

    }
}
