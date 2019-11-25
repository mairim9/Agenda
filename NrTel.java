package AppAgenda;


import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rares
 */
public abstract class NrTel implements Serializable{
    String numar;
    NrTel(String nr){
        if(validareNumar(nr)){
        this.numar = nr;}
    }
    abstract boolean validareNumar(String nr);
    public String toString(){
        return this.numar;
    }
    public boolean equals(NrTel t){
        if(!numar.equals(t.numar)){
            return false;}
        
            return true;
        
    }
    /*public static void main(String[] args){
        NrTel t = new NrFix("0327010562");
    }*/
}
   


