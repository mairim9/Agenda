package AppAgenda;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;

public class Contact implements Serializable{

    private String nume;
    private String prenume;
    private String data;
    private NrTel telefon;

    Contact(String nume, String prenume, String data, NrTel telefon) {

        if (valideazaData(data)&&telefon.validareNumar(telefon.toString())&&valideazaNume(nume)&&valideazaNume(prenume)) {
            this.data = data;
            this.nume = nume;
            this.prenume = prenume;
            this.telefon = telefon;
        }
    }
    public void setNume(String nume){
        this.nume=nume;
    }
    public void setPrenume(String prenume){
        this.prenume=prenume;
    }
    public void setData(String data){
        this.data=data;
    }
    public void setNrTel(NrTel tel){
        this.telefon=tel;
    }
    public String getNume(){
        return this.nume;
    }
    public String getPrenume(){
        return this.prenume;
    }
    public String getTelefon(){
        return this.telefon.toString();
    }
    public LocalDate getData(){
        String[] date=this.data.split("-");
        //LocalDate d;
        int zi=Integer.parseInt(date[0]);
        int luna=Integer.parseInt(date[1]);
        int an=Integer.parseInt(date[2]);
        return LocalDate.of(an,luna,zi);
    }
    public String toString() {
        return nume + " " + prenume + ", " + data + ", " + telefon;
    }

    private static boolean valideazaNume(String nume){
        if(nume==null || nume.length() <= 2){
            
            throw new IllegalArgumentException("Numele trebuie sa aiba cel putin 2 litere!");
            
        }
        return true;
    }
    private static boolean valideazaData(String data) {
        if (data == null || data.length() != 10 || data.indexOf("-") != 2 || data.lastIndexOf("-") != 5) {
            throw new IllegalArgumentException("Data trebuie sa fie in format DD-MM-YEAR!");
        }
        String[] d = data.split("-");
        if (d.length != 3) {
            throw new IllegalArgumentException("Data trebuie sa fie in format DD-MM-YEAR!");
        } else {
            int an = Integer.parseInt(d[2]);
            int luna = Integer.parseInt(d[1]);
            int zi = Integer.parseInt(d[0]);

            if (zi < 1 || zi > 31 || luna < 1 || luna > 12) {
                throw new IllegalArgumentException("Data invalida!");
            }

            return true;
        }
    }
   /* @Override
    public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((nume == null) ? 0 : nume.hashCode());
		result = prime * result + ((prenume == null) ? 0 : prenume.hashCode());
		result = prime * result + ((telefon == null) ? 0 : telefon.hashCode());
		return result;
	}*/
  
    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (nume == null) {
			if (other.nume != null)
				return false;
		} else if (!nume.equals(other.nume))
			return false;
		if (prenume == null) {
			if (other.prenume != null)
				return false;
		} else if (!prenume.equals(other.prenume))
			return false;
		if (telefon == null) {
			if (other.telefon != null)
				return false;
		} else if (!telefon.equals(other.telefon))
			return false;
		return true;
	}
    
   /*public static void main(String[] args) {

        Contact t = new Contact("Miri", "Miri", "29-05-2019", new NrMobil("0728010562"));
        System.out.println(t);
            System.out.println(t.getData());
            System.out.println(LocalDate.now());
            if(t.getData().equals(LocalDate.of(2019, 5, 29))){
                System.out.println("da");
            }
    ActionListener task=new ActionListener(){
        private int secunde=0;
        public void actionPerformed(ActionEvent e){
            System.out.println("au trecut"+ (++secunde)+" secunde");
        }
    };
    javax.swing.Timer timer=new javax.swing.Timer(1000, task);
    timer.start();
   
   }*/
}
