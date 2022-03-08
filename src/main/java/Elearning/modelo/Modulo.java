package Elearning.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Modulo")
public class Modulo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idModulo")
    private Integer idModulo;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descripcion")
    private String descripcion;
    
    @Column(name = "url")
    private String url;

    //Relación MUCHOS a UNO con Curso
    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.ALL,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name="idCurso")
    private Curso idCurso;
    
    //Relación UNO a MUCHOS con Cuestionario
//    @OneToMany(mappedBy = "idModuloC",fetch=FetchType.LAZY,cascade = {CascadeType.ALL,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
//    private List<Cuestionario> idCuestionario;

    //Relación MUCHOS a UNO con Cuestionario
    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.ALL,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name="idCuestionario")
    private Cuestionario idCuestionario;
    
    //Relacion UNO A MUCHOS con Preguntas
    @OneToMany(mappedBy = "idModulo_",fetch = FetchType.LAZY,cascade = {CascadeType.ALL,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    //@LazyCollection(LazyCollectionOption.FALSE)
    private List<Cuestionario> idCuestionario_;

    public Modulo() {}

    public Modulo(String titulo, String descripcion, String url) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public Integer getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Integer idModulo) {
        this.idModulo = idModulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Curso getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Curso idCurso) {
        this.idCurso = idCurso;
    }
    
    public Cuestionario getIdCuestionario() {
            return idCuestionario;
        }

    public void setIdCuestionario(Cuestionario idCuestionario) {
        this.idCuestionario = idCuestionario;
    }

    public List<Cuestionario> getIdCuestionario_() {
        return idCuestionario_;
    }

    public void setIdCuestionario_(List<Cuestionario> idCuestionario_) {
        this.idCuestionario_ = idCuestionario_;
    }

    public void addCuestionarios(Cuestionario cuestionario){   
//        if(idCuestionario != null){
//            //idCuestionario = new ArrayList<>();
//            idCuestionario.add(cuestionario);
//            cuestionario.setIdModulo(this);
//        }    
    }

//    @Override
//    public String toString() {
//        return "Modulo{" + "idModulo=" + idModulo + ", titulo=" + titulo + ", descripcion=" + descripcion + ", url=" + url + ", idCurso=" + idCurso + ", idCuestionario=" + idCuestionario + ", idCuestionario_=" + idCuestionario_ + '}';
//    }
    
    
//    

    

    
    
    
    


}
