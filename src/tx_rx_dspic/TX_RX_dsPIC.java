/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tx_rx_dspic;

import serial.SerialRXTX;

/**
 *
 * @author ediberto
 */
public class TX_RX_dsPIC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
     SerialRXTX serial = new SerialRXTX();
     int num = 12345;
     int num1 = num/10;
     int num2 = num%10;
     if(serial.iniciaSrial()){
         //while(true){
         serial.sendData(Integer.toString(num1));
         serial.sendData(Integer.toString(num2));
    
         //}  
     } else {
     
     
     }
             
    }    
}
