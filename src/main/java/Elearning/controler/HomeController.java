
package Elearning.controler;

import Elearning.service.UsuarioService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class HomeController {
    
    
    @Autowired
    private UsuarioService usuarioService;
    
    @RequestMapping(value = "index.html", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView mo = new ModelAndView();
        mo.setViewName("index");
        return mo;   
    }
    
    
    @RequestMapping(value="semilleroRegistro.html",method = RequestMethod.GET)
    public String semilleroRegistro(HttpServletRequest request){
         ModelAndView mo = new ModelAndView();
        if(usuarioService.createNewSemillero(request).equals("existente")){
            System.out.println("");
            System.out.println("Correo ya registrado");
        }
        return usuarioService.createNewSemillero(request);
    }
    
     
    @RequestMapping(name = "validador.html",method = RequestMethod.GET)
    public ModelAndView validador(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mo = new ModelAndView();
         switch (usuarioService.loginUser(request)) {
            case "Semillero":
                mo.setViewName("semillero");
                break;
            case "Administrador":
                mo.setViewName("admin");
                break;
            default:
                mo.addObject("error", "Contraseña o Usuario Incorrecto");
                mo.setViewName("error");
                break;
        }
         return mo;
    }
  
    @RequestMapping("error.html")
    public ModelAndView error(){
        ModelAndView mo = new ModelAndView();
        mo.setViewName("error");
        return mo;
    }
    
    @RequestMapping("admin.html")
    public ModelAndView admin(){
         ModelAndView mo = new ModelAndView();
        mo.setViewName("admin");
        return mo;
    }
    
     @RequestMapping("semillero.html")
     public ModelAndView semillero(){
         ModelAndView mo = new ModelAndView();
        mo.setViewName("semillero");
        return mo;
    }
    
   

   
}