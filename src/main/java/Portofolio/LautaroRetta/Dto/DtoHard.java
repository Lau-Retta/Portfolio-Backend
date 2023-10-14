
package Portofolio.LautaroRetta.Dto;

import javax.validation.constraints.NotBlank;


public class DtoHard {
    @NotBlank
    private int nivel;
    @NotBlank
    private String habilidad;
    
    //vacio
    public DtoHard(){}
    //constructor
    public DtoHard(int nivel, String hablidad) {
        this.nivel = nivel;
        this.habilidad = hablidad;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(String hablidad) {
        this.habilidad = hablidad;
    }
}
