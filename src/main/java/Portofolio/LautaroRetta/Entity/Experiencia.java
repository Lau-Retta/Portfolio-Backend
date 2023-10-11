
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
public class Experiencia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) /*campo autogenereado*/
    private int id;
    @NotNull
    @Size(min = 1, max = 50, message = "No cumple con la longitud")
    private String empresa;
    
    @NotNull
    @Size(min = 1, max = 700, message = "No cumple con la longitud")
    private String descripcion;
    
    @Size(min = 1, max = 500, message = "No cumple con la longitud")
    private String img_empresa;
    
    @NotNull
    @Size(min = 1, max = 50, message = "No cumple con la longitud")
    private String inicio;
    
   
    @Size(min = 1, max = 50, message = "No cumple con la longitud")
    private String fin;
    
    @Size(min = 1, max = 700, message = "No cumple con la longitud")
    private String url_info;
    
    
    
    //Constructor vacio
    public Experiencia(){}
    //Constructor
    public Experiencia(String Empresa, String Descripcion, String img_empresa,String Inicio, 
          String fin, String url_info )
    {
        this.empresa = Empresa;
        this.descripcion = Descripcion;
        this.img_empresa = img_empresa;
        this.inicio = Inicio;
        this.fin = fin;
        this.url_info= url_info;
    }
    //GETTERS & SETTERS
}
