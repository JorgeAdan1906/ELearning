package Elearning.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="Cuestionario")
public class Cuestionario implements Serializable{
    
    /*Timer myTimer = new Timer();
    long delay = 60000L;
    int tiempoDisp = 1;
    String tiempo;*/
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idCuestionario")
    private int idCuestionario;
    
    @Column(name="nombre")
    private String nombre;
    
    //Relación MUCHOS a UNO con Modulo
    //era persist el primero
    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.ALL,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name="idModulo")
    private Modulo idModulo_;
    
    //Relación MUCHOS a UNO con Modulo
//    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
//    @JoinColumn(name="idModulo")
//    private Modulo idModuloC;

    //Relación UNO a MUCHOS con Cuestionario
    @OneToMany(mappedBy = "idCuestionario", fetch=FetchType.LAZY, cascade = {CascadeType.ALL,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    //@LazyCollection(LazyCollectionOption.FALSE)
    private List<Modulo> idModulo;
    
    
    //Relacion UNO A MUCHOS con Preguntas
    @OneToMany(mappedBy = "idCuestionario",fetch=FetchType.LAZY, cascade = {CascadeType.ALL,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    //@LazyCollection(LazyCollectionOption.FALSE)
    private List<Preguntas> idPregunta;

    //Relacion UNO A MUCHOS con MiCuestionario --> "MUCHOS A MUCHOS"
    @OneToMany(mappedBy = "idCuestionario",fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    //@LazyCollection(LazyCollectionOption.FALSE)
    private List<MiCuestionario> miCuestionario;
    
    //Relacion MUCHOS A MUCHOS con Usuario
//    @ManyToMany(mappedBy = "cuestionarios",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH},fetch = FetchType.EAGER)
//    private Set<Usuario> usuarios = new HashSet<>();

    public Cuestionario() {}

    public Cuestionario(int idCuestionario, String nombre, Modulo idModulo_, List<Modulo> idModulo, List<Preguntas> idPregunta, List<MiCuestionario> miCuestionario) {
        this.idCuestionario = idCuestionario;
        this.nombre = nombre;
        this.idModulo_ = idModulo_;
        this.idModulo = idModulo;
        this.idPregunta = idPregunta;
        this.miCuestionario = miCuestionario;
    }
    

    public Cuestionario(String nombre) {
        this.nombre = nombre;
    }
     
    public int getIdCuestionario() {
        return idCuestionario;
    }

    public void setIdCuestionario(int idCuestionario) {
        this.idCuestionario = idCuestionario;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Modulo getIdModulo_() {
        return idModulo_;
    }

    public void setIdModulo_(Modulo idModulo_) {
        this.idModulo_ = idModulo_;
    }

    
    public List<Preguntas> getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(List<Preguntas> idPregunta) {
        this.idPregunta = idPregunta;
    }
    
    public List<MiCuestionario> getMiCuestionario() {
        return miCuestionario;
    }

    public void setMiCuestionario(List<MiCuestionario> miCuestionario) {
        this.miCuestionario = miCuestionario;
    }

    public List<Modulo> getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(List<Modulo> idModulo) {
        this.idModulo = idModulo;
    }

    
    public void addPreguntas(Preguntas preguntas){   
        if(idPregunta != null){
            idPregunta = new ArrayList<>();
            idPregunta.add(preguntas);
            preguntas.setIdCuestionario(this);
        }    
    }
    
//    public void addMiCuestionario(MiCuestionario miCuestionario){   
//        if(this.miCuestionario != null){
//            this.miCuestionario = new ArrayList<>();
//            this.miCuestionario.add(miCuestionario);
//            miCuestionario.setIdCuestionario(this);
//        }    
//    }

    @Override
    public String toString() {
        return "Cuestionario{" + "idCuestionario=" + idCuestionario + ", nombre=" + nombre + ", idModulo_=" + idModulo_ + '}';
    }

//    
    
    
    /*TimerTask task = new TimerTask(){
            public void run(){
                System.out.println("se agoto el timer");
                tiempoDisp = 0;
                
            }
        };*/
    
    /*public String TimerCuest(){
        //System.out.println("ok");
        myTimer.schedule(task, delay);
        if(tiempoDisp == 0){
            String tiempo = "Se acabo";
        }
        if(tiempoDisp == 1){
            String string = "Todavia puedes seguir";
        }
    return tiempo;
    }*/
    
}
