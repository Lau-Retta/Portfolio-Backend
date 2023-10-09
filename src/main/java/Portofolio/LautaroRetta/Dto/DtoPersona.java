
package Portofolio.LautaroRetta.Dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DtoPersona {
    
    private String Nombre;
    
    private String Apellido;
     
    private String img;
    
    private String banner;
    
    private String descripcion;
    
    private String linkedin;
    
    private String instagram;
    
    private String github;

    public DtoPersona() {
    }

    public DtoPersona(String Nombre, String Apellido, String img, String banner, String descripcion, String linkedin, String instagram, String github) {
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.img = img;
        this.banner = banner;
        this.descripcion = descripcion;
        this.linkedin = linkedin;
        this.instagram = instagram;
        this.github = github;
    }

    
    
    
}
