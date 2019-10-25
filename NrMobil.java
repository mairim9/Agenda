
import java.io.Serializable;


public class NrMobil extends NrTel implements Serializable{
    NrMobil(String nr){
        super(nr);
    }
    boolean validareNumar(String nr){
        if(nr == null || nr.length()<10 || !nr.startsWith("07")){
            throw new IllegalArgumentException("Numarul trebuie sa inceapa cu 07 si sa aiba 10 cifre!");
        }
        return true;
    }
}
