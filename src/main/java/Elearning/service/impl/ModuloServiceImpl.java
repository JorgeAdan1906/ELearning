package Elearning.service.impl;

import Elearning.dao.ModuloDao;
import Elearning.modelo.Modulo;
import Elearning.dto.ModuloDto;
import Elearning.modelo.formModel.CursoModel;
import Elearning.modelo.formModel.ModuloModel;
import Elearning.service.ModuloService;
import Elearning.util.JavaDropBox;
import com.dropbox.core.DbxException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author sergi
 */
public class ModuloServiceImpl implements ModuloService {

    @Autowired
    private ModuloDao moduloDao;

    @Override
    public String readModulo() {

        List<ModuloDto> lista = new ArrayList<ModuloDto>();
        List<Modulo> lista2 = moduloDao.findAll();
        String data = "";

        for (int i = 0; i < lista2.size(); i++) {
            ModuloDto dto = new ModuloDto();
            dto.setIdModulo(lista2.get(i).getIdModulo());
            dto.setTitulo(lista2.get(i).getTitulo());
            dto.setDescripcion(lista2.get(i).getDescripcion());
            dto.setUrl(lista2.get(i).getUrl());
            lista.add(dto);
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            data = mapper.writeValueAsString(lista);

        } catch (JsonProcessingException ex) {
            Logger.getLogger(ModuloServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return data;

    }

    @Override
    public ModelAndView createNewModulo(ModuloModel moduloM) {
        ModelAndView mo = new ModelAndView("html_utf8");
    
        Modulo entidad = new Modulo();
        try {
            entidad.setTitulo(moduloM.getTitulo());
            entidad.setDescripcion(moduloM.getDescripcion());
            
            String enlace = guardarDropBox(moduloM);
            
             if(!enlace.equals("")){
                entidad.setUrl(enlace);
                System.out.println("El video se Guardo correctamente y ya esta creada la url de DropBox");
            }else{
                mo.setViewName("error");
                System.out.println("Error al crear la Url de DropBox");
                return mo;
            }
             
             entidad = moduloDao.create(entidad);
             mo.setViewName("redirect:");
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(ModuloServiceImpl.class.getName()).log(Level.SEVERE, null,e);
            mo.setViewName("");
        }
        return mo;
    }

    @Override
    public String updateModulo(HttpServletRequest request) {
        // Integer idModulo = Integer.parseInt(request.getParameter("idModulo"));
        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");
        String url = request.getParameter("url");
        //String[] curso = request.getParameterValues("curso[]");

        Modulo modulo = new Modulo(titulo, descripcion, url);
        modulo = moduloDao.update(modulo);

        ModuloDto moduloDto = new ModuloDto(modulo.getTitulo(), modulo.getDescripcion(), modulo.getUrl());
        String data = "";

        try {
            ObjectMapper mapper = new ObjectMapper();
            data = mapper.writeValueAsString(moduloDto);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ModuloServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    @Override
    public String deleteModulo(Map<String, String> requestParam) {
        Integer idModulo = Integer.parseInt(requestParam.get("IdModulo"));
        Modulo elimModulo = new Modulo();
        elimModulo.setIdModulo(idModulo);
        boolean flag = moduloDao.delete(elimModulo);
        if (flag) {
            return "{\"valid\"}";
        }
        return "{\"valid\"}";
    }

    private String guardarDropBox(ModuloModel moduloM) throws IOException, FileNotFoundException, DbxException {
        JavaDropBox jv = new JavaDropBox();
        String enlace = "";
        String caratula = moduloM.getUrl()+ "_Video" + getExtention(moduloM.getUrl().getOriginalFilename());
        jv.uploadToDropbox(moduloM.getUrl().getBytes(), "/" + caratula);
        String urlVideo = jv.createURL(caratula);

        if (!urlVideo.equals("")) {
            enlace = urlVideo;
        }

        return enlace;
    }

    private String getExtention(String string) {
        return string.substring(string.lastIndexOf("."), string.length());

    }
}
