package Portofolio.LautaroRetta.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter  @Setter /*Loombok ya nos da anotaciones para generar los get y set */
@Entity
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) /*campo autogenereado*/
    private Long id;
    @NotNull /*Indicamos que este campo no puede estar vacio*/
    @Size(min = 1, max = 50, message = "No cumple con la longitud")/*condicionamos la cantidad de caracteres y en caso contrario dev el message*/
    private String Nombre;
    @NotNull /*Indicamos que este campo no puede estar vacio*/
    @Size(min = 1, max = 50, message = "No cumple con la longitud")
    private String Apellido;
     
    private String img;
    
    private String descripcion;
    
    private String linkedin;
    
    private String instagram;
    
    private String github;
    
}
