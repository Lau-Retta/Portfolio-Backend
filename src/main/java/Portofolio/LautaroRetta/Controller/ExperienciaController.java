
package Portofolio.LautaroRetta.Controller;

import Portofolio.LautaroRetta.Dto.DtoExperiencia;
import Portofolio.LautaroRetta.Entity.Experiencia;
import Portofolio.LautaroRetta.Security.Mensaje;
import Portofolio.LautaroRetta.Service.ExperienciaService;
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

@RestController
@RequestMapping("Trabajos")
@CrossOrigin(origins = "http://localhost:4200")
public class ExperienciaController {
    @Autowired ExperienciaService iexperienciaService;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Experiencia>> getExperiencia(){
        List<Experiencia> exp_list = iexperienciaService.getExperiencia();
        return new ResponseEntity(exp_list, HttpStatus.OK);
    }

    @GetMapping("/traer/{id}")
    public Experiencia findExperiencia(@PathVariable int id){
        return iexperienciaService.findExperiencia(id);
    }

    
    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody DtoExperiencia dtoexp){
        /*Hacemos la comprobacion por condicionales de que los datos ingresados sean los correctos
        y no generen errores al momento de ejecutar los metodos*/
        
        //comprobamos que no este vacio, si es true se le dev el mensaje
        if(StringUtils.isBlank(dtoexp.getEmpresa())){
            return new ResponseEntity(new Mensaje("La empresa no puede esta vacio"), HttpStatus.BAD_REQUEST);
        }    
        if(iexperienciaService.existByEmpresa(dtoexp.getEmpresa())){
            return new ResponseEntity(new Mensaje("Esa experiencia no existe"), HttpStatus.BAD_REQUEST);
        }
        Experiencia experiencia = new Experiencia(dtoexp.getEmpresa(), dtoexp.getDescripcion(), dtoexp.getInicio(),
        dtoexp.getFin(),dtoexp.getImg_Empresa(), dtoexp.getUrl_info());
        iexperienciaService.saveExperiencia(experiencia);
        
        
        return new ResponseEntity(new Mensaje("Esperiencia añadida"), HttpStatus.OK);
         
    }
   
    
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editar(@PathVariable("id") int id, @RequestBody DtoExperiencia dtoexp){
        
        /*Validademos que la experiencia que queremos editar exista comprobando por id*/
        if (!iexperienciaService.existById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);            
        }
        
        /*Validadmos si la experiencia que se quiere ingresar al editar ya exista, si ya existe se retorna
        el mensaje*/
        if (iexperienciaService.existByEmpresa(dtoexp.getEmpresa())&& 
                iexperienciaService.getEmpresa(dtoexp.getEmpresa()).get().getId()!=id){
            return new ResponseEntity(new Mensaje("Esa empresa ya existe no existe"), HttpStatus.BAD_REQUEST);
        }
        /*Validamos si el nombre de la empresa no quedo en blanco*/
        if(StringUtils.isBlank(dtoexp.getEmpresa())){
            return new ResponseEntity(new Mensaje("La empresa esta en blanco"), HttpStatus.BAD_REQUEST);
        }
       
        Experiencia exp = iexperienciaService.getOne(id).get();
        exp.setEmpresa(dtoexp.getEmpresa());
        exp.setDescripcion(dtoexp.getDescripcion());
        exp.setInicio(dtoexp.getInicio());
        exp.setFin(dtoexp.getFin());
        exp.setImg_empresa(dtoexp.getImg_Empresa());
        exp.setUrl_info(dtoexp.getUrl_info());
        
        
        iexperienciaService.saveExperiencia(exp);
        return new ResponseEntity(new Mensaje("Experiencia actualizada"), HttpStatus.OK);
        
        
      
    }
    
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrar(@PathVariable("id") int id){
        /*Validademos que la experiencia que queremos borrar exista comprobando por id*/
        if (!iexperienciaService.existById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);            
        }
        iexperienciaService.deleteExperiencia(id);
        return new ResponseEntity(new Mensaje("La experiencia fue eliminada"), HttpStatus.OK);
    }
    
   
}
