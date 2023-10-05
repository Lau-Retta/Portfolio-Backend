
package Portofolio.LautaroRetta.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Educacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull
    @Size(min = 1, max = 50, message = "No cumple con la longitud")
    private String instituto;
    
    @NotNull
    @Size(min = 1, max = 50, message = "No cumple con la longitud")
    private String titulo;
    
    @NotNull
    @Size(min = 1, max = 700, message = "No cumple con la longitud")
    private String descripcion;
    
    @NotNull
    @Size(min = 1, max = 700, message = "No cumple con la longitud")
    private String img_educacion;
    
    @NotNull
    @Size(min = 1, max = 50, message = "No cumple con la longitud")
    private String inicio;
    @NotNull
    @Size(min = 1, max = 50, message = "No cumple con la longitud")
    private String fin;
    
    //constructor vacio
    public Educacion(){}
    
    //constructor

    public Educacion(String instituto, String titulo, String descripcion, String img_educacion, String inicio, String fin) {
        this.instituto = instituto;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.img_educacion = img_educacion;
        this.inicio = inicio;
        this.fin = fin;
    }
}