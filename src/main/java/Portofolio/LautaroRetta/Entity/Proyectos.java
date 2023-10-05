
package Portofolio.LautaroRetta.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter  @Setter
public class Proyectos {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull
    @Size(min = 1, max = 50, message = "No cumple con la longitud")
    private String nombre;
    
    @NotNull
    @Size(min = 1, max = 500, message = "No cumple con la longitud")
    private String descripcion;
    
  
    @Size(min = 1, max = 700, message = "No cumple con la longitud")
    private String ima_proyecto;
    
    @Size(min = 1, max = 700, message = "No cumple con la longitud")
    private String url;
    
    //constructor vacio
    public Proyectos(){}
    
    //constructor

    public Proyectos(String nombre, String descripcion, String ima_proyecto, String url) {
        this.nombre= nombre;
        this.descripcion = descripcion;
        this.ima_proyecto = ima_proyecto;
        this.url = url;
    }
    
}
