package Elearning.service.impl;

import Elearning.dao.CuestionarioDao;
import Elearning.dao.ModuloDao;
import Elearning.dao.PreguntasDao;
import Elearning.modelo.Cuestionario;
import Elearning.modelo.Modulo;
import Elearning.service.CuestionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service("CuestionarioService")
public class CuestionarioServiceImpl implements CuestionarioService {
    @Autowired
    private CuestionarioDao cuestionarioDao;
    
    @Autowired
    private PreguntasDao preguntasDao;

    @Autowired
    private ModuloDao moduloDao;
    
    @Override
    public String listAllCuestionarios(Model model, int idModulo) {
        Cuestionario cuestionario = null;
        if (cuestionarioDao.findAll(idModulo) != null && !cuestionarioDao.findAll(idModulo).isEmpty()) {
            cuestionario= cuestionarioDao.getCuestionario(cuestionarioDao.getIdByModulo(idModulo));
        }
        model.addAttribute("cuestionario", cuestionario);
        
        if(cuestionario != null){
            model.addAttribute("preguntas", preguntasDao.findAll(cuestionarioDao.getIdByModulo(idModulo)));
            model.addAttribute("IdModulo", idModulo);
        }
        return "cuestionario";
    }

    @Override
    public String listAllCuestionariosById(Model model, int idCuestionario) {
        model.addAttribute("cuestionario", cuestionarioDao.getCuestionario(idCuestionario));
        model.addAttribute("preguntas", preguntasDao.findAll(idCuestionario));
        //System.out.println("preguntas: " + preguntasDao.findAll(idCuestionario));
        System.out.println("ok cuest service impl");
        return "examenuser";
    }
    
    @Override
    public String createNewCuestionario(int IdModulo, String nombre) {
        try{
            Modulo modulo = new Modulo();
            System.out.println("idModulo en serv impl: " + IdModulo );
            modulo = moduloDao.getModulo(IdModulo); //--> Establecer el ID del Modulo
            //Cuestionario cuestionario = new Cuestionario(nombre); //--> Recuperar Datos de la Vista
            //Agregar Cuestionario al Modulo (Solo relaciona las tablas)
            //modulo.addCuestionarios(cuestionario);
            //Agregar Cuestionario a la BD (Previamente se tuvieron que relacionar las tablas)
            
            Cuestionario cuestionario = new Cuestionario();
            modulo = moduloDao.getModulo(IdModulo);
            cuestionario.setIdModulo_(modulo);
            cuestionario.setNombre(nombre);
            
            cuestionario = cuestionarioDao.create(cuestionario, IdModulo);
            
            //Retornar respuesta de exito
            return "Cuestionario creado";
        } catch(Exception e){
            //Retornar respuesta de error
            System.out.println("Hubo un error en createNewCuestionario - CuestionarioServiceImpl.java ---> " + e);
            return "Cuestionario no creado";
        }
    }

    @Override
    public String updateCuestionario(int idCuestionario, String nombre) {
        try{
            //Crear el Cuestionario
            Cuestionario cuestionario = new Cuestionario();
            //Recuperar por Id el Cuestionario ya existente
            cuestionario = cuestionarioDao.getCuestionario(idCuestionario);
            //Modificar el Nombre del Cuestionario
            cuestionario.setNombre(nombre);
            //Actualizar Cuestionario en la BD
            cuestionario = cuestionarioDao.update(cuestionario);
            
            //Retornar respuesta de exito
            return "Cuestionario actualizado";
        } catch(Exception e){
            //Retornar respuesta de error
            System.out.println("Hubo un error en updateCuestionario - CuestionarioServiceImpl.java ---> " + e);
            return "Cuestionario no actualizado";
        }
    }

    @Override
    public boolean deleteCuestionario(int idCuestionario) {
        try{
            Cuestionario cuestionario = new Cuestionario();
            cuestionario = cuestionarioDao.getCuestionario(idCuestionario);
            //Eliminar Cuestionario en la BD
            //if(cuestionarioDao.delete(cuestionario)){
            if(cuestionarioDao.deleteMisCuestionarioByCuestionario(cuestionario)){
                return true;
            } else 
                return false;
        } catch(Exception e){
            //Retornar respuesta de error
            System.out.println("Hubo un error en deleteCuestionario - CuestionarioServiceImpl.java ---> " + e);
            return false;
        }
    }
}
