/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Envio_harmonic;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import serial.SerialRXTX;

/**
 *
 * @author ediberto
 */
public class teste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)   {
        // TODO code application logic here
         
        File arquivo = new File("C:/Users/ediberto/Desktop/Conjunto de teste 7 neuronios ocultos.csv");
        SerialRXTX serial = new SerialRXTX();
        
        
        
        try {
            FileReader ler  = new FileReader(arquivo);
            BufferedReader lerarq = new BufferedReader(ler);
            String linha = lerarq.readLine();
            while(linha!=null){
                
                String [] har = linha.split(",");
                
                for (int i = 0; i < 6; i++) {
                   // float har_aux= (Float.parseFloat(har[i]));
                    //int n1 = (int) (10000*har_aux);
                    //int n2 = (int) (100000000*har_aux%10000);
//                    serial.sendData(Integer.toString(n1));
//                    serial.sendData(Integer.toString(n2));
//                    serial.sendData(";");
                   System.out.println(har[i]);
                   // System.out.println(n1);
                   // System.out.println(n2);
                   // har_aux=0;
                            
                    }
                
                
                //
//                Thread.sleep(1000);
                
                linha=lerarq.readLine();
                
            }
            
            
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(teste.class.getName()).log(Level.SEVERE, null, ex);
        }   catch (IOException ex) {
                Logger.getLogger(teste.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    
    
}
    }
    

