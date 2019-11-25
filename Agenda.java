package AppAgenda;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

public class Agenda implements Serializable{
     private ArrayList<Contact> contacte;
     private CriteriuOrdonare criteriu;
     private Predicate<Contact> predicate;
     
     public Agenda(){
         contacte=new ArrayList<>();
         criteriu=CriteriuOrdonare.NUME;
         predicate = c->c.getTelefon().startsWith("0");
     }
     
    public void adauga(Contact c){
        if(!contacte.contains(c)){
            contacte.add(c);
        }
        else{
            throw new RuntimeException("Contactul exista deja in agenda!");
        }
        
}
    public void setListaContacte(ArrayList<Contact> lista){
         contacte=lista;
     }
    public ArrayList<Contact> getListaContacte(){
        return this.contacte;
    }
    public void sterge(Contact c){
        contacte.remove(c);
    }
    public void sterge(int i){
        contacte.remove(i);
    }
            
    public int size(){
        return contacte.size();
    }
    public Contact get(int i){
        return contacte.get(i);
    }
   
    public void afiseaza(){
        for(Contact con:contacte){
            System.out.println(con);
        }
    }
    public void setCriteriu(CriteriuOrdonare criteriu){
        this.criteriu=criteriu;
    }
    public CriteriuOrdonare getCriteriu(){
        return this.criteriu;
    }
    public ArrayList<Contact> ordoneaza(){
        ArrayList<Contact> arr=new ArrayList<>();
        switch(this.criteriu){
            case NUME:
                arr=contacte.stream().sorted((p1,p2)->p1.getNume().compareTo(p2.getNume())).collect(Collectors.toCollection(ArrayList::new));
                break;
            case PRENUME:
                arr=contacte.stream().sorted((p1,p2)->p1.getPrenume().compareTo(p2.getPrenume())).collect(Collectors.toCollection(ArrayList::new));
                break;
            case VARSTA:
                arr=contacte.stream().sorted((p1,p2)->p1.getData().compareTo(p2.getData())).collect(Collectors.toCollection(ArrayList::new));
                break;
        }
        return arr;
    }
    public Predicate<Contact> getFiltru(){
        return this.predicate;
    }
    public void setFiltru(String s){
        this.predicate = c->c.getNume().contains(s)||c.getPrenume().contains(s)||c.getTelefon().contains(s);
    }
    public void setFiltruCBox(Filtru f){
        switch(f){
            case FIX:
                this.predicate = c->c.getTelefon().startsWith("02")||c.getTelefon().startsWith("03");
                break;
            case MOBIL:
                this.predicate = c->c.getTelefon().startsWith("07");
                break;
            case AZI:
                this.predicate = c->c.getData().equals(LocalDate.now());
                break;
            case LUNA_ASTA:
                this.predicate = c->c.getData().isAfter(LocalDate.now()) && (c.getData().getMonth() == LocalDate.now().getMonth());
                break;
            case TOTI:
                this.predicate = c->c.getTelefon().startsWith("0");
        }
        
    }
    /*public void setFiltruNrMobil(){
        this.predicate = c->c.getTelefon().startsWith("07");
    }
    public void setFiltruAzi(){
        this.predicate = c->c.getData().equals(LocalDate.now());
    }
    public void setFiltruLuna(){
        this.predicate = c->c.getData().isAfter(LocalDate.now()) && (c.getData().getMonth() == LocalDate.now().getMonth());
    }*/
    public ArrayList<Contact> filtreaza(Predicate<Contact> pred){
        ArrayList<Contact> arr;
        arr=contacte.stream().filter(pred).collect(Collectors.toCollection(ArrayList::new));
        return arr;
    }
    
    public void salveaza(ArrayList<Contact> c, File cale)  throws IOException, ClassNotFoundException {
        //File save = new File("C:\\Users\\rares\\Documents\\NetBeansProjects\\Proiect\\save.agenda");
        FileOutputStream fos = new FileOutputStream(cale);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(c);
        oos.close();
        fos.close();
        System.out.println("Am salvat cu succes");
    }
    
    public static ArrayList<Contact> incarca(File cale) throws IOException, ClassNotFoundException {
        //File save = new File("C:\\Users\\rares\\Documents\\NetBeansProjects\\Proiect\\save.agenda");
        FileInputStream fis = new FileInputStream(cale);
        ObjectInputStream ois = new ObjectInputStream(fis);
        ArrayList<Contact> ag = (ArrayList<Contact>)ois.readObject();
        ois.close();
        fis.close();
        return ag;
    }
    /* public static void main(String[] args) {
         
         Agenda a=new Agenda();
        Contact t1 = new Contact("Airi", "Miri", "12-12-2010", new NrMobil("0720010562"));
        Contact t2 = new Contact("Birii", "Niri", "12-12-2003", new NrMobil("0728010562"));
        Contact t3 = new Contact("Miri", "birii", "12-01-2013", new NrMobil("0758010562"));
        Contact t4 = new Contact("Diri", "Airi", "10-12-2013", new NrMobil("0728010562"));
        Contact t5 = new Contact("Diri", "Airi", "31-05-2019", new NrMobil("0728010562"));
        a.adauga(t1);
        a.adauga(t2);
        a.adauga(t3);
        a.adauga(t4);
        a.adauga(t5);
        a.afiseaza();
        ArrayList<Contact> ar= new ArrayList<>();
        ArrayList<Contact> arr=new ArrayList<>();
        
        a.setCriteriu(CriteriuOrdonare.PRENUME);
        ar=a.ordoneaza();
         System.out.println(ar);
         a.setFiltru("ii");
         arr=a.filtreaza(a.predicate);
         System.out.println(arr);
        
         try{
        
            a.salveaza(a.getListaContacte());
            System.out.println("am salvat");
            ArrayList<Contact> b=a.incarca();
            System.out.println(b);
        }
        catch(IOException e){
            System.out.println("exceptie  "+ e.getMessage());
        }
        catch(ClassNotFoundException e){
            System.out.println("Exceptie  "+ e.getMessage());
        }
        
        
    }*/
}
