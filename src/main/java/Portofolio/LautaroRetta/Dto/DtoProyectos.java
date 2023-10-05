
package Portofolio.LautaroRetta.Dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DtoProyectos {
    @NotBlank
    private String nombre;
   
   
    private String descripcion;
    
  
    private String ima_proyecto;
    
    
    private String url;
    
    //constructor vacio
    public DtoProyectos(){}
    //constructor 

    public DtoProyectos(String nombre, String descripcion, String ima_proyecto, String url) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ima_proyecto = ima_proyecto;
        this.url = url;
    }
}
