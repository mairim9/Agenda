package AppAgenda;


import java.io.Serializable;


public class NrFix extends NrTel implements Serializable{
    NrFix(String nr){
        super(nr);
    }
    boolean validareNumar(String nr){
        if(nr == null || nr.length()<10 || (!nr.startsWith("02") && !nr.startsWith("03"))){
            throw new IllegalArgumentException("Numarul trebuie sa inceapa cu 02 sau 03 si sa aiba 10 cifre!");
        }
        return true;
    }
}
