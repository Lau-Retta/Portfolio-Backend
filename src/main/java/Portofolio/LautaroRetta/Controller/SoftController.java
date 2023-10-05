
package Portofolio.LautaroRetta.Controller;

import Portofolio.LautaroRetta.Dto.DtoSoft;
import Portofolio.LautaroRetta.Entity.SoftSkills;
import Portofolio.LautaroRetta.Security.Mensaje;
import Portofolio.LautaroRetta.Service.SoftService;
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
@RequestMapping("Soft")
@CrossOrigin(origins = "http://localhost:4200")
public class SoftController {
 @Autowired
    SoftService isoftservice;

    @GetMapping("/lista")
    public ResponseEntity<List<SoftSkills>> getSoftSkills() {
        List<SoftSkills> edu_list = isoftservice.getHabilidad();
        return new ResponseEntity(edu_list, HttpStatus.OK);
    }

    @GetMapping("/traer/{id}")
    public SoftSkills findSoftSkills(@PathVariable int id) {
        return isoftservice.findHabilida(id);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody DtoSoft dtoSoftSkills) {

        //comprobamos que no este vacio, si es true se le dev el mensaje
        if (StringUtils.isBlank(dtoSoftSkills.getHabilidad())) {
            return new ResponseEntity(new Mensaje("La habilidad no puede esta vacio"), HttpStatus.BAD_REQUEST);
        }
        if (isoftservice.existByHabilidad(dtoSoftSkills.getHabilidad())) {
            return new ResponseEntity(new Mensaje("Esa SoftSkills no existe"), HttpStatus.BAD_REQUEST);
        }
        SoftSkills softSkills = new SoftSkills();
        isoftservice.saveHabilidad(softSkills);

        return new ResponseEntity(new Mensaje("SoftSkills añadida"), HttpStatus.OK);

    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editar(@PathVariable("id") int id, @RequestBody DtoSoft dtoSoftSkills) {

        /*Validademos que la SoftSkills que queremos editar exista comprobando por id*/
        if (!isoftservice.existById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }

        /*Validadmos si la SoftSkills que se quiere ingresar al editar ya exista, si ya existe se retorna
        el mensaje*/
        if (isoftservice.existByHabilidad(dtoSoftSkills.getHabilidad())
                && isoftservice.getHabilidad(dtoSoftSkills.getHabilidad()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Ese Intstituto ya no existe"), HttpStatus.BAD_REQUEST);
        }
        /*Validamos si el nombre de la Intstituto no quedo en blanco*/
        if (StringUtils.isBlank(dtoSoftSkills.getHabilidad())) {
            return new ResponseEntity(new Mensaje("El Intstituto esta en blanco"), HttpStatus.BAD_REQUEST);
        }

        SoftSkills exp = isoftservice.getOne(id).get();
        exp.setHabilidad(dtoSoftSkills.getHabilidad());

        isoftservice.saveHabilidad(exp);
        return new ResponseEntity(new Mensaje("SoftSkills actualizada"), HttpStatus.OK);

    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrar(@PathVariable("id") int id) {
        /*Validademos que la SoftSkills que queremos borrar exista comprobando por id*/
        if (!isoftservice.existById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }
        isoftservice.deleteHabilidad(id);
        return new ResponseEntity(new Mensaje("La Habilidad fue eliminada"), HttpStatus.OK);
    }
}