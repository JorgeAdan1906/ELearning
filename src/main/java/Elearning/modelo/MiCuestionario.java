package Elearning.modelo;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MiCuestionario")
public class MiCuestionario implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idMiCuestionario")
    private int idMiCuestionario;
    
    @Column(name="evaluacion")
    private boolean evaluacion;

    @Column(name="estado")
    private String estado;
    
    @Column(name="calificacion")
    private float calificacion;
    
    //Relación MUCHOS a UNO con Usuario --> "MUCHOS A MUCHOS"
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name="idUsuario", referencedColumnName = "idUsuario")
    private Usuario usuario;
    
    //Relación MUCHOS a UNO con Cuestionario --> "MUCHOS A MUCHOS"
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name="idCuestionario", referencedColumnName = "idCuestionario")
    private Cuestionario idCuestionario; //le agrege el "id"
    
    public MiCuestionario() {
    }

    public MiCuestionario(int idMiCuestionario, boolean evaluacion, String estado, float calificacion, Usuario usuario, Cuestionario idCuestionario) {
        this.idMiCuestionario = idMiCuestionario;
        this.evaluacion = evaluacion;
        this.estado = estado;
        this.calificacion = calificacion;
        this.usuario = usuario;
        this.idCuestionario = idCuestionario;
    }

//    public MiCuestionario(int idUsuario, int idCuestionario) {
//        this.idUsuario = idUsuario;
//        this.idCuestionario = idCuestionario;
//    }
    
    public MiCuestionario(boolean evaluacion) {
        this.evaluacion = evaluacion;
    }
    
    public int getIdMiCuestionario() {
        return idMiCuestionario;
    }

    public void setIdMiCuestionario(int idMiCuestionario) {
        this.idMiCuestionario = idMiCuestionario;
    }
    
    public boolean getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(boolean evaluacion) {
        this.evaluacion = evaluacion;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
//    public Cuestionario getCuestionario() {
//        return cuestionario;
//    }
//
//    public void setCuestionario(Cuestionario cuestionario) {
//        this.cuestionario = cuestionario;
//    }

    public Cuestionario getIdCuestionario() {
        return idCuestionario;
    }

    public void setIdCuestionario(Cuestionario idCuestionario) {
        this.idCuestionario = idCuestionario;
    }
    
    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(float calificacion) {
        this.calificacion = calificacion;
    }

    @Override
    public String toString() {
        return "MiCuestionario{" + "idMiCuestionario=" + idMiCuestionario + ", evaluacion=" + evaluacion + ", estado=" + estado + ", calificacion=" + calificacion + ", usuario=" + usuario + ", idCuestionario=" + idCuestionario + '}';
    }

}

//-----------------------------------------------------------------------------

//@Entity
//@Table(name = "MiCuestionario")
//public class MiCuestionario implements Serializable{
//    
//    @Column(name="evaluacion")
//    private boolean evaluacion;
//    
//    @Id
//    @Column(name="idUsuario")
//    private int idUsuario;
//    
//    @Id
//    @Column(name="idCuestionario")
//    private int idCuestionario;
//    
//    public MiCuestionario() {
//    }
//
//    public MiCuestionario(int idUsuario, int idCuestionario) {
//        this.idUsuario = idUsuario;
//        this.idCuestionario = idCuestionario;
//    }
//    
//    public MiCuestionario(boolean evaluacion) {
//        this.evaluacion = evaluacion;
//    }
//    
//    public int getIdUsuario() {
//        return idUsuario;
//    }
//
//    public void setIdUsuario(int idUsuario) {
//        this.idUsuario = idUsuario;
//    }
//    
//    public int getIdCuestionario() {
//        return idCuestionario;
//    }
//
//    public void setIdCuestionario(int idCuestionario) {
//        this.idCuestionario = idCuestionario;
//    }
//    
//    public boolean getEvaluacion() {
//        return evaluacion;
//    }
//
//    public void setEvaluacion(boolean evaluacion) {
//        this.evaluacion = evaluacion;
//    }
//}
