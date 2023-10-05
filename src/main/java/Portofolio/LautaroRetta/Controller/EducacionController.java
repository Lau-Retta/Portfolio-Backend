/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Portofolio.LautaroRetta.Controller;

import Portofolio.LautaroRetta.Dto.DtoEducacion;
import Portofolio.LautaroRetta.Entity.Educacion;
import Portofolio.LautaroRetta.Security.Mensaje;
import Portofolio.LautaroRetta.Service.EducacionService;
import io.micrometer.common.util.StringUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lauta
 */
@RestController
@RequestMapping("Educacion")
@CrossOrigin(origins = "http://localhost:4200")
public class EducacionController {
   @Autowired EducacionService iEducacionService;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Educacion>> getEducacion(){
        List<Educacion> edu_list = iEducacionService.getEducacion();
        return new ResponseEntity(edu_list, HttpStatus.OK);
    }

    @GetMapping("/traer/{id}")
    public Educacion findEducacion(@PathVariable int id){
        return iEducacionService.findEducacion(id);
    }

    
    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody DtoEducacion dtoedu){
        /*Hacemos la comprobacion por condicionales de que los datos ingresados sean los correctos
        y no generen errores al momento de ejecutar los metodos*/
        
        //comprobamos que no este vacio, si es true se le dev el mensaje
        if(StringUtils.isBlank(dtoedu.getInstituto())){
            return new ResponseEntity(new Mensaje("La Intstituto no puede esta vacio"), HttpStatus.BAD_REQUEST);
        }    
        if(iEducacionService.existByEducaucion(dtoedu.getInstituto())){
            return new ResponseEntity(new Mensaje("Esa Educacion no existe"), HttpStatus.BAD_REQUEST);
        }
        Educacion educacion = new Educacion(dtoedu.getInstituto(),dtoedu.getTitulo(), dtoedu.getDescripcion(),dtoedu.getImg_educacion(), dtoedu.getInicio(),
        dtoedu.getFin());
        iEducacionService.saveEducacion(educacion);
        
        
        return new ResponseEntity(new Mensaje("Educacion añadida"), HttpStatus.OK);
         
    }
   
    
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editar(@PathVariable("id") int id, @RequestBody DtoEducacion dtoedu){
        
        /*Validademos que la Educacion que queremos editar exista comprobando por id*/
        if (!iEducacionService.existById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);            
        }
        
        /*Validadmos si la Educacion que se quiere ingresar al editar ya exista, si ya existe se retorna
        el mensaje*/
        if (iEducacionService.existByEducaucion(dtoedu.getInstituto())&& 
                iEducacionService.getEduacion(dtoedu.getInstituto()).get().getId()!=id){
            return new ResponseEntity(new Mensaje("Ese Intstituto ya no existe"), HttpStatus.BAD_REQUEST);
        }
        /*Validamos si el nombre de la Intstituto no quedo en blanco*/
        if(StringUtils.isBlank(dtoedu.getInstituto())){
            return new ResponseEntity(new Mensaje("El Intstituto esta en blanco"), HttpStatus.BAD_REQUEST);
        }
       
        Educacion exp = iEducacionService.getOne(id).get();
        exp.setInstituto(dtoedu.getInstituto());
        exp.setDescripcion(dtoedu.getDescripcion());
        exp.setInicio(dtoedu.getInicio());
        exp.setFin(dtoedu.getFin());
        exp.setImg_educacion(dtoedu.getImg_educacion());
        
        
        iEducacionService.saveEducacion(exp);
        return new ResponseEntity(new Mensaje("Educacion actualizada"), HttpStatus.OK);
        
    }
    
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrar(@PathVariable("id") int id){
        /*Validademos que la Educacion que queremos borrar exista comprobando por id*/
        if (!iEducacionService.existById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);            
        }
        iEducacionService.deleteEducacion(id);
        return new ResponseEntity(new Mensaje("La Educacion fue eliminada"), HttpStatus.OK);
    }
}
