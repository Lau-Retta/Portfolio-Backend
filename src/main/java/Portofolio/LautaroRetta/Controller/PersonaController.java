/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Portofolio.LautaroRetta.Controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@CrossOrigin(origins = "http://localhost:4200")/*Con esto evitamops el error " blocked by CORS policy",
le estamos diciendo que  vamos hacer llamados desde esta URL */
public class PersonaController {
    @Autowired IPersonaService iPersonaService; /*inyecta la dependencia de la interfas del servicio */
    
    //describimos las accines de los metodos 
    
    //Listar Personas
    @GetMapping("Personas/lista")/*va indicar en la url y va ejecutar este metodo*/
    public List<Persona> getPersona(){
        return iPersonaService.getPersona();
    }
    
    @PostMapping("Personas/crear")
    //creamos una Persona
    //para pasar los datos del front al back se utiliza el RequestBody, estos 
    //datos lo pasamos como parametrodel metodo
    public String createPersona(@RequestBody Persona Persona){
        iPersonaService.savePersona(Persona);
        return "Se creo la entidad de la Persona correctamente ";
    }
    
    @DeleteMapping("Personas/borrar/{id}")
    /*Con PathVariable pasamos los datos de parametro para que el indicador que tenga las llaves {}
    tome el valor pasado y lo coloque en la URL y se ejeccute el deleteMapping*/
    public String deletePersona(@PathVariable Long id){
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
    public Persona editPersona(@PathVariable Long id, 
            @RequestParam("nombre") String N_nombre,
            @RequestParam("apellido") String N_apellido,
            @RequestParam("img") String N_img,
            @RequestParam("descripcion") String N_descripcion,
            @RequestParam("linkedin") String N_linkedin,
            @RequestParam("instagram") String N_instagram,
            @RequestParam("github") String N_github)
            
    {
      //1° busca a la Persona que quiero editar por su id
      Persona Persona =  iPersonaService.findPersona(id);
      
      //2° cambia los valores de esa Persona x los nuevos
      Persona.setNombre(N_nombre);
      Persona.setApellido(N_apellido);
      Persona.setImg(N_img);
      Persona.setImg(N_descripcion);
      Persona.setImg(N_linkedin);
      Persona.setImg(N_instagram);
      Persona.setImg(N_github);
      
      //3° guarda esos valores y devuelve a ese objeto Persona modificado
      iPersonaService.savePersona(Persona);
      return Persona;
    }
    
    @GetMapping("Personas/traer/perfil")
    public Persona findPersona(){
        return iPersonaService.findPersona((long) 1);
    }
    
}
