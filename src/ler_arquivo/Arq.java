/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ler_arquivo;

import Q15_model.Q15;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.concurrent.TimeUnit;
import serial.SerialRXTX;
/**
 *
 * @author ediberto
 */
public class Arq {
    
    public static void main(String[] args){
//        Path caminho = Paths.get("C:/Users/ediberto/Desktop/teste.txt");
        File arquivos[];
        File diretorio = new File("C:/Users/ediberto/Desktop/novosdados/q15");
        arquivos = diretorio.listFiles();
        File arquivo = new File("C:/Users/ediberto/Desktop/q15.txt");
         SerialRXTX serial = new SerialRXTX();
//         Q15 convercao = new Q15();
     
     if(serial.iniciaSrial()){
    
        int i =0;
        int j=0;
        int q15=0;
        String aux;
        String Nome;
         for(j = 0; j < arquivos.length; j++){  
        try {
         
         //   byte[] texto = Files.readAllBytes(caminho);
            System.out.println(arquivos[j].toString());
            FileReader ler  = new FileReader(arquivo);
            BufferedReader lerarq = new BufferedReader(ler);
            String linha = lerarq.readLine();
//            System.out.println(arquivos[j]);
//            System.out.println(j);
            while(linha!=null){

               q15=Integer.parseInt(linha);
//               System.err.println(q15);
               int q15_1 = q15/10;
               int q15_2 = q15%10;
                serial.sendData(Integer.toString(q15_1));
                serial.sendData(Integer.toString(q15_2)+",");
                serial.sendData("0,");
//                serial.sendData(",");
                
//               System.out.println(j);
                linha=lerarq.readLine();
               
            i++; 
            }
            serial.sendData("     ");//limpar 
            TimeUnit.MILLISECONDS.sleep(300);
            System.out.println(j+1);
            
        i=0;
        } catch (Exception e) {
        }
        }
//         }
    }else{
     }
     //serial.close();
     
     
    }
    
    
}
