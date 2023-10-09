/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Portofolio.LautaroRetta.Controller;

import Portofolio.LautaroRetta.Dto.DtoPersona;
import Portofolio.LautaroRetta.Entity.Persona;
import Portofolio.LautaroRetta.Interface.IPersonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")/*Con esto evitamops el error " blocked by CORS policy",
le estamos diciendo que  vamos hacer llamados desde esta URL */
public class PersonaController {

    @Autowired
    IPersonaService iPersonaService;

    /*inyecta la dependencia de la interfas del servicio */
    //describimos las accines de los metodos 
    //Listar Personas
    @GetMapping("Personas/lista")/*va indicar en la url y va ejecutar este metodo*/
    public List<Persona> getPersona() {
        return iPersonaService.getPersona();
    }

    @PostMapping("Personas/crear")
    //creamos una Persona
    //para pasar los datos del front al back se utiliza el RequestBody, estos 
    //datos lo pasamos como parametrodel metodo
    public String createPersona(@RequestBody Persona Persona) {
        iPersonaService.savePersona(Persona);
        return "Se creo la entidad de la Persona correctamente ";
    }

    @DeleteMapping("Personas/borrar/{id}")
    /*Con PathVariable pasamos los datos de parametro para que el indicador que tenga las llaves {}
    tome el valor pasado y lo coloque en la URL y se ejeccute el deleteMapping*/
    public String deletePersona(@PathVariable Long id) {
        iPersonaService.deletePersona(id);
        return "La Persona se elimino correctamente";
    }

    //editar una Persona
    /* 
    Para editar le pasamos el id de la Persona que queremos modificar
    Para indicar el id pasamos el PathVariable y para indicar los datos a modificar usamos
    el RequestParam (parametros de respuesta), que  entre parentesis y comillas colocamos el dato que se 
    VA a modificar y seguido de tipo de dato con el nuevo valor a tomar
     */
    //La URL quedaria con los datos modifcar asi:
    //Personas/editar/{id}/ nombre=nuevo&apellid=new&img=new
    @PutMapping("Personas/editar/{id}")
    public Persona editPersona(@PathVariable Long id, @RequestBody DtoPersona request) {
        // 1° busca a la Persona que quieres editar por su id
        Persona persona = iPersonaService.findPersona(id);

        // ...
// 2° cambia los valores de esa Persona por los nuevos
        persona.setNombre(request.getNombre());
        persona.setApellido(request.getApellido());
        persona.setImg(request.getImg());// Debería ser setImg
        persona.setBanner(request.getBanner());
        persona.setDescripcion(request.getDescripcion());// Establece la descripción
        persona.setLinkedin(request.getLinkedin()); // Establece LinkedIn
        persona.setInstagram(request.getInstagram());// Establece Instagram
        persona.setGithub(request.getGithub()); // Establece GitHub
// ...

        //3° guarda esos valores y devuelve a ese objeto Persona modificado
        iPersonaService.savePersona(persona);
        return persona;
    }

    @GetMapping("Personas/traer/perfil")
    public Persona findPersona() {
        return iPersonaService.findPersona((long) 1);
    }

}
