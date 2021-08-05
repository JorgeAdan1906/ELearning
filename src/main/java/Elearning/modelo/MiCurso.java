
package Elearning.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MiCurso")
public class MiCurso implements Serializable{
  
    @Id
    @Column(name="idUsuario")
    private int idUsuario;
    
    @Id
    @Column(name="idCurso")
    private int idCurso;

    public MiCurso() {
    }

    public MiCurso(int idUsuario, int idCurso) {
        this.idUsuario = idUsuario;
        this.idCurso = idCurso;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    @Override
    public String toString() {
        return "MiCurso{" + "idUsuario=" + idUsuario + ", idCurso=" + idCurso + '}';
    }
 

    
}
