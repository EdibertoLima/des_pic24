/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Q15_model;

/**
 *
 * @author ediberto
 */
public class Q15 {
    int Q15=0;
   public int float_q15(float num){
   Q15=(int)(32768*num);
   if(Q15>32767)
       Q15=32767;
   if(Q15<-32768)
       Q15=-32768;
   return Q15;
   }
    
}
