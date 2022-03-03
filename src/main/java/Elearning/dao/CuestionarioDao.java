package Elearning.dao;

import java.util.List;
import Elearning.modelo.Cuestionario;

public interface CuestionarioDao {

    public List<Cuestionario> findAll(int idModulo);
    public List<Cuestionario> findAllByCurso(int idCurso);
    public List<Cuestionario> findAllByCursoByModulo(int idCurso, int idModulo);
    public Integer countByCurso(final Integer idCurso);
    public Cuestionario getCuestionario(final Integer idCuestionario);
    public Integer getIdByModulo(final Integer idModulo);
    public Integer getIdByModuloAndName(final Integer idModulo, String name);
    public Cuestionario create(final Cuestionario elCuestionario, int idModulo);
    public Cuestionario update(final Cuestionario elCuestionario);
    public boolean delete(final Cuestionario elCuestionario);
    public boolean deleteMisCuestionarioByCuestionario(final Cuestionario elCuestionario);
    public List<Integer> getCuestionariosByCurso(int idCurso);
    
}
