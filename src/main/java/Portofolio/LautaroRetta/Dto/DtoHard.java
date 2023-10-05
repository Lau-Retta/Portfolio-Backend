
package Portofolio.LautaroRetta.Dto;

import javax.validation.constraints.NotBlank;


public class DtoHard {
    @NotBlank
    private int nivel;
    @NotBlank
    private String hablidad;
    
    //vacio
    public DtoHard(){}
    //constructor
    public DtoHard(int nivel, String hablidad) {
        this.nivel = nivel;
        this.hablidad = hablidad;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getHablidad() {
        return hablidad;
    }

    public void setHablidad(String hablidad) {
        this.hablidad = hablidad;
    }
}
