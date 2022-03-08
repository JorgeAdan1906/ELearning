package Elearning.controler;

//import static Elearning.modelo.Cuestionario_.idCuestionario;
import Elearning.service.MiCuestionarioService;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MiCuestionarioController {
    @Autowired
    private MiCuestionarioService miCuestionarioService;
    
    //Crear nuevo MiCuestionario  --> "mediacursos.jsp" 
    @RequestMapping(value = "CrearMiCuestionario.html", method = RequestMethod.GET)
    public String crearMiCuestionario(@RequestParam("Evaluacion") boolean Evaluacion, @RequestParam("IdUsuario") int IdUsuario, @RequestParam("IdCuestionario") int IdCuestionario){
        String Redirect;
        if (miCuestionarioService.validateMiCuestionario(IdUsuario, IdCuestionario))
            Redirect = "redirect:/examenuser.html?idUsuario="+IdUsuario+"&idCuestionario=" +IdCuestionario;
        else {
            if (miCuestionarioService.createNewMiCuestionario(Evaluacion, IdUsuario, IdCuestionario).equals("MiCuestionario creado")) {
                Redirect = "redirect:/examenuser.html?idUsuario="+IdUsuario+"&idCuestionario=" +IdCuestionario; 
            } else
                Redirect = "redirect:/error.html"; 
        }
        return Redirect;
    }
    
    //Actualizar MiCuestionario  --> "examenuser.jsp" 
    @RequestMapping(value = "ActualizarMiCuestionario.html", method = RequestMethod.GET)
    public String actualizarMiCuestionario(@RequestParam("idCurso") int idCurso, @RequestParam("idUsuario") int idUsuario, @RequestParam("idCuestionario") int idCuestionario, @RequestParam("calificacion") float calificacionDec ,@RequestParam("Evaluacion") boolean Evaluacion){
        String Redirect  = "";
        System.out.println("Curso: " + idCurso);
            System.out.println("usuario " + idUsuario);
            System.out.println("Cuest: " + idCuestionario);
            //System.out.println("CALIFICACION: " + calificacion);
            DecimalFormat formato1 = new DecimalFormat("#.00");
            String calificacion = formato1.format(calificacionDec);
            System.out.println("x: " + calificacion);
            float calificacionFinal = Float.parseFloat(calificacion);
            System.out.println("calif final:" + calificacionFinal);
        try {  
            try {
                int idMicuest = miCuestionarioService.getIdMiCuestionario(idUsuario, idCuestionario);
                System.out.println("micuest: " + idMicuest);
            } catch (Exception e) {
                System.out.println("no idMiCuest: " + e);
            }
        
        if (miCuestionarioService.updateMiCuestionario(miCuestionarioService.getIdMiCuestionario(idUsuario, idCuestionario), Evaluacion, calificacionFinal).equals("MiCuestionario actualizado")) {  
              try {
                  if(miCuestionarioService.defineProgress(idCurso, idUsuario).equals("Progreso establecido")){
                        Redirect = "redirect:/mediacursos.html?idCurso=" + idCurso; 
                        System.out.println("idCuest en miCuest controller: " + idCuestionario);
                        //Redirect = "redirect:/examenuser.html?idUsuario="+idUsuario+"&idCuestionario="+ idCuestionario ;
                  }
                    else
                        System.out.println("error al actualizar miCuestionario");
                        //Redirect = "redirect:/error.html"; 
              } catch (Exception e) {
                  System.out.println("ERROR try miCuest controller:" + e);
              }
        } else
            Redirect = "redirect:/error.html";   
        } catch (Exception e) {
            System.out.println("ERROR controller: " + e);
        }
        return Redirect;
    }
    
    
    //Eliminar MiCuestionario  --> "cuestionario.jsp" 
    @RequestMapping(value = "EliminarMiCuestionario.html", method = RequestMethod.GET)
    public String eliminarMiCuestionario(@RequestParam("IdMiCuestionario") int IdMiCuestionario){
        String Redirect;
        if (miCuestionarioService.deleteMiCuestionario(IdMiCuestionario)) {
            Redirect = "redirect:/cuestionario.html"; 
        } else
            Redirect = "redirect:/error.html"; 
        return Redirect;
    }
}
