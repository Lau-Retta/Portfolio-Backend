
package Portofolio.LautaroRetta.Controller;

import Portofolio.LautaroRetta.Dto.DtoCertificado;
import Portofolio.LautaroRetta.Entity.Certificado;
import Portofolio.LautaroRetta.Security.Mensaje;
import Portofolio.LautaroRetta.Service.CertificadoService;
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
@RequestMapping("Certificado")
@CrossOrigin(origins = "http://localhost:4200")
public class CertificadoController {
     @Autowired CertificadoService impcertiS;
 
    
    @GetMapping("/lista")
    public ResponseEntity<List<Certificado>> getCertificado(){
        List<Certificado> edu_list = impcertiS.getCertificado();
        return new ResponseEntity(edu_list, HttpStatus.OK);
    }

    @GetMapping("/traer/{id}")
    public Certificado findCertificado(@PathVariable int id){
        return impcertiS.findCertificado(id);
    }

    
    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody DtoCertificado dtoCerti){
        /*Hacemos la comprobacion por condicionales de que los datos ingresados sean los correctos
        y no generen errores al momento de ejecutar los metodos*/
        
        //comprobamos que no este vacio, si es true se le dev el mensaje
        if(StringUtils.isBlank(dtoCerti.getDescripcion())){
            return new ResponseEntity(new Mensaje("La descripcion no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }    
        if(impcertiS.existByCertificado(dtoCerti.getDescripcion())){
            return new ResponseEntity(new Mensaje("Ese Certificado no existe"), HttpStatus.BAD_REQUEST);
        }
        Certificado Certificado = new Certificado(dtoCerti.getDescripcion(), dtoCerti.getCertificado());
        impcertiS.saveCertificado(Certificado);
        
        
        return new ResponseEntity(new Mensaje("Certificado añadido"), HttpStatus.OK);
         
    }
    
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editar(@PathVariable("id") int id, @RequestBody DtoCertificado dtoCerti){
        
        /*Validademos que la Educacion que queremos editar exista comprobando por id*/
        if (!impcertiS.existById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);            
        }
        
        /*Validadmos si la Educacion que se quiere ingresar al editar ya exista, si ya existe se retorna
        el mensaje*/
        if (impcertiS.existByCertificado(dtoCerti.getDescripcion())&& 
                impcertiS.getCertificado(dtoCerti.getDescripcion()).get().getId()!=id){
            return new ResponseEntity(new Mensaje("Ese certificado ya no existe"), HttpStatus.BAD_REQUEST);
        }
        /*Validamos si el nombre de la Intstituto no quedo en blanco*/
        if(StringUtils.isBlank(dtoCerti.getCertificado())){
            return new ResponseEntity(new Mensaje("La URL del certificado esta en blanco"), HttpStatus.BAD_REQUEST);
        }
       
        Certificado exp = impcertiS.getOne(id).get();
        exp.setDescripcion(dtoCerti.getDescripcion());
        exp.setCertificado(dtoCerti.getCertificado());
        
        
        impcertiS.saveCertificado(exp);
        return new ResponseEntity(new Mensaje("Educacion actualizada"), HttpStatus.OK);
        
    }
    
      @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrar(@PathVariable("id") int id){
        /*Validademos que la Educacion que queremos borrar exista comprobando por id*/
        if (!impcertiS.existById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);            
        }
        impcertiS.deleteCertificado(id);
        return new ResponseEntity(new Mensaje("El curso fue eliminado"), HttpStatus.OK);
    }
}
