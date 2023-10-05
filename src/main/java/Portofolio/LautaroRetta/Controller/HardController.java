
package Portofolio.LautaroRetta.Controller;

import Portofolio.LautaroRetta.Dto.DtoHard;
import Portofolio.LautaroRetta.Entity.Hard;
import Portofolio.LautaroRetta.Security.Mensaje;
import Portofolio.LautaroRetta.Service.HardService;
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
@RequestMapping("Hard")
@CrossOrigin(origins = "http://localhost:4200")
public class HardController {

    @Autowired
    HardService ihardService;

    @GetMapping("/lista")
    public ResponseEntity<List<Hard>> getHard() {
        List<Hard> edu_list = ihardService.getHabilidad();
        return new ResponseEntity(edu_list, HttpStatus.OK);
    }

    @GetMapping("/traer/{id}")
    public Hard findHard(@PathVariable int id) {
        return ihardService.findHabilida(id);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody DtoHard dtohard) {

        //comprobamos que no este vacio, si es true se le dev el mensaje
        if (StringUtils.isBlank(dtohard.getHablidad())) {
            return new ResponseEntity(new Mensaje("La habilidad no puede esta vacio"), HttpStatus.BAD_REQUEST);
        }
        if (ihardService.existByHabilidad(dtohard.getHablidad())) {
            return new ResponseEntity(new Mensaje("Esa Hard no existe"), HttpStatus.BAD_REQUEST);
        }
        Hard Hard = new Hard();
        ihardService.saveHabilidad(Hard);

        return new ResponseEntity(new Mensaje("Hard añadida"), HttpStatus.OK);

    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editar(@PathVariable("id") int id, @RequestBody DtoHard dtohard) {

        /*Validademos que la Hard que queremos editar exista comprobando por id*/
        if (!ihardService.existById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }

        /*Validadmos si la Hard que se quiere ingresar al editar ya exista, si ya existe se retorna
        el mensaje*/
        if (ihardService.existByHabilidad(dtohard.getHablidad())
                && ihardService.getHabilidad(dtohard.getHablidad()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Ese Intstituto ya no existe"), HttpStatus.BAD_REQUEST);
        }
        /*Validamos si el nombre de la Intstituto no quedo en blanco*/
        if (StringUtils.isBlank(dtohard.getHablidad())) {
            return new ResponseEntity(new Mensaje("El Intstituto esta en blanco"), HttpStatus.BAD_REQUEST);
        }

        Hard exp = ihardService.getOne(id).get();
        exp.setNivel(dtohard.getNivel());
        exp.setHabilidad(dtohard.getHablidad());

        ihardService.saveHabilidad(exp);
        return new ResponseEntity(new Mensaje("Hard actualizada"), HttpStatus.OK);

    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrar(@PathVariable("id") int id) {
        /*Validademos que la Hard que queremos borrar exista comprobando por id*/
        if (!ihardService.existById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }
        ihardService.deleteHabilidad(id);
        return new ResponseEntity(new Mensaje("La Habilidad fue eliminada"), HttpStatus.OK);
    }
}
