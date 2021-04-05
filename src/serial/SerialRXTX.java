/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serial;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author ediberto
 */
public class SerialRXTX implements SerialPortEventListener{
    SerialPort serialPort = null;
    private Protocolo protocolo = new Protocolo();
    private String appName; // nome
    
    private BufferedReader input; //objeto para leitura serial
    private OutputStream output;// objeto para escrita serial
            
    private static final int TIME_OUT = 1000;// tempo de espera
    private static final int DATE_RATE=115200;// define velocidade de comunincação
    
    private boolean Leitura;

    private boolean Escrita;
    
    private String serialPortName= "COM3";
    
    public boolean iniciaSrial(){
        try {
            CommPortIdentifier portId = null;
            Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
            
            while(portId == null&& portEnum.hasMoreElements()){
                CommPortIdentifier currPortId = (CommPortIdentifier)portEnum.nextElement();
                if(currPortId.getName().equals(serialPortName)|| currPortId.getName().startsWith(serialPortName)){
                    serialPort = (SerialPort) currPortId.open(appName,TIME_OUT);
                    portId = currPortId;
                    System.out.println("Cpmectado em : "+currPortId.getName());
                    break;
                }
            }
            
            if(portId==null || serialPort == null){
                return false;
            }
            
            serialPort.setSerialPortParams(DATE_RATE, 
                    SerialPort.DATABITS_8, 
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
            
            try {
                Thread.sleep(1000);
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    int tempodelay = 20;// tempo em micro
    public void sendData(String data ){
        try {
            output = serialPort.getOutputStream();
            // System.out.println("Enviando : " + data );
            output.write(data.getBytes());
            TimeUnit.MICROSECONDS.sleep(10);
            //busyWaitMicros(100);
            
           //Thread.sleep(5);
            
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }
    
    public static void busyWaitMicros(long micros){
        long waitUntil = System.nanoTime() + (micros * 1_000);
        while(waitUntil > System.nanoTime()){
            ;
}    }
    
    public  synchronized void close(){
        if(serialPort!= null){
            serialPort.removeEventListener();
            serialPort.close();
        
        }   
    }
    
    
    
    
    @Override
    public void serialEvent(SerialPortEvent spe) {
        
        try {
            
            switch(spe.getEventType()){
                case SerialPortEvent.DATA_AVAILABLE:
                    if(input==null){
                       input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
                    }
                    
                 if(input.ready()){   
                 String InputLine = input.readLine();
                 System.err.println("" + InputLine);
                 escritor(InputLine);
                 }
                 break;
                 
                default:
                    break;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }  

   public static void escritor(String result) throws IOException {
        String path="C:/Users/ediberto/Desktop/result.txt";
        FileWriter filewriter= new FileWriter(path, true);
        BufferedWriter buffWrite = new BufferedWriter(filewriter);
//        String linha = "";
//        Scanner in = new Scanner(System.in);
//        System.out.println("Escreva algo: ");
//        linha = in.nextLine();
        buffWrite.write(result);
        //buffWrite.append(Integer.toString(q15) + ",");
        buffWrite.newLine();
        buffWrite.close();
    }
    
    
}
