
package Elearning.dao;

import Elearning.modelo.Modulo;
import java.util.List;

public interface ModuloDao {
    
    public List<Modulo> findAll();
    public Modulo create(final Modulo elModulo);
    public Modulo getModulo(final Integer idModulo);
    public Modulo update(final Modulo elModulo);
    public List<Modulo> findbyCurso(int idCurso);
    public List<Modulo> findbyCurso_media(int idCurso);
    public boolean delete(final Modulo elModulo);
    public String updateidModulo(int idModulo, int idCuestionario);
    public boolean noCheckModulo();
    public boolean noCheckCuestionario();
    public boolean noCheckPregunta();
    public boolean CheckModulo();
    public boolean CheckCuestionario();
    public List<Integer> findIdsbyCurso(int idCurso);
    
}
