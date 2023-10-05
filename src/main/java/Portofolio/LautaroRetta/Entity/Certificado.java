
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
@Getter @Setter
public class Certificado {
    
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Size(min = 1, max = 500, message = "No cumple con la longitud")
    private String descripcion;
    @Size(min = 1, max = 700, message = "No cumple con la longitud")
    private String certificado;

    public Certificado(){}
    
    public Certificado(String descripcion, String certificado) {
        this.descripcion = descripcion;
        this.certificado = certificado;
    }
    
    
}
