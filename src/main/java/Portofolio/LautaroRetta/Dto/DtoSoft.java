
package Portofolio.LautaroRetta.Dto;

import javax.validation.constraints.NotBlank;


public class DtoSoft {
    @NotBlank
    private String habilidad;
    
    public DtoSoft(){}
    
    public DtoSoft(String habilidad){
        this.habilidad = habilidad;
    }

    public String getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(String habilidad) {
        this.habilidad = habilidad;
    }
}
