/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Envio_harmonic;

/**
 *
 * @author ediberto
 */
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Math.abs;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import serial.SerialRXTX;
import sun.security.util.Length;
import java.math.MathContext;
import java.util.concurrent.TimeUnit;
/**
 *
 * @author Ediberto
 */
public class envio_harm {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        // TODO code application logic here
        
        File arquivo = new File("C:/Users/ediberto/Desktop/teste.csv");
        SerialRXTX serial = new SerialRXTX();
        if(serial.iniciaSrial()){
        
        
        try {
            FileReader ler  = new FileReader(arquivo);
            BufferedReader lerarq = new BufferedReader(ler);
            String linha = lerarq.readLine();
            while(linha!=null){
                
                String [] har = linha.split(",");
                
                for (int i = 0; i < 6; i++) {
                    float har_aux= (Float.parseFloat(har[i]));
                    if(abs(har_aux)==1){
                     int n=(int) har_aux;
                     serial.sendData(Integer.toString(n));
                    }else{
                    int n1 = (int) (10000*har_aux);
                    int n2 = (int) (100000000*har_aux%10000);
                    String aux_n1 = Integer.toString(abs(n1));
                    String aux_n2 = Integer.toString(abs(n2));
                        if(aux_n1.length()<3){
                        serial.sendData("0");
                        }
                        if(aux_n1.length()<4){
                            serial.sendData("0");
                            serial.sendData(Integer.toString(n1));
                        }else{
                            serial.sendData(Integer.toString(n1));}
                        if(aux_n2.length()<3){
                        serial.sendData("0");
                        }
                        if(aux_n2.length()<4){
                            serial.sendData("0");
                            serial.sendData(Integer.toString(n2));
                        }else{
                            serial.sendData(Integer.toString(n2));}
                    }
//                    serial.sendData("-2");
                    serial.sendData(";");
                    System.out.println(har_aux);
//                    System.out.println(aux_n1);
//                    System.out.println(aux_n2);

//                    System.out.println(n1);
//                    System.out.println(n2);
                    har_aux=0;
                            
                    }
                
                
                //
 //               TimeUnit.MICROSECONDS.sleep(100);
                Thread.sleep(10);
                linha=lerarq.readLine();
                
            }
            
            
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(envio_harm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
    
}