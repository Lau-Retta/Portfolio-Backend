
package Portofolio.LautaroRetta.Controller;

import Portofolio.LautaroRetta.Dto.DtoProyectos;
import Portofolio.LautaroRetta.Entity.Proyectos;
import Portofolio.LautaroRetta.Security.Mensaje;
import Portofolio.LautaroRetta.Service.ProyectosService;
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
@RequestMapping("Proyectos")
@CrossOrigin(origins = "http://localhost:4200")
public class ProyectosController {
    @Autowired
    ProyectosService iproyectoService;

    @GetMapping("/lista")
    public ResponseEntity<List<Proyectos>> getProyectos() {
        List<Proyectos> edu_list = iproyectoService.getProyecto();
        return new ResponseEntity(edu_list, HttpStatus.OK);
    }

    @GetMapping("/traer/{id}")
    public Proyectos findProyecto(@PathVariable int id) {
        return iproyectoService.findProyecto(id);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody DtoProyectos dtoProyecto) {

        //comprobamos que no este vacio, si es true se le dev el mensaje
        if (StringUtils.isBlank(dtoProyecto.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre del proyecto no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
        if (iproyectoService.existByNombre_p(dtoProyecto.getNombre())) {
            return new ResponseEntity(new Mensaje("Ese Proyecto no existe"), HttpStatus.BAD_REQUEST);
        }
        Proyectos Proyecto = new Proyectos();
        iproyectoService.saveProyecto(Proyecto);

        return new ResponseEntity(new Mensaje("Proyecto añadida"), HttpStatus.OK);

    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editar(@PathVariable("id") int id, @RequestBody DtoProyectos dtoProyecto) {

        /*Validademos que la Proyecto que queremos editar exista comprobando por id*/
        if (!iproyectoService.existById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }

        /*Validadmos si la Proyecto que se quiere ingresar al editar ya exista, si ya existe se retorna
        el mensaje*/
        if (iproyectoService.existByNombre_p(dtoProyecto.getNombre())
                && iproyectoService.getNombre_p(dtoProyecto.getNombre()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Ese Intstituto ya no existe"), HttpStatus.BAD_REQUEST);
        }
        /*Validamos si el nombre del proyecto no quedo en blanco*/
        if (StringUtils.isBlank(dtoProyecto.getNombre())) {
            return new ResponseEntity(new Mensaje("El Intstituto esta en blanco"), HttpStatus.BAD_REQUEST);
        }

        Proyectos exp = iproyectoService.getOne(id).get();
        exp.setNombre(dtoProyecto.getNombre());
        exp.setDescripcion(dtoProyecto.getDescripcion());
        exp.setIma_proyecto(dtoProyecto.getIma_proyecto());
        exp.setUrl(dtoProyecto.getUrl());

        iproyectoService.saveProyecto(exp);
        return new ResponseEntity(new Mensaje("Proyecto actualizada"), HttpStatus.OK);

    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrar(@PathVariable("id") int id) {
        /*Validademos que la Proyecto que queremos borrar exista comprobando por id*/
        if (!iproyectoService.existById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }
        iproyectoService.deleteProyecto(id);
        return new ResponseEntity(new Mensaje("La Habilidad fue eliminada"), HttpStatus.OK);
    }
}
